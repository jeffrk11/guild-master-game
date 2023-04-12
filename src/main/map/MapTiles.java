package main.map;

import java.util.Random;
import static main.map.MapRule.of;

public enum MapTiles {

    SAND("SAND.png",of("SAND_GRASS",5),of("WATER",3)),
    WATER("WATER.png",of("WATER",4),of("SAND",3)),
    SAND_GRASS("SAND_GRASS.png",of("SAND",6),of("GRASS",3)),
    GRASS("GRASS.png",        of("FOREST", 2),of("GRASS", 8),of("GRASS_PURPLE", 2), of("SAND_GRASS", 2)),
    GRASS_PURPLE("GRASS_PURPLE.png",of("GRASS",3),of("GRASS_PURPLE",6),of("FOREST",3)),
    FOREST("FOREST.png",       of("FOREST", 2),of("DENSE_FOREST", 8)),
    DENSE_FOREST("DENSE_FOREST.png", of("GRASS",  2) ,of("DENSE_FOREST", 8));

    private String id;
    private MapRule[] rules;

    MapTiles(String id, MapRule... rules){
        this.id = id;
        this.rules = rules;
    }

    public String getId(){
        return this.id;
    }

    public static MapTiles getRandom(){
        return values()[new Random().nextInt(MapTiles.values().length)];
    }

    public MapRule[] getRules(){
        return this.rules;
    }

}
