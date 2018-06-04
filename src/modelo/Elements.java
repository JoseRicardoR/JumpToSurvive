package modelo;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Point2D;

public class Elements {

    private int dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2;//coordenadas de imagen
    private String image; //Nombre de la imagen
//    private Rectangle rect; // Bordes de colision
    private Rectangle[] rect;

    //Constructor para cualquier elemento
    public Elements(String image, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2) {
        this.dx1 = dx1;
        this.dy1 = dy1;
        this.dx2 = dx2;
        this.dy2 = dy2;
        this.sx1 = sx1;
        this.sy1 = sy1;
        this.sx2 = sx2;
        this.sy2 = sy2;
        this.image = image;
//        this.rect = new Rectangle(dx1, dy1, dx2 - dx1, dy2 - dy1);
        rect = new Rectangle[4]; //      width     height
        rect[0] = new Rectangle(dx1, dy1, 10, (int) Point2D.distance(dx1, dy1, dx1, dy2));//izquierda 
        rect[1] = new Rectangle(dx2 -10, dy1, 10,(int) Point2D.distance(dx1, dy1, dx1, dy2) );//derecha
        rect[2] = new Rectangle( dx1, dy1 ,(int) Point2D.distance(dx1, dy1, dx2, dy1+10)  , 10);//arriba    
        rect[3] = new Rectangle(dx1, dy2-10, (int) Point2D.distance(dx1, dy1, dx2, dy1+10), 10);//abajo
    }

    public int getDx1() {
        return dx1;
    }

    public void setDx1(int dx1) {
        this.dx1 = dx1;
    }

    public int getDy1() {
        return dy1;
    }

    public void setDy1(int dy1) {
        this.dy1 = dy1;
    }

    public int getDx2() {
        return dx2;
    }

    public void setDx2(int dx2) {
        this.dx2 = dx2;
    }

    public int getDy2() {
        return dy2;
    }

    public void setDy2(int dy2) {
        this.dy2 = dy2;
    }

    public int getSx1() {
        return sx1;
    }

    public void setSx1(int sx1) {
        this.sx1 = sx1;
    }

    public void plusSx1(int x) {
        this.sx1 += x;
    }

    public int getSy1() {
        return sy1;
    }

    public void setSy1(int sy1) {
        this.sy1 = sy1;
    }

    public int getSx2() {
        return sx2;
    }

    public void setSx2(int sx2) {
        this.sx2 = sx2;
    }

    public void plusSx2(int x) {
        this.sx2 += x;
    }

    public int getSy2() {
        return sy2;
    }

    public void setSy2(int sy2) {
        this.sy2 = sy2;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Rectangle getRect(int i) {
        return rect[i];
    }

    public void setRect(Rectangle rect, int i) {
        this.rect[i] = rect;
    }

//    public Rectangle getRect() {
//        return rect;
//    }
//
//    public void setRect(Rectangle rect) {
//        this.rect = rect;
//    }
    //funciomn que dibja los rectangulos
    public void debugRect(Graphics g, int i) {
        g.drawRect(this.rect[i].x, this.rect[i].y, this.rect[i].width, this.rect[i].height);
    }

}
