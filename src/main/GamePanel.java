package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
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
    }
}
