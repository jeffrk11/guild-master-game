package main.map;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



import main.GamePanel;
import main.entity.Entity;
import main.entity.MapTile;
import main.sprites.Paths;

public class MapHandler {
    private final int TILE_SIZE = 36; 
    private MapTile[][] grid;
    private float minZoom = 0.8f;
    private float maxzoom = 2.9f;

    public MapHandler(){
        grid = new MapTile[0][0];
    }

    public MapHandler(int width, int height){
        grid = new MapTile[width][height];

        var maptiles =  WaveFunctionMap.generateMapGrid(width, height);
        int x = 0,y = 0;
        for (int i = 0; i < maptiles.length; i++) {
            for (int j = 0; j < maptiles[i].length; j++) {
                grid[i][j] = new MapTile(Paths.MAPTILES, maptiles[i][j].getId(), x, y);
                x += TILE_SIZE;
            }
            y += TILE_SIZE;
            x = 0;
        }

    }

    public void deslocateTilesPositions(int size){
        int x = grid[0][0].getX();
        int y = grid[0][0].getY();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j].setX( x + size );
                grid[i][j].setY( y + size );
                x += grid[i][j].getWidth() + size*-1;
            }
            y += grid[i][0].getHeight() + size*-1;
            x = 0;
        }
    }

    public MapTile[][] getGrid() {
        return grid;
    }

    public List<? extends Entity> getGridAsList() {
        List<MapTile> gridList = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                gridList.add(grid[i][j]);
            }
        }
        return gridList;
    }

    public boolean isZoomable(float currentZoom, int zoom){
        if(zoom > 0){//zoom out
            return currentZoom < maxzoom;
        } else{ // zoom in
            return currentZoom > minZoom;
        }
    }
}
