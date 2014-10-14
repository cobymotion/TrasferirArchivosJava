


/**
 *Clase que tiene la interfaz del usuario asi como el tamaño del cache
 * @author luis
 */
public class Interfaz extends javax.swing.JFrame {

    private Thread hilo; 
    public static final long tamCache = 60000000;
    /**
     * Crea una interfaz para poder controlar el servidor
     */
    public Interfaz() {
        initComponents();
        jLabelTam.setText(String.valueOf(tamCache/1000000));
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabelAlertaStatus = new javax.swing.JLabel();
        jToggleArrancar = new javax.swing.JToggleButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListArchivos = new javax.swing.JList();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextPuerto = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabelStatus = new javax.swing.JLabel();
        jLabelTam = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(78, 12, 19));

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(214, 205, 205));
        jLabel1.setText("Servidor");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jLabel2.setText("Estatus del Servidor: ");

        jLabelAlertaStatus.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabelAlertaStatus.setText("Detenido");

        jToggleArrancar.setText("Arrancar");
        jToggleArrancar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleArrancarActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(jListArchivos);

        jLabel4.setText("Cache");

        jLabel5.setText("Numero de puerto:");

        jTextPuerto.setText("5001");

        jPanel2.setBackground(new java.awt.Color(197, 193, 189));

        jLabelStatus.setFont(new java.awt.Font("Ubuntu", 0, 10)); // NOI18N
        jLabelStatus.setText("El servidor no esta arrancado ni recibe peticiones");
        jPanel2.add(jLabelStatus);

        jLabelTam.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabelTam.setText("60");

        jLabel6.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel6.setText("MB");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabelAlertaStatus)
                            .addGap(18, 18, 18)
                            .addComponent(jToggleArrancar))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabelTam)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel6))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jTextPuerto, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabelAlertaStatus)
                    .addComponent(jToggleArrancar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabelTam)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextPuerto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jToggleArrancarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleArrancarActionPerformed
        // TODO add your handling code here:        
        if(jToggleArrancar.isSelected()) 
        {
            jToggleArrancar.setText("Apagar");
            int numPort = Integer.parseInt(jTextPuerto.getText()); 
            ///Arranca el proceso del servidor 
            ProcesoServidor procesoServidor = new ProcesoServidor(numPort,jLabelStatus,jLabelAlertaStatus, jListArchivos,jLabelTam); 
            hilo = new Thread(procesoServidor); 
            hilo.start();            
            ///            
        }else
        {
            if(hilo.isAlive())
                hilo.stop();                              
            jToggleArrancar.setText("Arrancar");            
            hilo=null;  
            jLabelStatus.setText("El servidor se detuvo");
            jLabelAlertaStatus.setText("Detenido");
        }
    }//GEN-LAST:event_jToggleArrancarActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabelAlertaStatus;
    private javax.swing.JLabel jLabelStatus;
    private javax.swing.JLabel jLabelTam;
    private javax.swing.JList jListArchivos;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextPuerto;
    private javax.swing.JToggleButton jToggleArrancar;
    // End of variables declaration//GEN-END:variables
}
