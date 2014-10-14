

import com.Archivo;
import java.util.List;
/**
 * Es una clase que sirve para almacenar todos las partes de un archivo y se toma como un registro completo del cache Se utiliza como un simple variable o estructura para llamar al archivo
 * @author luis
 */
public class RegistroCache {
    
    String key_ruta; 
    List<Archivo> partes; 
    String nombre; 
    long tam; 
    
    /**
     * Inicializa los datos que necesita para almacenar los valores
     * @param key_ruta
     * Es la ruta del archivo que se pidio, esto se toma como llave principal en el mapa
     * @param partes
     *     Es una lista de objetos de Tipo Archivo que guardan una parte del archivo En conjunto es el archivo en binario
     * @param nombre
     * Es el nombre del archivo sin path para el mostrarlos en la lista de cache
     * @param tam 
     * Es el tamaño que tiene el archivo
     */

    public RegistroCache(String key_ruta, List<Archivo> partes,String nombre, long tam) {
        this.key_ruta = key_ruta;
        this.partes = partes;
        this.nombre = nombre; 
        this.tam=tam;
    }
    
    
    
}
