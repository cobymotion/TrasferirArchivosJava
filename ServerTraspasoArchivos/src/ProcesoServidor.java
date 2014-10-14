

import com.Archivo;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JLabel;
import com.Message;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JList;
/**
 * Clase que tiene todo el proceso transparente del servidor administrar las conexiones y busqueda de los archivos
 * @author luis
 */
public class ProcesoServidor implements Runnable {

    static boolean para = true;
    int port;
    JLabel status, statusmsg, tam;
    JList lista;
    /**
     * Inicializa los servidores
     * @param port
     * numero de puerto que se usuara para abrir los servicios
     * @param status
     * La etiqueta que tiene el estatus o los mensajes que el servidor ira conectando
     * @param statusmsg
     * La etiqueta junto al boton que permite saber el estado del server
     * @param lista
     * La JList que esta en la interfaz del cliente
     * @param tam 
     * La Etiqueta que tiene el valor del cache
     */

    public ProcesoServidor(int port, JLabel status, JLabel statusmsg, JList lista, JLabel tam) {
        this.port = port;
        this.status = status;
        this.statusmsg = statusmsg;
        this.lista = lista;
        this.tam = tam;
    }
    /**
     * Metodo que abre la conexion tiene un cciclo que permite buscar 
     * las peticiones que se hagan, manda un letrero si el archivo no 
     * existe y administra las formas de obtener el mismo 
     * Si esta cargado en cache los busca en la clase cache, 
     * si no lo extrae del path que se mando
     */
    @Override
    public void run() {
        ObjectInputStream entrada;
        ObjectOutputStream salida;
        Message peticion;
        ServerSocket server;
        Socket socket = null;
        try {
            status.setText("Abriendo la conexion");
            server = new ServerSocket(port);
        } catch (IOException ee) {
            status.setText("No fue posible abrir el puerto");
            status.setText("El servidor se detuvo");
            statusmsg.setText("Detenido");
            return;
        }

        while (para) {
            try {
                status.setText("El server acepta conexiones");
                statusmsg.setText("Escuchando");
                socket = server.accept();
                socket.setSoLinger(true, 10);
                entrada = new ObjectInputStream(socket.getInputStream());
                salida = new ObjectOutputStream(socket.getOutputStream());
                boolean recibe = true;
                do {
                    status.setText("El Server esta conectado con " + socket.getInetAddress());
                    statusmsg.setText("Conectado");
                    Object objPeticion = entrada.readObject();
                    if (objPeticion instanceof Message) {
                        peticion = (Message) objPeticion;
                        File file = new File(peticion.ruta);
                        if (peticion.ruta.equals("liberar")) {
                            recibe = false;
                        } else if (!file.exists()) {
                            peticion.ruta = "No existe el archivo";
                            salida.writeObject(objPeticion);
                        } else if (file.isDirectory()) {
                            peticion.ruta = "Es un directorio el que trata de seleccionar";
                            salida.writeObject(objPeticion);
                        } else if (file.exists()) {
                            if (existeEncache(peticion.ruta)) {
                                status.setText("Se encuentra en cache"); 
                                enviaArchivoCache(peticion.ruta,salida); 
                            } else {
                                enviaArchivo(peticion.ruta, salida, file.getName(), file.length());
                            }
                            Cache.rellenar(lista, tam);
                        }
                    } else {
                        System.out.println("Objeto no esperado");
                    }
                } while (recibe);
                //cerramos conexiones                
            } catch (IOException e) {
                status.setText("No fue posible abrir el puerto");
                System.out.println(e.toString());
            } catch (ClassNotFoundException ex) {
                status.setText("No fue encontrado la clase");
                System.out.println(ex.toString());
            }
        }
        if (socket.isConnected()) {
            try {
                socket.close();
            } catch (IOException ex) {
            }
        }
        status.setText("El servidor se detuvo");
        statusmsg.setText("Detenido");

    }

    private void enviaArchivo(String nombreArchivo, ObjectOutputStream oos, String nombre, long tam) {
        String key = nombreArchivo;
        List<Archivo> lista = new ArrayList();

        try {
            boolean enviadoUltimo = false;
            // Se abre el fichero.
            FileInputStream fis = new FileInputStream(nombreArchivo);

            // Se instancia y rellena un mensaje de envio de fichero
            Archivo archivo = new Archivo();
            archivo.nombreArchivo = nombre;

            // Se leen los primeros bytes del fichero en un campo del mensaje
            int leidos = fis.read(archivo.contenidoArchivo);

            // Bucle mientras se vayan leyendo datos del fichero
            while (leidos > -1) {

                // Se rellena el número de bytes leidos
                archivo.bytesValidos = leidos;

                // Si no se han leido el máximo de bytes, es porque el fichero
                // se ha acabado y este es el último mensaje
                if (leidos < Archivo.LONGITUD) {
                    archivo.ultimoMsj = true;
                    enviadoUltimo = true;
                } else {
                    archivo.ultimoMsj = false;
                }

                // Se envía por el socket
                oos.writeObject(archivo);
                lista.add(archivo);
                // Si es el último mensaje, salimos del bucle.
                if (archivo.ultimoMsj) {
                    break;
                }

                // Se crea un nuevo mensaje
                archivo = new Archivo();
                archivo.nombreArchivo = nombreArchivo;

                // y se leen sus bytes.
                leidos = fis.read(archivo.contenidoArchivo);
            }

            if (enviadoUltimo == false) {
                archivo.ultimoMsj = true;
                archivo.bytesValidos = 0;
                oos.writeObject(archivo);
                lista.add(archivo);
            }
            // Se cierra el ObjectOutputStream         
            RegistroCache rc = new RegistroCache(key, lista, nombre, tam);
            if(Cache.addACache(key, rc))
                status.setText("Agregado a la cache");
            else 
                status.setText("No pudo ser agregado a la cache por su tamaño");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean existeEncache(String ruta) {
        boolean isExist = false;
        Object obj = Cache.cache.get(ruta);
        if (obj != null) {
            isExist = true;
        } else {
            isExist = false;
        }
        return isExist;
    }

    private void enviaArchivoCache(String ruta, ObjectOutputStream oos) {
        RegistroCache registro = (RegistroCache)Cache.cache.get(ruta); 
        for(Archivo archivo: registro.partes)
        {
            try {
                oos.writeObject(archivo);
            } catch (IOException ex) {
                status.setText("Ocurrio un error al mandar por cache");
                System.out.println(ex.toString());
            }
        }
    }
}
