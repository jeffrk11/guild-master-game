package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import main.entity.Entity;

public class GamePanel extends JPanel{
    final int originalTileSize = 30; // 16x16 tile
    final int scale = 2;
    
    final int tileSize = originalTileSize * scale; // 60
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;

    final int screenWidth = tileSize * maxScreenCol; //768
    final int screenHeight = tileSize * maxScreenRow; //768

    final public List<Entity> sprites;

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        sprites = new ArrayList<>();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Entity e = new Entity();
        // g.drawImage(e.img, e.x, e.y, null);
        sprites.forEach( e -> g.drawImage(e.getSprite(), e.getX(), e.getY(),null));
        g.dispose();
    }
}
