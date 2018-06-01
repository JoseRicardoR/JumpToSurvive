package modelo;

import javax.swing.JOptionPane;

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

    public int show() {
        String[] options = { "Inicio", "Reiniciar", "Siguiente nivel"};
        int x = JOptionPane.showOptionDialog(null, this.mensaje, this.titulo, JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
        switch (x) {
            case 0:
                System.out.println("Volver a Inicio");
                break;
            case 1:
                System.out.println("Riniciar nivel");
                break;
            case 2:
                System.out.println("Siguiente nivel");
                break;
            default:
                break;
        }
        return x;
    }
    
    public int show2() {
        String[] options = {"Inicio", "Reiniciar"};
        int x = JOptionPane.showOptionDialog(null, this.mensaje, this.titulo, JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
        switch (x) {
            case 0:
                System.out.println("Volver a Inicio");
                break;
            case 1:
                System.out.println("Riniciar nivel");
                break;
            default:
                break;
        }
        return x;
    }
}
