package main.entity.player;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import main.sprites.Paths;
import main.utils.SpriteUtils;

public class PlayerSprite {
    private Player player;
    private PlayerControl playerControl;

    private Map<String , List<BufferedImage>> sprites;

    private int spriteTime = 0;

    public PlayerSprite(Player player, PlayerControl playerControl){
        this.player = player;
        this.playerControl = playerControl;

        sprites = new HashMap<>();
        sprites.put("W", new LinkedList<>(List.of(SpriteUtils.getSprite(Paths.PLAYER, "left-idle.png"))));
        sprites.put("E", new LinkedList<>(List.of(SpriteUtils.getSprite(Paths.PLAYER, "right-idle.png"))));
        sprites.put("S", 
            new LinkedList<>(List.of(
                    // SpriteUtils.getSprite(Paths.PLAYER, "down-idle.png"),
                    SpriteUtils.getSprite(Paths.PLAYER, "down-step-1.png"),
                    SpriteUtils.getSprite(Paths.PLAYER, "down-step-2.png"))));
        sprites.put("N", 
            new LinkedList<>(List.of(

                    SpriteUtils.getSprite(Paths.PLAYER, "top-step-1.png"),
                    SpriteUtils.getSprite(Paths.PLAYER, "top-step-2.png"))));
    }

    public void updateSprite(){
        if(spriteTime++ < 14)
            return;
        LinkedList<BufferedImage> spritesMovement =  (LinkedList<BufferedImage>) this.sprites.getOrDefault(player.getDirection(), this.sprites.get("S"));

        //set and rotate
        player.setSprite(spritesMovement.getFirst());
        spritesMovement.addLast(spritesMovement.removeFirst());
        spriteTime = 0;
    }
}
