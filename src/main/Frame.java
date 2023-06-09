package main;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Frame extends JFrame{
    private static Frame INSTANCE;
    public Frame(){
        super("game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(GamePanel.WIDTH, GamePanel.HEIGHT);
        this.setResizable(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width /2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
    }

    public static JFrame getInstance() {
        return INSTANCE == null ? new Frame() : INSTANCE;
    }
}
