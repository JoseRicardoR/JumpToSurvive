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

    ;

    public Tablero() {
        //Lanza un evento de tipo ActionListener cada 25 Milisegundo
        //Se hace referencia a this porque la misma clase (Tablero) procesa el evento
        this.timer = new Timer(25, this);
        this.numero = 0;
        this.secuencia = 0;
        //debug------------------
        this.silhouette = false;
        //end debug-------------------
        setFocusable(true);
        addKeyListener(new EventosTeclado());
        this.timer.start();
        player = new Personaje(0, 260, 70, 330, 161, 162, 214, 209, "personaje1.png");

        blocks.add(new Elements(-15, 325, 250, 600, 461, 81, 720, 335));//bloque inferior izquierdo
        blocks.add(new Elements(555, 325, 850, 525, 461, 81, 720, 335));//bloque inferior derecha

        blocks.add(new Elements(300, 250, 350, 300, 920, 46, 1047, 166));//bloque hielo izquierda
        blocks.add(new Elements(455, 250, 505, 300, 920, 46, 1047, 166));//bloque hielo derecha

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image fondo = loadImage("4.jpg");
        g.drawImage(fondo, 0, 0, null);

        pintar(g, blocks);
        Image fuego = loadImage("fire3.png");
        g.drawImage(fuego, 250, 391, 560, 480, 0, 30, 499, 227, this);

        if (silhouette) {//dibujade los rectangulos de los bloques de colisiones
            for (int i = 0; i < 4; i++) {
                debugRect(g, blocks.get(i).getRect());
            }
        }
        g.translate(mov[0], mov[1]);
        debugRect(g, player.getBounds());
        pintar(g, player);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        repaint();
        checkCollisions(player, mov); //Se ejecuta la funcion de verificar colisiones
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
            G.drawImage(p, b.getDx1(), b.getDy1(), b.getDx2(), b.getDy2(), b.getSx1(), b.getSy1(), b.getSx2(), b.getSy2(), this);
        }
    }

    private class EventosTeclado extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();

            //debug colisiones
            if (key == KeyEvent.VK_F10) {
                if (silhouette) {
                    silhouette = false;
                } else {
                    silhouette = true;
                }
            }
            if (key == KeyEvent.VK_D) {
                player.setDx1(0);
                player.setDx2(70);
                mov[0] += 4;
            }
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
                player.setDy2(290);
                player.setDy1(220);

            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_W) {
                player.plusDy2(40);
                player.plusDy1(40);
            }
            if (key == KeyEvent.VK_A) {

                player.setDx1(70);
                player.setDx2(0);

            }
        }

    }

    public void checkCollisions(Personaje p, int[] mov) {
        Rectangle playerBordes = new Rectangle(p.getBounds().x + mov[0], p.getBounds().y + mov[1], p.getBounds().width, p.getBounds().height);
        Rectangle bordes1 = new Rectangle(-15, 325, 250 - 15, 600 - 325);          //Bordes de la plataforma para las colisiones
        Rectangle bordes2 = new Rectangle(550, 325, 850 - 550, 525 - 325);         //creados a partir de los paint components
        Rectangle bordes3 = new Rectangle(300, 250, 350 - 300, 300 - 250);
        Rectangle bordes4 = new Rectangle(455, 250, 505 - 455, 300 - 250);
        if (playerBordes.intersects(bordes1) || playerBordes.intersects(bordes2) || playerBordes.intersects(bordes3) || playerBordes.intersects(bordes4)) {
            System.out.println("Han colisionado .....");
            p.setCayo(true);
        } else {
            p.setCayo(false);
        }

//    public void checkCollisions() {
//        Rectangle playerBordes = player.getBounds();
//      if(coin != null){
//      Rectangle moneda = this.coin.getBounds();
//         if(rCraft.intersects(moneda)){
//             System.out.println("A colisionado con la moneda");
//             this.coin.setVisible(false);
//         }
//      }
    }

    //funcion de prueba-----------------------------------------------------------------------------------------------------------------------------------+
    public void debugRect(Graphics g, Rectangle r) {
        g.drawRect(r.x, r.y, r.width, r.height);
    }
}
