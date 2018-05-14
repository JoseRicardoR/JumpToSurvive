/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jumptosurvive;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author ASUS
 */
public class Tablero extends JPanel implements ActionListener {

    private Timer timer;
    private int movX = 0, movY = 0;
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
        g.drawImage(plataforma1, -15, 325, 250, 600, 461, 81, 720, 335, this);
        g.drawImage(plataforma1, 300, 250, 350, 300, 920, 46, 1047, 166, this);
        g.drawImage(plataforma1, 455, 250, 505, 300, 920, 46, 1047, 166, this);
        g.drawImage(plataforma1, 550, 325, 850, 525, 461, 81, 720, 335, this);
        Image fuego = loadImage("fire3.png");
        g.drawImage(fuego, 250, 391, 560, 480, 0, 30, 499, 227, this);
        g.translate(movX, movY);
        pintar(g, player);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        repaint();
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
                movX += 4;

            }

            if (key == KeyEvent.VK_A) {
                player.setDx2(0);
                player.setDx1(70);
                movX += -4;
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

}
