/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jumptosurvive;

import java.awt.Rectangle;

/**
 *
 * @author Asus
 */
public class Personaje extends Thread {

    private int dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2;//coordenadas de imagen
    private String image;
    private boolean cayo;
    private int gravedad = 1;

    public Personaje(int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, String image) {
        this.dx1 = dx1;
        this.dy1 = dy1;
        this.dx2 = dx2;
        this.dy2 = dy2;
        this.sx1 = sx1;
        this.sy1 = sy1;
        this.sx2 = sx2;
        this.sy2 = sy2;
        this.image = image;
        this.cayo = false;
    }

    @Override
    public void run() {
        if (cayo) {
            while (true) {
                cae(gravedad);
            }
        }
    }

    public boolean Cayo() {
        return cayo;
    }

    public void setCayo(boolean cayo) {
        this.cayo = cayo;
    }

    public int getDx1() {
        return dx1;
    }

    public void setDx1(int x) {
        this.dx1 = x;
    }

    public void plusDx1(int x) {
        this.dx1 += x;
    }

    public int getDy1() {
        return dy1;
    }

    public void plusDy1(int z) {
        this.dy1 += z;
    }

    public void setDy1(int z) {
        this.dy1 = z;
    }

    public int getDx2() {
        return dx2;
    }

    public void setDx2(int y) {
        this.dx2 = y;
    }

    public void plusDx2(int y) {
        this.dx2 += y;
    }

    public int getDy2() {
        return dy2;
    }

    public void plusDy2(int w) {
        this.dy2 += w;
    }

    public void setDy2(int w) {
        this.dy2 = w;
    }

    public int getSx1() {
        return sx1;
    }

    public void setSx1(int i) {
        this.sx1 = i;
    }

    public int getSy1() {
        return sy1;
    }

    public void setSy1(int o) {
        this.sy1 = o;
    }

    public int getSx2() {
        return sx2;
    }

    public void setSx2(int j) {
        this.sx2 = j;
    }

    public int getSy2() {
        return sy2;
    }

    public void setSy2(int k) {
        this.sy2 = k;
    }

    public void moveX(int m) {
        this.plusDx1(m);
        this.plusDx2(m);
    }

    public void moveY(int m) {
        this.plusDy1(m);
        this.plusDy2(m);
    }

    public void direccion(boolean i) {
        if (i) {
            this.setDx1(dx1);
            this.setDx2(dx1);
        } else {
            this.setDx1(dx1);
            this.setDx2(dy1);
        }
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getGravedad() {
        return gravedad;
    }

    public void setGravedad(int gravedad) {
        this.gravedad = gravedad;
    }

    public void cae(int g) {
        while (true) {
            plusDy1(g);
            plusDy2(g);
        }
    }

    public Rectangle getBounds() {

        return new Rectangle(0, dy1, 70, dy2 - dy1);
        //return new Rectangle(0, 260, 70 - 0, 330 - 260);
    }
}
