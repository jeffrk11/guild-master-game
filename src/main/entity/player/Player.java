package main.entity.player;

import java.util.Map.Entry;

import main.entity.Entity;
import main.listeners.Inputable;
import main.listeners.Updatable;

public class Player extends Entity implements Inputable,Updatable{
    private int speed;
    private boolean moving;

    private PlayerControl controller;

    public Player(String sprite) {
        super(sprite);
        speed = 3;
        moving = false;
        controller = new PlayerControl();
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
        // System.out.println("x: "+getX() +" y: "+getY());
    }

    //update player status by keypressed
    private void updatePlayerStatus(){
        //upate moving
        moving = isMoving() ? true : false;
    }

    public boolean isMoving(){
        for(Entry<String,Integer> entry : PlayerKeybind.getKeys().get("MOVEMENT").entrySet()){
            if(controller.getKeysPressed().getOrDefault(entry.getValue(),false))
                return true;
        }
        return false;
    }

    public int getSpeed() {return speed;}
    public void setSpeed(int speed) {this.speed = speed;}
    
}
