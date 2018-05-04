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
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author ASUS
 */
public class Tablero extends JPanel implements ActionListener {

    private Timer timer;
    private int x, z;
    private int y, w;
    private int secuencia;
    private int numero;


    public Tablero() {
        //Lanza un evento de tipo ActionListener cada 25 Milisegundo
        //Se hace referencia a this porque la misma clase (Tablero) procesa el evento
        this.timer = new Timer(25, this);
        this.x = 0;
        this.y = 70;
        this.z = 260;
        this.w = 330;
        this.numero = 0;
        this.secuencia = 0;
        setFocusable(true);
        addKeyListener(new EventosTeclado());
        this.timer.start();
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
        Image personaje1 = loadImage("personaje1.png");
        g.drawImage(personaje1, this.x, this.z, this.y, this.w, 161, 162, 214, 209, this);
       

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

    private class EventosTeclado extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_W) {

                z += 40;
                w += 40;

            }
        }

        
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_D) {
                x+=4;
                y+=4;
               
                
            }
            
            if(key == KeyEvent.VK_A){
                x-=4;
                y-=4;
            }
            
            if (key == KeyEvent.VK_W) {
                if (z == 220 && w == 290 || z==300 && w==370) {
                    z = 260;
                    w = 330;
                } else {
                    z -= 40;
                    w -= 40;
                }

            }
        }
    }

}
