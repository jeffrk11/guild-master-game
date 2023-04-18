package main;



import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.naming.NameNotFoundException;
import javax.swing.JPanel;

import main.entity.Entity;
import main.entity.player.PlayerKeybind;
import main.listeners.Inputable;
import main.listeners.KeyHandler;
import main.listeners.MouseHandler;
import main.listeners.Updatable;
import main.map.MapHandler;

public class Game implements Runnable{
    private static Thread gameThread;
    private static GamePanel panel;
    private static int fps;
    private static KeyHandler keyHandler;
    private static MouseHandler mouseHandler;
    private static Set<Updatable>  updatables;

    private static Frame frame;
    private static Environment environment;

    private Game(){}

    private static void startFrame(Environment environment){
        frame = (Frame) Frame.getInstance();
        panel = GamePanel.getInstance();
        frame.add(panel);
        keyHandler = new KeyHandler();
        mouseHandler = new MouseHandler();
        panel.setFocusable(true);
        panel.addKeyListener(keyHandler);
        panel.addMouseListener(mouseHandler);
        panel.addMouseMotionListener(mouseHandler);
        panel.setEnvironment(environment);
        frame.pack();
        frame.setVisible(true);
    }

    public static void startGame(){
        fps = 60;
        gameThread = new Thread(new Game(),"game_loop");
        gameThread.start();
        updatables = new HashSet<>();
        PlayerKeybind.initKeys();
        
    }

    @Override
    public void run() {
        double drawInterval = 1_000_000_000/fps;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime = 0;
        int timer = 0;
        int drawCount= 0;

        while(gameThread != null){
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if(delta >= 1){
                update();
                panel.repaint();
                delta--;
                drawCount++;
                
            }
            if(timer >= 1_000_000_000 ){
                // System.out.println("FPS :" +drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    private void update(){
        //call update of updatables entities
        updatables.stream().forEach( e -> e.update());
        environment.getCamera().update();
        // System.out.println("camera x: "+panel.getCamera().getScreenX() +" y: "+panel.getCamera().getScreenY());
    }

    public static void addUpdatables(List<? extends Entity> entities){
        entities.forEach( e -> {
            if(e instanceof Inputable)
                keyHandler.addSubscriber((Inputable) e);
            if(e instanceof Updatable)
                updatables.add((Updatable) e);
        });
        
    }

    public static void setEnvironment(Environment environment) {
        Game.environment = environment;
        startFrame(environment);
    }
    public static Environment getEnvironment() {
        return environment;
    }

    public static int getWidth(){
        return frame.getWidth();
    }

    public static int getHeight(){
        return frame.getHeight();
    }
}
