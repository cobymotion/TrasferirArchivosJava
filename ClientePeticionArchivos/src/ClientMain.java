/**
 * Clase para mandar llamar la interfaz de los arhivos que se tienen Practicamente lo usamos para que tenga un inicio los .jar creados para Ejecutar
 * @author luis
 */

public class ClientMain {
  /**
   * Metodo principal para que pueda iniciarlizar la clase
   * @param args 
   * No son manejados
   */
    public static void main(String[] args) {
         InterfazCliente gui = new InterfazCliente();          
         gui.setVisible(true);
    }
}
