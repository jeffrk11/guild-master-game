package main;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import main.entity.Creature;
import main.entity.Entity;
import main.entity.MapTile;
import main.entity.player.Player;
import main.sprites.Paths;


public class Main{

    public static void main(String[] args) {
        JFrame frame = new JFrame("game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setBackground(Color.white);
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