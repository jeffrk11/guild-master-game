package main;

import java.awt.Graphics;
import java.awt.Point;

import javax.swing.text.Position;

import main.entity.Entity;
import main.listeners.Updatable;

public class Camera implements Updatable{
    private int screenX;
    private int screenY;
    private float zoom;
    private Entity atached;
    private Point center;

    public Camera(Entity atach){
        this.atached = atach;
        this.screenX = this.atached.getX();
        this.screenY = this.atached.getY();
        this.zoom = 1;
    }
    public Camera(){
        this.screenX = 0;
        this.screenY = 0;
        this.zoom = 1;
    }


    public void draw(Environment environment, Graphics g){
        // SCALE = 10;//camera.getZoom();
        
        environment.getLayers().forEach( (k,v) -> {
            v.forEach( s->{
                int newWidth = (int)(s.getWidth() * this.zoom);
                int newHeight = (int)(s.getHeight() * this.zoom);
                Point spriteScreenPosition = getScreenPosition(s.getX(), s.getY());
                g.drawImage(
                    s.getSprite(),   
                    spriteScreenPosition.x, 
                    spriteScreenPosition.y,
                    newWidth +1, //+1 to correct little visual bug
                    newHeight+1,
                    null);
            });
        });
        
        // System.out.println(System.nanoTime() - lasttime);
    }

    @Override
    public void update() {
        if(this.atached != null){
            int newX = (int)(this.atached.getX() * this.zoom);
            int newY = (int)(this.atached.getY() * this.zoom);
            this.screenX = newX;
            this.screenY = newY;
        }
    }

    public Point getScreenPosition(int x,int y){
        return new Point(
            (( ((int)(x * this.zoom)) - this.screenX) + Game.getWidth() /2) , 
            (( ((int)(y * this.zoom)) - this.screenY) + Game.getHeight()/2));
    }

    public Point getCenter(){
        return new Point(
            (Game.getWidth() /2), 
            (Game.getHeight()/2));
    }

    public int getScreenX() {
        return screenX;
    }

    public int getScreenY() {
        return screenY;
    }
    public void setScreenX(int screenX) {
        this.screenX = screenX;
    }
    public void setScreenY(int screenY) {
        this.screenY = screenY;
    }
    public void setAtached(Entity atached) {
        this.atached = atached;
    }
    public float getZoom() {
        return zoom;
    }
    
    public void setZoom(float zoom) {
        this.zoom = zoom;
    }
}
