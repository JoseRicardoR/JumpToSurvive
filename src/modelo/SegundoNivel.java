package modelo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.Timer;
import vista.MenuInicio;
import vista.MenuNiveles;

public class SegundoNivel extends JFrame implements ActionListener{
    private Tablero tablero;
    private Timer timer;
    private Personaje personaje;
    private Cronometro cronometro;
    private int puntuacion;
    private boolean llegoMeta;

    public SegundoNivel() {
        this.timer = new Timer(25, this);
        this.timer.start();
        this.cronometro = new Cronometro();
        this.personaje = new Personaje(0, 0, 50, 50, 161, 162, 214, 209, "personaje1.png");
        this.tablero = new Tablero(this.personaje, this.cronometro, "4.jpg");
        anadirElements();
        initUI();
    }

    public void anadirElements() {
        this.tablero.addElements(new Elements("plataforma1.png", -10, 80, 310, 200, 466, 81, 720, 250)); //Bloqeu superior izquierdo
        this.tablero.addElements(new Elements("plataforma1.png", -10, 400, 310, 600, 466, 81, 720, 335)); //Bloqeu inferior izquierdo
        this.tablero.addElements(new Elements("plataforma1.png", 650, 220, 810, 340, 466, 81, 720, 335)); //Bloqeu derecha
        this.tablero.addElements(new Elements("plataforma1.png", 410, 130, 460, 180, 920, 46, 1047, 166));  // bloqeu de hielo arriba
        this.tablero.addElements(new Elements("plataforma1.png", 550, 230, 600, 280, 920, 46, 1047, 166));  // bloqeu de hielo medio
        this.tablero.addElements(new Elements("plataforma1.png", 410, 330, 460, 380, 920, 46, 1047, 166));  // bloqeu de hielo abajo
        this.tablero.addElements(new Elements("coin.png", 720, 100, 770, 150, 0, 0, 0, 100)); // moneda
        this.tablero.addElements(new Elements("flag.png", 70, 330, 140, 400, 0, 0, 512, 512));// meta
        this.tablero.addElements(new Elements("spikes.png", 310, 420, 630, 480, 0, 0, 629, 127));// obstaculo
        this.tablero.addElements(new Elements("spikes.png", 630, 420, 950, 480, 0, 0, 629, 127));// obstaculo
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

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public int puntuacion() throws IOException {
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
           registrarPuntuacion(puntua);
            recordPuntuacion();
            this.tablero.setPuntuacion(String.valueOf(puntua));
            if(recordPuntuacion() == puntua ){
                message.setTitulo("Nuevo Record");
                message.setMensaje("Puntuacion mas alta: " + this.tablero.getPuntuacion());
                op = message.show();
            } else{
                message.setTitulo("Completado");
                message.setMensaje("Puntuacion: " + this.tablero.getPuntuacion());
                op = message.show();
            }
            this.tablero.setLlegoMeta(false);
        }
        return op;
    }
    
    public void registrarPuntuacion(int a) throws FileNotFoundException, IOException{
        // Se obtiene a fecha del sismtema para el regstro de la puntuacion
        Date date = new Date();
        DateFormat hourdateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
        // Se crea un registro con el identifiacdor del nivel, la puntuacion y la hora separada por -
        String registro ="2"+ " - "+ a + " - "+ hourdateFormat.format(date); 
        // Se instancian los io, y el archivo de lectura
        File fichero = new File ("Persistencia.txt");
        FileWriter outsputStream = new FileWriter(fichero, true);
        outsputStream.write(registro);
        // cada registro en una linea de texto
        outsputStream.write(System.getProperty( "line.separator" ));
       // Se cierra io
        outsputStream.close();
    }
    
     public int recordPuntuacion() throws FileNotFoundException, IOException{
        File fichero = new File ("Persistencia.txt");
        Scanner input = new Scanner(fichero);
        int record = 0;
        while(input.hasNextLine()){
            String[] text = input.nextLine().split(" - ");
            if(text[0].equals("2")){
                if( Integer.parseInt(text[1]) > record ){
                    record = Integer.parseInt(text[1]);
                } 
            }
        }
        return record;
    }

    public int gameOver() {
        int op = 100;
        Mensaje message = new Mensaje(null, null);
        if (!this.tablero.getPersonaje().isVivo()) {
            message.setTitulo("Game Over");
            message.setMensaje("Sigue intentando");
            op = message.show2();
            this.tablero.getPersonaje().setVivo(true);
        }
        return op;
    }

    public void options(int o) {
        switch (o) {
            case 0:
                //Volver a  inicios
                this.dispose();
                MenuInicio menuI = new MenuInicio();
                menuI.setVisible(true);
                menuI.setLocationRelativeTo(null);
                break;
            case 1:
                //Reiniciar
                this.dispose();
                MenuInicio menuRM = new MenuInicio();
                menuRM.setVisible(false);
                MenuNiveles menuR = new MenuNiveles(menuRM);
                menuR.setVisible(false);
                menuR.getNivelDos().setVisible(true);
                menuR.getNivelDos().getCronometro().iniciarCronometro();
                System.out.println("......Inicia Nivel dos.......");
                break;
            case 2:
                //Siguiente nivel
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
    try {
        options(puntuacion());
    } catch (IOException ex) {
        Logger.getLogger(SegundoNivel.class.getName()).log(Level.SEVERE, null, ex);
    }
        options(gameOver());
    }
}
