/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jumptosurvive;

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

/**
 *
 * @author ASUS
 */
public class Tablero extends JPanel implements ActionListener {

    private Timer timer;
    private boolean silhouette;
    private int[] mov = {0, 0};
    private int secuencia;
    private int numero;
    private Personaje player;
    private ArrayList<Elements> blocks = new ArrayList<>();
    private Cronometro cronometro;
    private boolean monedaRecogida;

    public Tablero() {
        //Lanza un evento de tipo ActionListener cada 25 Milisegundo
        //Se hace referencia a this porque la misma clase (Tablero) procesa el evento
        this.timer = new Timer(25, this);
        this.numero = 0;
        this.secuencia = 0;
        this.cronometro = new Cronometro();
        this.cronometro.iniciarCronometro();
        this.monedaRecogida = false;
        //debug------------------
        this.silhouette = false;
        //end debug-------------------
        setFocusable(true);
        addKeyListener(new EventosTeclado());
        this.timer.start();
        player = new Personaje(0, 60, 70, 130, 161, 162, 214, 209, "personaje1.png");

        blocks.add(new Elements(-15, 325, 250, 600, 466, 81, 720, 335));//bloque inferior izquierdo
        blocks.add(new Elements(555, 325, 850, 525, 466, 81, 720, 335));//bloque inferior derecha

        blocks.add(new Elements(300, 250, 350, 300, 920, 46, 1047, 166));//bloque hielo izquierda
        blocks.add(new Elements(455, 250, 505, 300, 920, 46, 1047, 166));//bloque hielo derecha

        blocks.add(new Elements("coin.png", 300, 190, 350, 240, 0, 0, 0, 100)); // Moneda
        blocks.add(new Elements("flag.png", 690, 255, 760, 325, 0, 0, 512, 512)); // Meta

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        // g.setColor(Color.WHITE);

        Image fondo = loadImage("4.jpg");
        g.drawImage(fondo, 0, 0, null);

        if (this.numero % 10 == 0) {
            if (this.secuencia == 9) {
                this.secuencia = 0;
            } else {
                this.secuencia++;
            }
        }
        if (monedaRecogida == false) {
            Image coin = loadImage("coin.png");
            g.drawImage(coin, 300, 190, 350, 240, 100 * this.secuencia, 0, 100 * (this.secuencia) + 100, 100, this);
        }

        pintar(g, blocks);
        Image fuego = loadImage("fire3.png");
        g.drawImage(fuego, 250, 391, 560, 480, 0, 30, 499, 227, this);

        g.drawString(this.cronometro.getTexto(), 350, 20);

        if (silhouette) {//dibujade los rectangulos de los bloques de colisiones
            for (int i = 0; i < this.blocks.size(); i++) {
                blocks.get(i).debugRect(g);
            }
        }

        g.translate(mov[0], mov[1]);
        player.debugRect(g);
        pintar(g, player);

        if (!player.getCayo()) {//si esta cayendo
            mov[1] += player.getGravedad();

        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        repaint();
        checkCollisions(player, mov); //Se ejecuta la funcion de verificar colisiones
        this.numero++;
    }

    public Image loadImage(String imageName) {
        ImageIcon ii = new ImageIcon(imageName);
        Image image = ii.getImage();
        return image;
    }

    public void pintar(Graphics G, Personaje P) {
        Image p = loadImage(P.getImage());
        G.drawImage(p, P.getDx1(), P.getDy1(), P.getDx2(), P.getDy2(), P.getSx1(), P.getSy1(), P.getSx2(), P.getSy2(), this);
    }

    public void pintar(Graphics G, ArrayList<Elements> B) {
        Iterator<Elements> iterator = B.iterator();
        while (iterator.hasNext()) {
            Elements b = iterator.next();
            Image p = loadImage(b.getImage());
            if (!b.getImage().equals("coin.png")) {
                G.drawImage(p, b.getDx1(), b.getDy1(), b.getDx2(), b.getDy2(), b.getSx1(), b.getSy1(), b.getSx2(), b.getSy2(), this);
            }
        }
    }

    private class EventosTeclado extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();

            //debug colisiones
            if (key == KeyEvent.VK_F10) {
                silhouette = !silhouette;
            }
            if (key == KeyEvent.VK_F11) {
            }
//            _-------------------------------------------------------------------------------------------------------------------
            if (key == KeyEvent.VK_D) {
                player.setDx1(0);
                player.setDx2(70);
                mov[0] += 4;
            }

            if (key == KeyEvent.VK_A) {
                player.setDx2(0);
                player.setDx1(70);
                mov[0] += -4;
            }

            if (key == KeyEvent.VK_W) {
                //gravedad cambia

            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_A) {

                player.setDx1(70);
                player.setDx2(0);

            }
        }

    }

    public void checkCollisions(Personaje p, int[] mov) {
        Rectangle playerBordes = new Rectangle(p.getBounds().x + mov[0], p.getBounds().y + mov[1], p.getBounds().width, p.getBounds().height);
        for (int i = 0; i < this.blocks.size(); i++) {
            if (playerBordes.intersects(this.blocks.get(i).getRect())) {
                System.out.println("Hay colision con " + i);
                p.setCayo(true);
                if (this.blocks.get(i).getImage().equals("coin.png")) {
                    System.out.println("Moneda recolectada");
                    this.blocks.remove(this.blocks.get(i));
                    this.monedaRecogida = true;
                } else if (this.blocks.get(i).getImage().equals("flag.png")) {
                    System.out.println("llegue a la meta");
                    this.cronometro.pararCronometro();
                    String men = "Siguiente nivel";
                    Mensaje mensaje = new Mensaje(men);
                    mensaje.show();
                }

            } else {
                System.out.println("No hay colision con " + i);
            }
        }
    }
}
