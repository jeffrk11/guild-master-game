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
    }

    public static void action(int key,boolean pressed,Player p){
        if(controls.containsKey(key)){
            controls.get(key).accept(p);
        }
    }
}
