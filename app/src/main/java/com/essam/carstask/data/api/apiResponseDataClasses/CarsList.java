package com.essam.carstask.data.api.apiResponseDataClasses;

public class CarsList {
    private int id;
    private String brand;
    private String constractionYear;
    private boolean isUsed;
    private String imageUrl;

    public int getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getConstractionYear() {
        return constractionYear;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String isUsed() {
        if (isUsed) return "Used";
        return "New";
    }
}

