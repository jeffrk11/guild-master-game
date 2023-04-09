package main.entity.player;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import main.entity.Entity;
import main.listeners.Inputable;

public class Player extends Entity implements Inputable{

    private int speed = 3;

    public Player(String sprite) {
        super(sprite);
        PlayerControl.initConfig();

    }

    @Override
    public void input(int key,boolean pressed) {
        PlayerControl.action(key, pressed, this);
    }

    public int getSpeed() {return speed;}
    public void setSpeed(int speed) {this.speed = speed;}
    
}
