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

    public String getDB_URI() {
        return "jdbc:mysql://sql12.freesqldatabase.com:3306/sql12555731";
    }

    public String getDB_USERNAME() {
        return "sql12555731";
    }

    public String getDB_PASSWORD() {
        return "UnjhEHWCwe";
    }
}
