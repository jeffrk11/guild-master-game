package main.entity.player;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class PlayerKeybind {
    
    private static Map<String, Map<String,Integer>> keys;
    //read from some file in the future
    private PlayerKeybind(){}
    public static void initKeys(){
        keys = new HashMap<>();
        keys.put("MOVEMENT",
                Map.of( "MOVE RIGHT",KeyEvent.VK_D,
                        "MOVE LEFT",KeyEvent.VK_A,
                        "MOVE TOP",KeyEvent.VK_W,
                        "MOVE DOWN",KeyEvent.VK_S));

    }

    public static Map<String, Map<String, Integer>> getKeys() {
        return keys;
    }
}
