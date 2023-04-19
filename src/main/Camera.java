package main;

import java.awt.Graphics;
import java.awt.Point;

import javax.swing.text.Position;

import main.entity.Entity;
import main.listeners.Updatable;

public class Camera implements Updatable{
    private int screenX;
    private int screenY;
    private int zoom;
    private Entity atached;
    private Point center;

    public Camera(Entity atach){
        this.atached = atach;
        this.screenX = this.atached.getX();
        this.screenY = this.atached.getY();
        this.zoom = 0;
    }
    public Camera(){
        this.screenX = 0;
        this.screenY = 0;
        this.zoom = 0;
    }


    public void draw(Environment environment, Graphics g){
        // SCALE = 10;//camera.getZoom();
        
        environment.getLayers().forEach( (k,v) -> {
            v.forEach( s->{
 
                g.drawImage(
                    s.getSprite(),   
                    //(( s.getX() - this.screenX) + Game.getWidth() /2) - ( s.getSprite().getWidth() ), 
                    //(( s.getY() - this.screenY) + Game.getHeight()/2) - ( s.getSprite().getHeight()),
                    ((int)this.getScreenPosition(s.getX(),s.getY()).getX()) , 
                    ((int)this.getScreenPosition(s.getX(),s.getY()).getY()) ,
                    s.getWidth(),
                    s.getHeight(),
                    null);
            });
        });
        
        // System.out.println(System.nanoTime() - lasttime);
    }

    @Override
    public void update() {
        
        // if(this.atached != null){
        //     this.screenX = (int) this.atached.getPosition().x;
        //     this.screenY = (int) this.atached.getPosition().y;
        // }
    }

    public Point getScreenPosition(int x,int y){
        return new Point(
            (( x - this.screenX) + Game.getWidth() /2), 
            (( y - this.screenY) + Game.getHeight()/2));
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
    public int getZoom() {
        return zoom;
    }
    public void setZoom(int zoom) {
        this.zoom = zoom;
    }
}
