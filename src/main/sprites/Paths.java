package main.sprites;

public enum Paths {
    SPRITES("src/resources/sprites/"),
    MAPTILES("src/resources/sprites/maptiles/"),
    PLAYER("src/resources/sprites/player/");

    private String path;

    Paths(String path){
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
