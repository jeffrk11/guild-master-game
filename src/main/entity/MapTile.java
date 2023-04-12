package main.entity;

import main.sprites.Paths;

public class MapTile extends Entity{

    public MapTile(Paths spritePath,String sprite, int x, int y) {
        super(spritePath,sprite);
        this.x = x;
        this.y = y;
    }
    
}
