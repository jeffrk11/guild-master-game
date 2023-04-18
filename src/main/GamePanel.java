package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

import main.entity.Entity;

public class GamePanel extends JPanel{
    private static GamePanel INSTANCE;
    private static Environment environment;
    public static int WIDTH = 800;
    public static int HEIGHT = 800;

    public GamePanel(){
        this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
    }

    public static GamePanel getInstance(){
        return INSTANCE == null ? new GamePanel() : INSTANCE;
    }

    public static void setEnvironment(Environment environment) {
        GamePanel.environment = environment;
    }

    @Override
    protected void paintComponent(Graphics g) {
        // long lasttime = System.nanoTime();
        super.paintComponent(g);
        if (environment == null)
            throw new RuntimeException("AN ENVIRONMENT IS MANDATORY");

        environment.updateGraphics(g);
        //debug mode
        if(Game.isDebug())
            GamePanel.drawDebugInfo(g);
        g.dispose();
    }

    private static void drawDebugInfo(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.RED);
        g2d.setStroke(new BasicStroke(2f));
        g2d.drawString("FPS: "+Game.getFps(), 10, 20);
        g2d.drawString("MOUSE: "+Game.getMousePosition(), 10, 35);

        //map
        for(var maptile : Game.getEnvironment().getMapHandler().getGridAsList()){
            g2d.drawOval(
                    ((int)maptile.getX() - Game.getEnvironment().getCamera().getScreenX()) + Game.getWidth() /2 , 
                    ((int)maptile.getY() - Game.getEnvironment().getCamera().getScreenY()) + Game.getHeight() /2, 
                    1, 1); 
        }
        //entities
        for(var entity : Game.getEnvironment().getEntities()){
            g2d.drawOval(
                    ((int)entity.getX() - Game.getEnvironment().getCamera().getScreenX()) + Game.getWidth() /2 , 
                    ((int)entity.getY() - Game.getEnvironment().getCamera().getScreenY()) + Game.getHeight() /2, 
                    2, 2); 
        }

        
    }
}
