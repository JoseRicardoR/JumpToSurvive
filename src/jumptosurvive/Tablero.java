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
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author ASUS
 */
public class Tablero extends JPanel implements ActionListener {

    private Timer timer;
    private int[] mov = {0, 0};
    private int secuencia;
    private int numero;
    public Personaje player;

    public Tablero() {
        //Lanza un evento de tipo ActionListener cada 25 Milisegundo
        //Se hace referencia a this porque la misma clase (Tablero) procesa el evento
        this.timer = new Timer(25, this);
        this.numero = 0;
        this.secuencia = 0;
        setFocusable(true);
        addKeyListener(new EventosTeclado());
        this.timer.start();

        player = new Personaje(0, 260, 70, 330, 161, 162, 214, 209, "personaje1.png");

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image fondo = loadImage("4.jpg");
        g.drawImage(fondo, 0, 0, null);
        Image plataforma1 = loadImage("plataforma1.png");
        g.drawImage(plataforma1, -15, 325, 250, 600, 461, 81, 720, 335, this); //Plataforma de suelo derecha
        g.drawImage(plataforma1, 555, 325, 850, 525, 461, 81, 720, 335, this); // Plataforma de suelo izquierda
        g.drawImage(plataforma1, 300, 250, 350, 300, 920, 46, 1047, 166, this); //Bloque de hielo 
        g.drawImage(plataforma1, 455, 250, 505, 300, 920, 46, 1047, 166, this);// Bloque de hielo
        Image fuego = loadImage("fire3.png");
        g.drawImage(fuego, 250, 391, 560, 480, 0, 30, 499, 227, this);

        pruebadibujarrect(g, new Rectangle(-15, 325, 250 - 15, 600 - 325));
        pruebadibujarrect(g, new Rectangle(555, 325, 850 - 555, 525 - 325));

//        pruebadibujarrect(g, new Rectangle(300, 250, 350 - 300, 300 - 250));
//        pruebadibujarrect(g, new Rectangle(455, 250, 505 - 455, 300 - 250));
        g.translate(mov[0], mov[1]);
        pruebadibujarrect(g, player.getBounds());
        checkCollisions(player, mov);
        pintar(g, player);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        repaint();
        //checkCollisions();         //Se ejecuta la funcion de verificar colisiones
    }

    public Image loadImage(String imageName) {
        ImageIcon ii = new ImageIcon(imageName);
        Image image = ii.getImage();
        return image;
    }

    public boolean pintar(Graphics G, Personaje P) {
        Image p = loadImage(P.getImage());
        return G.drawImage(p, P.getDx1(), P.getDy1(), P.getDx2(), P.getDy2(), P.getSx1(), P.getSy1(), P.getSx2(), P.getSy2(), this);
    }

    private class EventosTeclado extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
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
            player.setCayo(true);
        } else {
            player.setCayo(false);
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

    //funcion de prueba
    public void pruebadibujarrect(Graphics g, Rectangle r) {
        g.drawRect(r.x, r.y, r.width, r.height);
    }

}
