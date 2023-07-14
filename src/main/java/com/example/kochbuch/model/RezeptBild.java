package com.example.kochbuch.model;

public class RezeptBild {
    private int bildId;
    private int rezeptId;
    private String bildUrl;

    // Getter and Setter methods
    // BildID
    public int getBildId() {
        return bildId;
    }

    public void setBildId(int bildId) {
        this.bildId = bildId;
    }

    // RezeptID
    public int getRezeptId() {
        return rezeptId;
    }

    public void setRezeptId(int rezeptId) {
        this.rezeptId = rezeptId;
    }

    // Bild-URL
    public String getBildUrl() {
        return bildUrl;
    }

    public void setBildUrl(String bildUrl) {
        this.bildUrl = bildUrl;
    }
}
