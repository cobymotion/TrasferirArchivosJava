

import com.Archivo;
import com.Message;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.JLabel;

/**
 *Hilo de ejecucion que permite escuchar hasta que reciba el ultimo mensaje del servidor solo atiende a la petición de un solo archivo, cuando se resuelve el hilo termina
 * @author luis
 */
public class ProcesandoPeticion implements Runnable {

    ObjectOutputStream salida;
    ObjectInputStream entrada;
    Socket socket;
    JLabel status;
    String rutaDestino; 
    
    /**
     * Recibe los datos que se necesitaran para el proceso
     * @param socket
     * Es la conexion que se abrio en la interfaz
     * @param salida
     * Es el Objeto de Output donde se escribira las posibles envios con el server
     * @param entrada
     * Es el objeto Input que se leera para que pueda recibir el archivo
     * @param status
     * Es el Jlabel que se tiene en la interfaz para mandar posibles mensajes que puedan necesitar ver el usuario
     * @param rutaDestino 
     * Es la cadena con la ruta donde se quiere que se ponga el archivo
     */

    public ProcesandoPeticion(Socket socket, ObjectOutputStream salida,ObjectInputStream entrada, JLabel status, String rutaDestino) {
        this.salida = salida;
        this.socket = socket;
        this.status = status;
        this.rutaDestino =rutaDestino;
        this.entrada = entrada; 
    }
    
    /**
     * Metodo que se ejecuta con el Start del hilo y estara leyendo lo que el servidor conteste despues de la peticion que se haga Ya sea un mensaje de alerta o simplemente una parte del archivo
     */

    @Override
    public void run() {
        try {            
            Object respuesta = entrada.readObject();
            if (respuesta instanceof Message) {
                Message alerta = (Message) respuesta;
                status.setText(alerta.ruta);
            } else if(respuesta instanceof Archivo){
                Archivo archivo = (Archivo)respuesta; 
                recibeArchivo(archivo, entrada);
            }
            //entrada.close();
        } catch (IOException e) {
            status.setText("Ocurrio un error al tratar de leer los archivos");
            System.out.println(e.toString());
        } catch (ClassNotFoundException ex) {
            status.setText("No se encontro la clase a castear");
        }

    }

    private void recibeArchivo(Archivo archivo, ObjectInputStream entrada) {
        try {
            FileOutputStream fos = new FileOutputStream(rutaDestino +  "/" + archivo.nombreArchivo);
            Object mensajeAux=archivo;
            Archivo mensajeRecibido=null; 
            boolean primero=true; 
            do
            {
               if(!primero)
                   mensajeAux = entrada.readObject();
               if(mensajeAux instanceof Archivo)
               {
                   mensajeRecibido = (Archivo)mensajeAux; 
                   System.out.print(new String(
                            mensajeRecibido.contenidoArchivo, 0,
                            mensajeRecibido.bytesValidos));
                    fos.write(mensajeRecibido.contenidoArchivo, 0,
                            mensajeRecibido.bytesValidos);
               }
               primero = false;
            }while(!mensajeRecibido.ultimoMsj);
            fos.close();     
            status.setText("Se termino de pasar el archivo");
        } catch (FileNotFoundException ex) {
            status.setText("No existe la ruta donde copiarlo");
            System.out.println(ex.toString());
        } catch (IOException ex) {
            status.setText("Error al copiar el archivo");
        } catch (ClassNotFoundException ex) {
            status.setText("Error al copiar el archivo");
        }
        
    }

}
