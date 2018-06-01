package modelo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.Timer;
import vista.MenuInicio;
import vista.MenuNiveles;

public final class PrimerNivel extends JFrame implements ActionListener {

    private JFrame frame;
    private Tablero tablero;
    private Timer timer;
    private Personaje personaje;
    private Cronometro cronometro;
    private int puntuacion;
    private boolean llegoMeta;

    public PrimerNivel(JFrame frame) {
        this.frame = frame;
        this.timer = new Timer(25, this);
        this.timer.start();
        this.cronometro = new Cronometro();
        this.personaje = new Personaje(0, 0, 50, 50, 161, 162, 214, 209, "personaje1.png");
        this.tablero = new Tablero(this.personaje, this.cronometro, "4.jpg");
        anadirElements();
        initUI();
    }

    public void anadirElements() {
        this.tablero.addElements(new Elements("plataforma1.png", -15, 325, 250, 600, 466, 81, 720, 335)); //Bloqeu inferior izquiero
        this.tablero.addElements(new Elements("plataforma1.png", 555, 325, 850, 525, 466, 81, 720, 335)); // bloque inferior derecho
        this.tablero.addElements(new Elements("plataforma1.png", 300, 250, 350, 300, 920, 46, 1047, 166)); //Bloque de hielo izquierdo
        this.tablero.addElements(new Elements("plataforma1.png", 455, 250, 505, 300, 920, 46, 1047, 166));  // bloqeu de hielo derecho
        this.tablero.addElements(new Elements("coin.png", 380, 180, 430, 230, 0, 0, 0, 100)); // moneda
        this.tablero.addElements(new Elements("flag.png", 690, 255, 760, 325, 0, 0, 512, 512));// meta
        this.tablero.addElements(new Elements("spikes.png", 250, 410, 555, 480, 0, 0, 629, 127));// obstaculo
    }

    private void initUI() { // Marco basico del Escenario 
        add(this.tablero);
        //800 w y 500 h
        setSize(800, 500);
        setTitle("Jump To Survive");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public Tablero getTablero() {
        return tablero;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

    public Personaje getPersonaje() {
        return personaje;
    }

    public void setPersonaje(Personaje personaje) {
        this.personaje = personaje;
    }

    public Cronometro getCronometro() {
        return cronometro;
    }

    public void setCronometro(Cronometro cronometro) {
        this.cronometro = cronometro;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int p) {
        this.puntuacion = p;
    }

    public boolean isLlegoMeta() {
        this.setLlegoMeta();
        return llegoMeta;
    }

    public void setLlegoMeta() {
        this.llegoMeta = this.tablero.isLlegoMeta();
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public int puntuacion() {
        this.tablero.setPuntuacion("0");
        int puntua;
        int op = 30;
        Mensaje message = new Mensaje(null, null);
        if (this.tablero.isLlegoMeta()) {
            String p[] = this.cronometro.getTexto().split(":");
            puntua = 100 - Integer.parseInt(p[1]);
            if (this.tablero.isMonedaRecogida()) {
                puntua = puntua + 10;
            }
            this.tablero.setPuntuacion(String.valueOf(puntua));
            message.setTitulo("Felicidades");
            message.setMensaje("Puntuacion: " + this.tablero.getPuntuacion());
            op = message.show();
            this.tablero.setLlegoMeta(false);
        }
        return op;
    }

    public int gameOver() {
        int op = 100;
        Mensaje message = new Mensaje(null, null);
        if (!this.tablero.getPersonaje().isVivo()) {
            message.setTitulo("Game Over");
            message.setMensaje("Sigue intentando");
            op = message.show();
            this.tablero.getPersonaje().setVivo(true);
        }
        return op;
    }

    public void options(int o) {
        switch (o) {
            case 0:
                this.dispose();
                MenuInicio menuI = new MenuInicio();
                menuI.setVisible(true);
                menuI.setLocationRelativeTo(null);
                break;
            case 1:
                this.dispose();
                MenuInicio menuRM = new MenuInicio();
                menuRM.setVisible(false);
                MenuNiveles menuR = new MenuNiveles(menuRM);
                menuR.setVisible(false);
                menuR.getNivelUno().setVisible(true);
                menuR.getNivelUno().getCronometro().iniciarCronometro();
                System.out.println("......Inicia Nivel Uno.......");
                break;
            case 2:
                this.dispose();
                MenuInicio menuSM = new MenuInicio();
                menuSM.setVisible(false);
                MenuNiveles menuS = new MenuNiveles(menuSM);
                menuS.setVisible(true);
                break;
            default:
                break;
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        options(puntuacion());
        options(gameOver());
    }

}
