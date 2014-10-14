
package com;

import java.io.Serializable;

/**
 *Clase que sirve para guardar los datos serializados en binario que tiene los datos referentes a los archivos o partes de los mismos
 * @author luis
 */
public class Archivo implements Serializable {
    
    public String nombreArchivo; 
    public boolean ultimoMsj; 
    public byte[] contenidoArchivo = new byte[LONGITUD]; 
    public final static int LONGITUD=1024;
    public int bytesValidos=0;
    
}
