package main;

import main.entity.Creature;
import main.entity.Entity;
import main.entity.player.Player;
import main.map.MapHandler;


public class Main{

    public static void main(String[] args) {
        Environment env = new Environment(50, 50);
        env.setCamera(new Camera());
        env.addNewLayer("background");
        env.addNewLayer("objects");
        env.addNewLayer("player");
        Game.setEnvironment(env);
        Game.startGame();

        MapHandler map = new MapHandler(10, 10);
        Game.addEntities(map.getGridAsList(), "background");
        Game.addEntity(new Creature("none.png"),"player");
        Entity player = new Player("ball.png");
        player.setX(100);
        player.setY(100);
        Game.getEnvironment().getCamera().setAtached(player);
        Game.addEntity(player,"player");
        
        // frame.setVisible(true);
    }
}