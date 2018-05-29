
package vista;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class MenuInicio extends javax.swing.JFrame {

    /**
     * Creates new form Interfaz
     */
    public MenuInicio() {
        initComponents();
        this.setResizable(false);
        //this.setVisible(true);
//        this.setLocationRelativeTo(null); 
        this.setSize( new java.awt.Dimension(800, 500));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton4 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(800, 500));
        getContentPane().setLayout(null);

        jButton4.setText("Credits");
        jButton4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton4.setFocusPainted(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4);
        jButton4.setBounds(420, 280, 130, 40);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/ANUNCIO.png"))); // NOI18N
        jButton2.setMaximumSize(new java.awt.Dimension(333, 609));
        jButton2.setMinimumSize(new java.awt.Dimension(336, 609));
        jButton2.setPreferredSize(new java.awt.Dimension(333, 609));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(10, 0, 190, 480);

        jButton3.setText("Start Game");
        jButton3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3);
        jButton3.setBounds(420, 230, 130, 40);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/fondo 2.2.png"))); // NOI18N
        getContentPane().add(jLabel2);
        jLabel2.setBounds(280, 90, 430, 100);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/background.jpg"))); // NOI18N
        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 1229, 670);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
    MostrarCreditos();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String link ="http://www.rdontheroad.com/es/bogota/?utm_source=google&utm_medium=cpc&utm_campaign=eventos-OTR18-bogota-google&utm_content=&gclid=EAIaIQobChMIsJ3nvsGh2wIVZ8ThCh02ygu1EAEYASAAEgJ-XPD_BwE";
        try {
            Desktop.getDesktop().browse(new URI (link));
        } catch (URISyntaxException ex) {
            Logger.getLogger(MenuInicio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MenuInicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        MenuNiveles menu = new MenuNiveles( this );
        setLocationRelativeTo(null);
        menu.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

      static MenuInicio interfaz = new MenuInicio();
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
      
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MenuInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            interfaz.setVisible(true);
            interfaz.setResizable(false);
            interfaz.setSize( new java.awt.Dimension(800, 500));
            interfaz.setLocationRelativeTo(null); 
            }
        });
    }
    public void MostrarCreditos(){
        Icon icono = new ImageIcon(getClass().getResource("/vista/icono.png"));
        JOptionPane.showMessageDialog(rootPane,"Jose Ricardo Rincon\nCamilo Andres Usuga\nYoryi Daniel Martinez ","Desarrolladores",JOptionPane.INFORMATION_MESSAGE,icono);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}