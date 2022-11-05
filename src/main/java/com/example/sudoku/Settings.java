package com.example.sudoku;

public class Settings {

    private String tilesPrimary="#FCF6F5FF";
    private String tilesSecondary="#F0E1B9FF";
    private String fontColor="#000";
    private static final Settings settings = new Settings();

    public String getTilesPrimary() {
        return tilesPrimary;
    }

    public void setTilesPrimary(String tilesPrimary) {
        this.tilesPrimary = tilesPrimary;
    }

    public String getTilesSecondary() {
        return tilesSecondary;
    }

    public void setTilesSecondary(String tilesSecondary) {
        this.tilesSecondary = tilesSecondary;
    }

    public String getFontColor() {
        return fontColor;
    }

    public void setFontColor(String fontColor) {
        this.fontColor = fontColor;
    }

    public static Settings getInstance(){
        return settings;
    }
}
