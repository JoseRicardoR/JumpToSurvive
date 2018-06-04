package modelo;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Font;
import java.net.MalformedURLException;
import java.net.URL;

public class Tablero extends JPanel implements ActionListener {
    
    private ArrayList<Elements> blocks;
    private ArrayList<Integer> chok;
    private Personaje personaje;
    private Cronometro cronometro;
    private String fondo;
    private String puntuacion;
    private boolean monedaRecogida, llegoMeta, silhouette;
    private Timer timer;
    private int[] mov;
    private double secuencia;
    private int contasalto;
    private boolean A, W, S, D;
    
    public Tablero(Personaje jugador, Cronometro cronometro, String fondo) {
        this.blocks = new ArrayList();
        this.chok = new ArrayList();
        this.cronometro = cronometro;
        this.personaje = jugador;
        this.fondo = fondo;
        this.llegoMeta = false;
        this.monedaRecogida = false;
        this.timer = new Timer(25, this);
        this.mov = new int[]{0, 0};
        this.timer.start();
        addKeyListener(new EventosTeclado());
        setFocusable(true);
        this.contasalto = 0;
    }

//Añadir elementos al arrayList blocks-----------------------------------
    public void addElements(Elements e) {
        this.blocks.add(e);
    }
//Remover elementos al arrayList blocks-----------------------------------

    public void removeElements(Elements e) {
        this.blocks.remove(e);
    }
    
    @Override
    public void paintComponent(Graphics g) {

//SetUp---------------------------------------------
        super.paintComponent(g);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 20)); // Fuente de letra y tamaño para todo el tablero

//Elementos Tablero----------------------------------
        Image background = loadImage(this.fondo);//Pinta el fondo del nivel
        g.drawImage(background, 0, 0, null);
        
        pintarElements(g, blocks); // Pinta el arrayList blocks

        g.drawString(this.cronometro.getTexto(), 350, 20); //Pinta el cronometro
// combinar teclas
        if (A) {
            personaje.setDx2(0);
            personaje.setDx1(50);
            personaje.mov(mov, 0, -4);
        }
        if (D) {
            personaje.setDx1(0);
            personaje.setDx2(50);
            personaje.mov(mov, 0, 4);
        }
        if (W && personaje.isCayo()) {
            personaje.setVelocidad(-8);
            personaje.setSaltando(true);
            personaje.setCayo(false);
        }
//----------------------------------------
        //Funcion que pinta los elementos con secuencia
        if (secuencia == 9) {
            secuencia = 0;
        }
        secuencia += 0.5;
        pintarMoneda(g);
        
        pintarBordes(g);//funcionPintarBordesDe colision()

        //Funcion pinta movimiento del personaje
        g.translate(mov[0], mov[1]);
        personaje.debugRect(g);
        pintarPersonaje(g, personaje);
        //Funcio que pinta la caida
        boolean modular = false;
        if (personaje.isSaltando()) {
            int altura_salto = 25;
            modular = !modular;
            if (contasalto > 15 && modular) {
                if (personaje.getVelocidad() != 0) {
                    personaje.plusVelocidad(+1);
                }
            }
            if (contasalto > altura_salto) {
                contasalto = 0;
                personaje.setSaltando(false);
                
            }
            contasalto += 1;
            
        }
        
        personaje.mov(mov, 1, personaje.getVelocidad());
    }

//Funcion que carga las imagenes----------------------------------------
    public Image loadImage(String imageName) {
        ImageIcon ii = new ImageIcon(imageName);
        Image image = ii.getImage();
        return image;
    }

//Funcion que pinta al personaje--------------------------------------------
    public void pintarPersonaje(Graphics G, Personaje P) {
        Image p = loadImage(P.getImage());
        G.drawImage(p, P.getDx1(), P.getDy1(), P.getDx2(), P.getDy2(), P.getSx1(), P.getSy1(), P.getSx2(), P.getSy2(), this);
    }

//Funcion que pinta el movimiento-------------------------
//Funcion qeu pnta los bordes de colision----------------------------------------
    public void pintarBordes(Graphics g) {
        if (this.silhouette) {
            for (int i = 0; i < this.blocks.size(); i++) {
                if (this.blocks.get(i).getRect() != null) {
                    blocks.get(i).debugRect(g);
                }
            }
        }
    }

//Funcion que pinta los elementos del arrayList a excepcion de los secuenciales----------------------------
    public void pintarElements(Graphics G, ArrayList<Elements> B) {
        Iterator<Elements> iterator = B.iterator();
        while (iterator.hasNext()) {
            Elements b = iterator.next();
            Image p = loadImage(b.getImage());
            if (!b.getImage().equals("coin.png")) {
                G.drawImage(p, b.getDx1(), b.getDy1(), b.getDx2(), b.getDy2(), b.getSx1(), b.getSy1(), b.getSx2(), b.getSy2(), this);
            }
        }
    }

//Funcion qeu pinta los elementos secuenciales-----------------------------------------------------
    public void pintarMoneda(Graphics g) {
        if (monedaRecogida == false) {
            Image coin = loadImage("coin.png");
            Elements moneda = null;
            for (int i = 0; i < this.blocks.size(); i++) {
                if (this.blocks.get(i).getImage().equals("coin.png")) {
                    moneda = this.blocks.get(i);
                }
            }
            g.drawImage(coin, moneda.getDx1(), moneda.getDy1(), moneda.getDx2(), moneda.getDy2(), 100 * (int) secuencia, moneda.getSy1(), 100 * (int) secuencia + 100, moneda.getSy2(), this);
        }
    }

