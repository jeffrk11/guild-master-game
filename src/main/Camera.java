package main;

import java.awt.Graphics;

import main.entity.Entity;
import main.listeners.Updatable;

public class Camera implements Updatable{
    private int screenX;
    private int screenY;
    private int zoom;
    private Entity atached;

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
        // entities.forEach( (k,v) -> {
        //     v.forEach( s->{
        //         s.setX(s.getX() + SCALE + 36); //screenX
        //         g.drawImage(s.getSprite(),   ( s.getX() - camera.getScreenX()) + screenWidth/2, 
        //         ( s.getY() - camera.getScreenY()) + screenHeight/2,
        //     //s.getSprite().getWidth() + SCALE,
        //     //s.getSprite().getHeight() + SCALE,
        //         null);
        //     });
        // });
        
        // g.dispose();
        // System.out.println(System.nanoTime() - lasttime);
    }

    @Override
    public void update() {
        if(this.atached != null){
            this.screenX = this.atached.getX();
            this.screenY = this.atached.getY();
        }
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
