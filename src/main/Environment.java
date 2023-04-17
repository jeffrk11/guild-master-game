package main;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.NameNotFoundException;

import main.entity.Entity;
import main.map.MapHandler;

public class Environment{

    private MapHandler mapHandler;
    private List<Entity> entities;
    private Map<String, List<Entity>> layers;
    private Camera camera;
    
    public Environment(int width, int height){
        mapHandler = new MapHandler(width, height);
        layers = new HashMap<>();
        entities = new ArrayList<>();

    }

    public void updateGraphics(Graphics g){
        //update cameras
        if(camera!= null)
            camera.draw(this, g);
        else    
            System.out.println("You should use a camera to see XD");
    }

    public Camera getCamera() {
        return camera;
    }
    public void setCamera(Camera camera) {
        this.camera = camera;
    }

       //return if creation of new layer was successful
    public boolean addNewLayer(String name){
        if(layers.containsKey(name))
            return false;
        layers.put(name, new ArrayList<>());
        return true;
    }

    public void addEntities(String layer, List<? extends Entity> entities) throws NameNotFoundException{
        if(!this.layers.containsKey(layer))
            throw new NameNotFoundException("This layer does not exist");
        
        this.entities.addAll(entities);
        this.layers.get(layer).addAll(entities);

        Game.addUpdatables(entities);
    }

    public void setMapHandler(MapHandler mapHandler) {
        this.mapHandler = mapHandler;
    }
    public MapHandler getMapHandler() {
        return mapHandler;
    }

    public Map<String, List<Entity>> getLayers() {
        return layers;
    }
}
