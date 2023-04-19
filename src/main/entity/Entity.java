package main.entity;

import java.awt.Point;
import java.awt.image.BufferedImage;

import main.sprites.Paths;
import main.utils.SpriteUtils;

public abstract class Entity {
    protected int x,y;
    protected int width, height;
    private BufferedImage sprite;


    public Entity(String sprite){
        x = y = 0;
        this.sprite = SpriteUtils.getSprite(sprite);
        this.width = this.sprite.getWidth();
        this.height = this.sprite.getHeight();
    }

    public Entity(Paths spritePath,String sprite){
        x = y = 0;
        this.sprite = SpriteUtils.getSprite(spritePath,sprite);
        this.width = this.sprite.getWidth();
        this.height = this.sprite.getHeight();
    }


    public BufferedImage getSprite(){
        return this.sprite;
    }
    public void setSprite(BufferedImage sprite) {
        this.sprite = sprite;
    }

    public int getX() {
        return x ;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getHeight() {
        return height;
    }
    public int getWidth() {
        return width;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public void setWidth(int width) {
        this.width = width;
    }
    public Point getPosition(){
        return new Point(x + (getWidth() / 2), y + (getHeight() / 2));
    }
    
}
