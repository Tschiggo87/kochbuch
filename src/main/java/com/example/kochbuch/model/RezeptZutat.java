package com.example.kochbuch.model;

public class RezeptZutat {
    private int rezeptId;
    private int zutatId;
    private String menge;
    private String einheit;

    // Getter and Setter methods
    // RezeptID
    public int getRezeptId() {
        return rezeptId;
    }

    public void setRezeptId(int rezeptId) {
        this.rezeptId = rezeptId;
    }

    // ZutatID
    public int getZutatId() {
        return zutatId;
    }

    public void setZutatId(int zutatId) {
        this.zutatId = zutatId;
    }

    // Menge
    public String getMenge() {
        return menge;
    }

    public void setMenge(String menge) {
        this.menge = menge;
    }

    // Einheit
    public String getEinheit() {
        return einheit;
    }

    public void setEinheit(String einheit) {
        this.einheit = einheit;
    }
}
