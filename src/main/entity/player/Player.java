package main.entity.player;

import java.awt.Point;
import java.util.Map.Entry;

import main.Game;
import main.entity.Entity;
import main.listeners.Inputable;
import main.listeners.Updatable;

public class Player extends Entity implements Inputable,Updatable{
    private int speed;
    private boolean moving;
    private String directionS;

    private PlayerControl controller;
    private PlayerSprite playerSprite;

    public Player(String sprite) {
        super(sprite);
        speed = 2;
        moving = false;
        directionS  = "SOUTH";
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
        // System.out.println("player x: "+getX() +" y: "+getY());
    }

    private void lookToMouse(){
        
    }

    //update player status by keypressed
    private void updatePlayerStatus(){
        //upate moving
        moving = isMoving() ? true : false;
        directionS = getDirectionS();
        // System.out.println(direction);
    }

    public boolean isMoving(){
        for(Entry<String,Integer> entry : PlayerKeybind.getKeys().get("MOVEMENT").entrySet()){
            if(controller.getKeysPressed().getOrDefault(entry.getValue(),false))
                return true;
        }
        return false;
    }

    public String getDirectionS() {
        if(!moving)
            return directionS;
        
        boolean left = controller.getKeysPressed().getOrDefault(PlayerKeybind.getKeys().get("MOVEMENT").get("MOVE LEFT"),false);
        boolean right = controller.getKeysPressed().getOrDefault(PlayerKeybind.getKeys().get("MOVEMENT").get("MOVE RIGHT"),false);
        boolean top = controller.getKeysPressed().getOrDefault(PlayerKeybind.getKeys().get("MOVEMENT").get("MOVE TOP"),false);
        boolean down = controller.getKeysPressed().getOrDefault(PlayerKeybind.getKeys().get("MOVEMENT").get("MOVE DOWN"),false);

        if(left && top)         directionS = "NW";
        else if(right && top)   directionS = "NE";
        else if(right && down)  directionS = "SE";
        else if(left && down)   directionS = "SW";
        else if(left)           directionS = "W";
        else if(right)          directionS = "E";
        else if(top)            directionS = "N";
        else if(down)           directionS = "S";
        else directionS = "S";
        
        return directionS;
    }
    public int getSpeed() {return speed;}
    public void setSpeed(int speed) {this.speed = speed;}
}