//controles del juego--------------------------------------------
    public class EventosTeclado extends KeyAdapter {

        //eventos cuando se presiona una tecla-------------------------------
        @Override
        public void keyPressed(KeyEvent e) {
            
            int key = e.getKeyCode();

            //debug colisiones
            if (key == KeyEvent.VK_F10) {
                silhouette = true;
            }
            //  Movimiento del personaje-------------------------------------------------------------------------------------------
            if (key == KeyEvent.VK_D) {
                D = true;
            }
            
            if (key == KeyEvent.VK_A) {
                A = true;
            }
            if (key == KeyEvent.VK_W) {
                W = true;
            }
        }
        
        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_A) {
                A = false;
            }
            if (key == KeyEvent.VK_D) {
                D = false;
            }
            if (key == KeyEvent.VK_W) {
                W = false;
            }
        }
    }

    //Todas las funciones en conjunto-------------------------------------
    @Override
    public void actionPerformed(ActionEvent ae) {
        repaint(); //Funcion que ejecuta la funcion de pintar repetidamente m
        checkCollisions(this.personaje, mov); //Se ejecuta la funcion de verificar colisiones
    }

//Funcion que chuqeuea colisiones---------------------------------------------------
    public void checkCollisions(Personaje p, int[] mov) {
        try {
            Rectangle playerBordes = p.getBounds(mov);//Bordes de colision del personaje
            for (int i = 0; i < this.blocks.size(); i++) {
                if (playerBordes.intersects(this.blocks.get(i).getRect())) { //Ciclos que cuquean colisiones
                    //System.out.println("Hay colision con " + i);
//-----------------Colisiones especiales-----------------------------------------------------------------
                    switch (this.blocks.get(i).getImage()) {
                        case "coin.png":
                            this.setMonedaRecogida(true);
                            System.out.println("Moneda recolectada");
                            removeElements(this.blocks.get(i));
                            this.sonidoMoneda();
                            break;
                        case "flag.png": {
                            System.out.println("Lleguo a la meta");
                            this.setLlegoMeta(true);
                            this.blocks.get(i).setRect(null);
                            this.cronometro.pararCronometro();
                            break;
                        }
                        case "spikes.png": {
                            System.out.println("Murio");
                            this.blocks.get(i).setRect(null);
                            this.personaje.setVivo(false);
                            this.cronometro.pararCronometro();
                            break;
                        }
                        default:
                            break;
                    }
//----------------------------------------------------------------------------------
                    if (!chok.contains(i)) {
                        p.setVelocidad(0);
                        p.setCayo(true);
                        chok.add(i);
                    }
                }
            }
            
            for (int j = 0; j < chok.size(); j++) {
                if (!playerBordes.intersects(this.blocks.get(chok.get(j)).getRect()) && p.isSaltando() == false) {
                    //System.out.println("No hay colision");
                    p.setVelocidad(8);
                    p.setCayo(false);
                    chok.remove(j);
                }
            }
            
        } catch (Exception e) {
        }
        
    }

//sonido de moneda recolectadad--------------------------------------------
    public void sonidoMoneda() {
        URL url = null;
        try {
            url = new URL("file:coin.wav");
            AudioClip ac = Applet.newAudioClip(url);
            ac.play();
        } catch (MalformedURLException ex) {
            
        }
    }

//Getters y setters de los atributos de tablero------------------------------------------------
    public ArrayList<Elements> getBlocks() {
        return blocks;
    }
    
    public void setBlocks(ArrayList<Elements> blocks) {
        this.blocks = blocks;
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
    
    public String getFondo() {
        return fondo;
    }
    
    public void setFondo(String fondo) {
        this.fondo = fondo;
    }
    
    public boolean isMonedaRecogida() {
        return monedaRecogida;
    }
    
    public void setMonedaRecogida(boolean monedaRecogida) {
        this.monedaRecogida = monedaRecogida;
    }
    
    public boolean isLlegoMeta() {
        return llegoMeta;
    }
    
    public void setLlegoMeta(boolean llegoMeta) {
        this.llegoMeta = llegoMeta;
    }
    
    public boolean isSilhouette() {
        return silhouette;
    }
    
    public void setSilhouette(boolean silhouette) {
        this.silhouette = silhouette;
    }
    
    public Timer getTimer() {
        return timer;
    }
    
    public void setTimer(Timer timer) {
        this.timer = timer;
    }
    
    public ArrayList<Integer> getChok() {
        return chok;
    }
    
    public void setChok(ArrayList<Integer> chok) {
        this.chok = chok;
    }
    
    public int[] getMov() {
        return mov;
    }
    
    public void setMov(int[] mov) {
        this.mov = mov;
    }
    
    public String getPuntuacion() {
        return puntuacion;
    }
    
    public void setPuntuacion(String puntuacion) {
        this.puntuacion = puntuacion;
    }
    
    public double getSecuencia() {
        return secuencia;
    }
    
    public void setSecuencia(double secuencia) {
        this.secuencia = secuencia;
    }
    
}
