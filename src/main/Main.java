package main;

import javax.swing.JFrame;

import main.entity.Creature;
import main.entity.Entity;
import main.entity.player.Player;


public class Main{

    public static void main(String[] args) {
        JFrame frame = new JFrame("game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);

        GamePanel panel = new GamePanel(new Camera());
        frame.add(panel);
        frame.pack();
        

        //game starting
        Game.startGame(panel);

        // Game.addEntity(new MapTile(Paths.MAPTILES, "grass.png"),"background");
        Game.addEntity(new Creature("none.png"),"player");
        Entity player = new Player("ball.png");
        player.setX(100);
        player.setY(100);
        panel.getCamera().setAtached(player);
        Game.addEntity(player,"player");
        
        frame.setVisible(true);
    }
}