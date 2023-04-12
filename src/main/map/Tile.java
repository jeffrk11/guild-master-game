package main.map;

public class Tile {
    private int x;
    private int y;
    private MapTiles tileType;

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int getX() {return x;}
    public void setX(int x) {this.x = x;}
    public int getY() { return y;}
    public void setY(int y) { this.y = y;}
    public MapTiles getTileType() { return tileType;}
    public void setTileType(MapTiles tileType) { this.tileType = tileType;}
}
