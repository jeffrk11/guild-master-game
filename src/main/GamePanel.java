package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JPanel;

import main.entity.player.Player;

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
        Player p = (Player) Game.getEnvironment().getLayers().get("player").get(0);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(Color.RED);
        g2d.setStroke(new BasicStroke(2f));
        g2d.drawString("FPS: "+Game.getFps(), 10, 20);
        g2d.drawString("MOUSE: "+Game.getMousePosition(), 10, 35);
        g2d.drawString("ZOOM: "+Game.getEnvironment().getCamera().getZoom(), 10, 50);
        g2d.drawString("player x: "+p.getX() +" y: "+p.getY() , 10, 80);
        g2d.drawString("player w: "+p.getWidth() +" h: "+p.getHeight() , 10, 100);
        g2d.drawString("camera x: "+Game.getEnvironment().getCamera().getScreenX() +" y: "+Game.getEnvironment().getCamera().getScreenY() , 10, 120);

        GamePanel.debugDrawEntityStuff(g2d);
        g.setColor(Color.red);
        //draw line to mouse
        
        Point playerPosition = Game.getEnvironment().getCamera().getScreenPosition(p.getPosition().x,p.getPosition().y);
        try{
            g2d.drawLine(
                    playerPosition.x,
                    playerPosition.y,
                    ((int)Game.getMousePosition().getX()), 
                    ((int)Game.getMousePosition().getY()));
        }catch(NullPointerException e){
            //do nothing
        }

        Point mouse = Game.getMousePosition();
        Math.toDegrees(Math.atan2(mouse.y, mouse.x));

        float radians = (float)Math.atan2(mouse.y - playerPosition.y, mouse.x - playerPosition.x);
        //this.instance.transform.setFromEulerAnglesRad(0, 0, radians).setTranslation(playerPos);

        g2d.drawString("degree :"+(Math.toDegrees(radians) + 180f), playerPosition.x + 20, playerPosition.y);
        g2d.drawString("inverse degree :"+((Math.toDegrees(radians))), playerPosition.x + 20, playerPosition.y + 20);
        
        Rectangle tangle = new Rectangle(200, 200, 20, 20);
        //g2d.translate(centerX, centerY);
        g2d.rotate(radians, playerPosition.x, playerPosition.y);
        g2d.draw(tangle);
        
    }

    private static void debugDrawEntityStuff(Graphics2D g2d){
        
        g2d.setColor(Color.white);
        //map
        for(var maptile : Game.getEnvironment().getMapHandler().getGridAsList()){
            Point position = Game.getEnvironment().getCamera().getScreenPosition(maptile.getX(),maptile.getY());
            g2d.drawOval(
                    position.x, 
                    position.y, 
                    1, 1); 
        }
        g2d.setColor(Color.red);
        //entities
        for(var entity : Game.getEnvironment().getEntities()){
            Point position = Game.getEnvironment().getCamera().getScreenPosition(entity.getPosition().x,entity.getPosition().y);
            g2d.drawOval(
                position.x, 
                position.y, 
                    2, 2);
            g2d.setColor(Color.CYAN);

            g2d.drawOval(
                position.x - (40 /2), 
                position.y - (40/2), 
                    40, 40);
        }
    }
}
