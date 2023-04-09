package main.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.sprites.Paths;

public class SpriteUtils {
    
    public static BufferedImage getSprite(String name){
        try {
            return ImageIO.read(new File(Paths.SPRITES.getPath() + name));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
