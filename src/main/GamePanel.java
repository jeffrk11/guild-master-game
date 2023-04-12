package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.naming.NameNotFoundException;
import javax.swing.JPanel;

import main.entity.Entity;

public class GamePanel extends JPanel{
    final int originalTileSize = 30; // 30x30 tile
    final int scale = 2;
    
    final int tileSize = originalTileSize * scale; // 60
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;

    final int screenWidth = tileSize * maxScreenCol; //768
    final int screenHeight = tileSize * maxScreenRow; //768



    final private Map<String,List<Entity>> sprites;

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        sprites = new LinkedHashMap<>();
        
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        sprites.forEach( (k,v) -> {
            v.forEach( s-> g.drawImage(s.getSprite(), s.getX(), s.getY(),null) );
        });
        g.dispose();
    }

    //return if creation of new layer was successful
    public boolean addNewLayer(String name){
        if(sprites.containsKey(name))
            return false;
        sprites.put(name, new ArrayList<>());
        return true;
    }

    public void addSprites(String layer, List<? extends Entity> entities) throws NameNotFoundException{
        if(!sprites.containsKey(layer))
            throw new NameNotFoundException("This layer does not exist");
        
        sprites.get(layer).addAll(entities);
    }
}
