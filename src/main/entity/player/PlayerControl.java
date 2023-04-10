package main.entity.player;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class PlayerControl {
    private static Map<Integer, Consumer<Player>> controls;

    private PlayerControl(){}

    public static void initConfig(){
        controls = new HashMap<>();
        //move rigth
        controls.put(KeyEvent.VK_D, p ->{
            p.setX( p.getX() + p.getSpeed());
        });
        //move rigth
        controls.put(KeyEvent.VK_A, p ->{
            p.setX( p.getX() - p.getSpeed());
        });
        //move up
        controls.put(KeyEvent.VK_W, p ->{
            p.setY( p.getY() - p.getSpeed());
        });
        //move down
        controls.put(KeyEvent.VK_S, p ->{
            p.setY( p.getY() + p.getSpeed());
        });
    }

    public static void action(int key,Player p){
        if(controls.containsKey(key)){
            controls.get(key).accept(p);
        }
    }
}
