package main.map;

import java.util.Random;
import static main.map.MapRule.of;

public enum MapTiles {

    SAND(4,of("SAND_GRASS",5),of("WATER",3)),
    WATER(0,of("WATER",4),of("SAND",3)),
    SAND_GRASS(8,of("SAND",6),of("GRASS",3)),
    GRASS(2,        of("FOREST", 2),of("GRASS", 8),of("GRASS_PURPLE", 2), of("SAND_GRASS", 2)),
    GRASS_PURPLE(7,of("GRASS",3),of("GRASS_PURPLE",6),of("FOREST",3)),
    FOREST(3,       of("FOREST", 2),of("DENSE_FOREST", 8)),
    DENSE_FOREST(5, of("GRASS",  2) ,of("DENSE_FOREST", 8));

    private int id;
    private MapRule[] rules;

    MapTiles(int id, MapRule... rules){
        this.id = id;
        this.rules = rules;
    }

    public int getId(){
        return this.id;
    }

    public static MapTiles getRandom(){
        return values()[new Random().nextInt(MapTiles.values().length)];
    }

    public MapRule[] getRules(){
        return this.rules;
    }

}
