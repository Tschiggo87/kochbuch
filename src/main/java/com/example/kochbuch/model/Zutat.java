package com.example.kochbuch.model;

public class Zutat {
    private int zutatId;
    private String name;
    private String allergieInformationen;
    private String ernährungsinformationen;

    // Getter and Setter methods
    // ZutatID
    public int getZutatId() {
        return zutatId;
    }

    public void setZutatId(int zutatId) {
        this.zutatId = zutatId;
    }

    // Name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Allergie-Informationen
    public String getAllergieInformationen() {
        return allergieInformationen;
    }

    public void setAllergieInformationen(String allergieInformationen) {
        this.allergieInformationen = allergieInformationen;
    }

    // Ernährungsinformationen
    public String getErnährungsinformationen() {
        return ernährungsinformationen;
    }

    public void setErnährungsinformationen(String ernährungsinformationen) {
        this.ernährungsinformationen = ernährungsinformationen;
    }
}