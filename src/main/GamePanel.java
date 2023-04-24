package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import main.entity.Creature;
import main.entity.Entity;
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
        Point mouse = Game.getMousePosition();
        try{
            g2d.drawLine(
                    playerPosition.x,
                    playerPosition.y,
                    ((int)Game.getMousePosition().getX()), 
                    ((int)Game.getMousePosition().getY()));
            int ca = Math.abs(mouse.x - playerPosition.x);
            int co = Math.abs(mouse.y - playerPosition.y);
            g2d.drawString("h: "+ (int)((Math.sqrt((ca*ca)+(co*co)))), Game.getMousePosition().x +10, Game.getMousePosition().y);
            
        }catch(NullPointerException e){
            //do nothing
        }

        
        // Math.toDegrees(Math.atan2(mouse.y, mouse.x));

        float radians = (float)Math.atan2(mouse.y - playerPosition.y, mouse.x - playerPosition.x);
        //this.instance.transform.setFromEulerAnglesRad(0, 0, radians).setTranslation(playerPos);

        g2d.drawString("degree :"+radians, playerPosition.x + 20, playerPosition.y);
        // g2d.drawString("inverse degree :"+((Math.toDegrees(radians*-1) + 180f)), playerPosition.x + 20, playerPosition.y + 20);

        g2d = (Graphics2D) g2d.create();

        Rectangle rec1 = new Rectangle((int)((playerPosition.x) -(14/2)), (int)((playerPosition.y) -(14/2)), 14, 14);
        Rectangle rec2 = new Rectangle((int)((playerPosition.x) -(10/2))-10, (int)((playerPosition.y) -(10/2)), 12, 12);
        AffineTransform transform1 = new AffineTransform();
        AffineTransform transform2 = new AffineTransform();
        // transform.translate(20, 0);
        transform2.rotate(radians,(playerPosition.x-rec2.getX())-(rec2.getWidth()/2),(playerPosition.y-rec2.getY())-(rec2.getHeight()/2));
        transform1.rotate(radians,(playerPosition.x-rec1.getX())-(rec1.getWidth()/2),(playerPosition.y-rec1.getY())-(rec1.getHeight()/2));
        rec2.translate((int)transform2.getTranslateX(),(int)transform2.getTranslateY());
        rec1.translate((int)transform1.getTranslateX(),(int)transform1.getTranslateY());
        // transform.rotate(radians, playerPosition.x, playerPosition.y);
        // rec.translate((int)transform.getTranslateX(), (int)transform.getTranslateY());
        
        // g2d.draw(rec);
        // g2d = (Graphics2D) g.create();
        // Rectangle tangle = new Rectangle(p.getX(), p.getY(), 10, 10);
        // tangle.translate((int)transform.getTranslateX() , (int)transform.getTranslateY());
        // Ellipse2D.Double elip = new Ellipse2D.Double(recposition.x, recposition.y, 30, 30);
        
        // g2d.draw(elip);
        
        //var t =  AffineTransform.getRotateInstance(Math.toRadians(180), playerPosition.x, playerPosition.y);
        // transform.translate(-100, 0);

        
        // g2d.rotate(radians);
        // g2d.setTransform(transform);
        // System.out.println(elip.intersects(tangle));
        // AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);

        // g2d.drawString("teste x:"+transform.getTranslateX()+" y: "+transform.getTranslateY(), playerPosition.x + 20, playerPosition.y + 40);
        // BufferedImage img = op.filter(x.getSprite(), null);
        
        g2d.draw(rec1);
        g2d.draw(rec2);
        // g2d.drawImage(img, playerPosition.x + 20, playerPosition.y,null);
        
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
