package main.sprites;

public enum Paths {
    SPRITES("src/resources/sprites/");

    private String path;

    Paths(String path){
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
