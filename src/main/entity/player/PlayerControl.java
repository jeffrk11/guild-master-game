package main.entity.player;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class PlayerControl {

    private Map<Integer, Consumer<Player>> controls;

    private Map<Integer,Boolean> keysPressed;

    public PlayerControl(){
        controls = new HashMap<>();
        keysPressed = new HashMap<>();
        this.initConfig();
    }

    public void initConfig(){
        
        controls.put(PlayerKeybind.getKeys().get("MOVEMENT").get("MOVE RIGHT"), p ->{
            p.setX( p.getX() + p.getSpeed());
        });
        controls.put(PlayerKeybind.getKeys().get("MOVEMENT").get("MOVE LEFT"), p ->{
            p.setX( p.getX() - p.getSpeed());
        });
        controls.put(PlayerKeybind.getKeys().get("MOVEMENT").get("MOVE TOP"), p ->{
            p.setY( p.getY() - p.getSpeed());
        });
        controls.put(PlayerKeybind.getKeys().get("MOVEMENT").get("MOVE DOWN"), p ->{
            p.setY( p.getY() + p.getSpeed());
        });
    }

    public void action(int key,Player p){
        if(controls.containsKey(key)){
            controls.get(key).accept(p);
        }
    }

    public Map<Integer, Boolean> getKeysPressed() {
        return keysPressed;
    }

    public Map<Integer, Consumer<Player>> getControls() {
        return controls;
    }
}
