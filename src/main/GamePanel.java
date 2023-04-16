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
    public static final int TILE_SIZE = 36;
    public static int SCALE = 0;
    
    final int screenWidth = 800; //768
    final int screenHeight = 600; //768
    private Camera camera;


    final private Map<String,List<Entity>> sprites;

    public GamePanel(Camera camera){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        sprites = new LinkedHashMap<>();
        this.camera = camera;
        
    }

    @Override
    protected void paintComponent(Graphics g) {
        // long lasttime = System.nanoTime();
        super.paintComponent(g);

        SCALE = 0;//camera.getZoom();
        sprites.forEach( (k,v) -> {
            v.forEach( s-> g.drawImage(s.getSprite(),   ( s.getX() - camera.getScreenX()) + screenWidth/2, 
                                                        ( s.getY() - camera.getScreenY()) + screenHeight/2,
                                                    s.getSprite().getWidth() + SCALE,
                                                    s.getSprite().getHeight() + SCALE,
                                                    null));
        });
        g.dispose();
        // System.out.println(System.nanoTime() - lasttime);
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

    public void setCamera(Camera camera) {
        this.camera = camera;
    }
    public Camera getCamera() {
        return camera;
    }
}
