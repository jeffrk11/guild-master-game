package main.map;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import main.entity.MapTile;
import main.sprites.Paths;

public class MapHandler {
    private final int TILE_SIZE = 36;
    private MapTile[][] grid;

    public MapHandler(int width, int height){
        grid = new MapTile[2][2];

        grid[0][0] = new MapTile(Paths.MAPTILES, "grass.png",0,0);
        grid[0][1] = new MapTile(Paths.MAPTILES, "grass.png",36,0);
        grid[1][0] = new MapTile(Paths.MAPTILES, "grass.png",0,36);
        grid[1][1] = new MapTile(Paths.MAPTILES, "grass.png",36,36);


    }

    public MapTile[][] getGrid() {
        return grid;
    }

    public List<MapTile> getGridAsList() {
        List<MapTile> gridList = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                gridList.add(grid[i][j]);
            }
        }
        return gridList;
    }
}
