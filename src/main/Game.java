package main;



import main.entity.Entity;
import main.listeners.Inputable;
import main.listeners.KeyHandler;

public class Game implements Runnable{
    private static Thread gameThread;
    private static GamePanel panel;
    private static int fps;
    private static KeyHandler keyHandler;

    private Game(){}

    public static void startGame(GamePanel gamePanel){
        fps = 60;
        gameThread = new Thread(new Game(),"game_loop");
        keyHandler = new KeyHandler();
        gameThread.start();
        panel = gamePanel;
        panel.addKeyListener(keyHandler);
        panel.setFocusable(true);
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
        
    }

    public static void addEntity(Entity entity){
        if(entity instanceof Inputable)
            keyHandler.addSubscriber((Inputable) entity);

        panel.sprites.add(entity);
        
    }

}
