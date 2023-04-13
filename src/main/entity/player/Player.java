package main.entity.player;

import java.awt.image.BufferedImage;
import java.util.Map.Entry;

import main.entity.Entity;
import main.listeners.Inputable;
import main.listeners.Updatable;

public class Player extends Entity implements Inputable,Updatable{
    private int speed;
    private boolean moving;
    private String direction;

    private PlayerControl controller;
    private PlayerSprite playerSprite;

    public Player(String sprite) {
        super(sprite);
        speed = 3;
        moving = false;
        direction  = "SOUTH";
        controller = new PlayerControl();
        playerSprite = new PlayerSprite(this, controller);
    }

    @Override
    public void input(int key,boolean pressed) {
        controller.getKeysPressed().put(key, pressed);
    }
    
    //call every frame
    @Override
    public void update() {
        //do keysPressed actions
        controller.getKeysPressed().forEach( (k,v) -> {
            if(v) {
                controller.action(k, this);
            }
        });
        updatePlayerStatus();
        playerSprite.updateSprite();
        System.out.println("player x: "+getX() +" y: "+getY());
    }

    //update player status by keypressed
    private void updatePlayerStatus(){
        //upate moving
        moving = isMoving() ? true : false;
        direction = getDirection();
        // System.out.println(direction);
    }

    public boolean isMoving(){
        for(Entry<String,Integer> entry : PlayerKeybind.getKeys().get("MOVEMENT").entrySet()){
            if(controller.getKeysPressed().getOrDefault(entry.getValue(),false))
                return true;
        }
        return false;
    }

    public String getDirection() {
        if(!moving)
            return direction;
        
        boolean left = controller.getKeysPressed().getOrDefault(PlayerKeybind.getKeys().get("MOVEMENT").get("MOVE LEFT"),false);
        boolean right = controller.getKeysPressed().getOrDefault(PlayerKeybind.getKeys().get("MOVEMENT").get("MOVE RIGHT"),false);
        boolean top = controller.getKeysPressed().getOrDefault(PlayerKeybind.getKeys().get("MOVEMENT").get("MOVE TOP"),false);
        boolean down = controller.getKeysPressed().getOrDefault(PlayerKeybind.getKeys().get("MOVEMENT").get("MOVE DOWN"),false);

        if(left && top)         direction = "NW";
        else if(right && top)   direction = "NE";
        else if(right && down)  direction = "SE";
        else if(left && down)   direction = "SW";
        else if(left)           direction = "W";
        else if(right)          direction = "E";
        else if(top)            direction = "N";
        else if(down)           direction = "S";
        else direction = "S";
        
        return direction;
    }
    public int getSpeed() {return speed;}
    public void setSpeed(int speed) {this.speed = speed;}
}
