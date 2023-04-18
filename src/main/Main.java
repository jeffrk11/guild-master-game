package main;

import java.util.List;

import javax.naming.NameNotFoundException;

import main.entity.Creature;
import main.entity.Entity;
import main.entity.player.Player;
import main.map.MapHandler;


public class Main{

    public static void main(String[] args) throws NameNotFoundException {
        Environment env = new Environment(50, 50);
        env.setCamera(new Camera());

        env.addNewLayer("objects");
        env.addNewLayer("player");
        Game.setEnvironment(env);
        Game.startGame();
        
        MapHandler map = new MapHandler(10, 10);
        Game.getEnvironment().setMapHandler(map);
        // Game.getEnvironment().addEntities("background",map.getGridAsList());
        Entity x = new Creature("none.png");
        Entity player = new Player("ball.png");
        player.setX(100);
        player.setY(100);
        Game.getEnvironment().getCamera().setAtached(player);
        Game.getEnvironment().addEntities("player",List.of(player,x));
        
        
        // frame.setVisible(true);
    }
}