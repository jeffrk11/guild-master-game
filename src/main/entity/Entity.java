package main.entity;

import java.awt.image.BufferedImage;

import main.sprites.Paths;
import main.utils.SpriteUtils;

public abstract class Entity {
    protected int x,y;
    private BufferedImage sprite;

    public Entity(String sprite){
        x = y = 0;
        this.sprite = SpriteUtils.getSprite(sprite);
    }

    public Entity(Paths spritePath,String sprite){
        x = y = 0;
        this.sprite = SpriteUtils.getSprite(spritePath,sprite);
    }


    public BufferedImage getSprite(){
        return this.sprite;
    }
    public void setSprite(BufferedImage sprite) {
        this.sprite = sprite;
    }

    public int getX() {
        return x;
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

    
}
