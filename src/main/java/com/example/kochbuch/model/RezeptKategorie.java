package com.example.kochbuch.model;

public class RezeptKategorie {
    private int rezeptId;
    private int kategorieId;

    // Getter and Setter methods
    // RezeptID
    public int getRezeptId() {
        return rezeptId;
    }

    public void setRezeptId(int rezeptId) {
        this.rezeptId = rezeptId;
    }

    // KategorieID
    public int getKategorieId() {
        return kategorieId;
    }

    public void setKategorieId(int kategorieId) {
        this.kategorieId = kategorieId;
    }
}