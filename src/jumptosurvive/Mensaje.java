/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jumptosurvive;
import javax.swing.JOptionPane;
/**
 *
 * @author joser
 */
public class Mensaje {
    private String mensaje, titulo;

    public Mensaje(String mensaje, String titulo) {
        this.mensaje = mensaje;
        this.titulo = titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
       
    public void show() {
     JOptionPane.showMessageDialog(null, this.mensaje, this.titulo,JOptionPane.PLAIN_MESSAGE);
    }
}
