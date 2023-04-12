package main;



import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.naming.NameNotFoundException;

import main.entity.Entity;
import main.entity.player.PlayerKeybind;
import main.listeners.Inputable;
import main.listeners.KeyHandler;
import main.listeners.Updatable;
import main.map.MapHandler;

public class Game implements Runnable{
    private static Thread gameThread;
    private static GamePanel panel;
    private static int fps;
    private static KeyHandler keyHandler;
    private static Set<Updatable>  updatables;
    private static MapHandler mapHandler;

    private Game(){}

    public static void startGame(GamePanel gamePanel){
        fps = 60;
        gameThread = new Thread(new Game(),"game_loop");
        keyHandler = new KeyHandler();
        gameThread.start();
        panel = gamePanel;
        panel.addKeyListener(keyHandler);
        panel.setFocusable(true);
        panel.addNewLayer("background");
        panel.addNewLayer("player");
        updatables = new HashSet<>();
        PlayerKeybind.initKeys();
        mapHandler = new MapHandler(20, 20);
        addEntities(mapHandler.getGridAsList(), "background");

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
        //call update of updatables
        updatables.stream().forEach( e -> e.update());
        
    }

    public static MapHandler getMapHandler() {
        return mapHandler;
    }

    public static void addEntity(Entity entity, String layer){
        if(entity instanceof Inputable)
            keyHandler.addSubscriber((Inputable) entity);
        
        if(entity instanceof Updatable)
            updatables.add((Updatable) entity);

        try {
            panel.addSprites(layer, List.of(entity));
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void addEntities(List<? extends Entity> entity, String layer){
        if(entity instanceof Inputable)
            keyHandler.addSubscriber((Inputable) entity);
        
        if(entity instanceof Updatable)
            updatables.add((Updatable) entity);

        try {
            panel.addSprites(layer, entity);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
    }

}
