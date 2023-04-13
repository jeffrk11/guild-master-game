package main.map;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import main.GamePanel;
import main.entity.MapTile;
import main.sprites.Paths;

public class MapHandler {
    
    private MapTile[][] grid;

    public MapHandler(int width, int height){
        grid = new MapTile[width][height];

        var maptiles =  WaveFunctionMap.generateMapGrid(width, height);
        int x = 0,y = 0;
        for (int i = 0; i < maptiles.length; i++) {
            for (int j = 0; j < maptiles[i].length; j++) {
                grid[i][j] = new MapTile(Paths.MAPTILES, maptiles[i][j].getId(), x, y);
                x += GamePanel.TILE_SIZE + GamePanel.SCALE;
            }
            y += GamePanel.TILE_SIZE + GamePanel.SCALE;
            x = 0;
        }

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
