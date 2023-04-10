package main.entity.player;

import java.util.HashMap;
import java.util.Map;

import main.entity.Entity;
import main.listeners.Inputable;
import main.listeners.Updatable;

public class Player extends Entity implements Inputable,Updatable{

    private int speed = 3;

    private Map<Integer,Boolean> keysPressed;

    public Player(String sprite) {
        super(sprite);
        PlayerControl.initConfig();
        keysPressed = new HashMap<>();
    }

    @Override
    public void input(int key,boolean pressed) {
        keysPressed.put(key, pressed);
    }
    
    //call every frame
    @Override
    public void update() {
        //do keysPressed actions
        keysPressed.forEach( (k,v) -> {
            if(v) PlayerControl.action(k, this);
        });
    }

    public int getSpeed() {return speed;}
    public void setSpeed(int speed) {this.speed = speed;}
    
}
