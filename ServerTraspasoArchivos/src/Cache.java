

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
/**
 *     Clase que permite almacenar y administrar el cache de la aplicacion
 * @author luis
 */
public class Cache {

    static HashMap<String, RegistroCache> cache = new HashMap<>();
    
    /**
     * alida la entrada al cache de los datos, siempre y cuando haya espacion disponible, tiene una forma recursiva para si no hay espacio vuelva a invocarse con un elemento menos
     * @param key
     * Trae la cadena (ruta) que nos servira para identificar el objeto que esta en cache
     * @param rCache
     * Es el registro que trae almacenado el archivo que manetendremos en memoria
     * @return 
     * verdadero si es que pudo agregarse al cache y falso si no es posible
     */

    public static boolean addACache(String key, RegistroCache rCache) {
        if (getDisponible() >= rCache.tam) {
            cache.put(key, rCache);
        } else {
            if (cache.size() > 0) {
                Iterator iterator = cache.entrySet().iterator();
                Map.Entry ent = (Map.Entry) iterator.next();
                cache.remove(ent.getKey().toString());
                addACache(key, rCache);
            }else {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Hace un recorrido de lo que se tiene en el cache y lo pone en la lista que tenemos en la interfaz del servidor, tambien calculara nuevamente el espacio disponible y lo actualizara en el label correspondiente
     * @param lista
     * Es el JList de Swing que tenemos en la interfaz
     * @param tam 
     * Es el Jlbael que tenemos para marcar que hay espacio disponible
     */

    public static void rellenar(JList lista, JLabel tam) {
        lista.removeAll();
        long restante = Interfaz.tamCache;
        Iterator iterator = cache.entrySet().iterator();
        DefaultListModel modelo = new DefaultListModel();
        while (iterator.hasNext()) {
            Map.Entry ent = (Map.Entry) iterator.next();
            RegistroCache registro = (RegistroCache) ent.getValue();
            modelo.addElement(registro.nombre);
            restante -= registro.tam;
        }
        tam.setText(String.valueOf(restante / 1000000));
        lista.setModel(modelo);
    }
    
    /**
     * Solamente hace un calculo mediante un recorrido del cache para que pueda verificar si puede o no puede agregar
     * @return 
     * Returna el long con los bytes disponibles de la cache
     */

    public static long getDisponible() {
        long restante = Interfaz.tamCache;
        Iterator iterator = cache.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry ent = (Map.Entry) iterator.next();
            RegistroCache registro = (RegistroCache) ent.getValue();
            restante -= registro.tam;
        }
        return restante;
    }

}
