package main.map;

public class MapRule {
    private String mapTile;
    private int percentage;

    private MapRule(String mapTile, int percentage){
        this.mapTile = mapTile;
        this.percentage = percentage;
    }

    public static MapRule of(String mapTile, int percentage){
        return new MapRule(mapTile,percentage);
    }


    public String getMapTile() {
        return mapTile;
    }
    public void setMapTile(String mapTile) {
        this.mapTile = mapTile;
    }
    public int getPercentage() {
        return percentage;
    }
    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    
}
