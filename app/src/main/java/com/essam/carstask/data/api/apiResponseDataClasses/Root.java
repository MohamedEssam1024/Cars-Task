package com.essam.carstask.data.api.apiResponseDataClasses;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Root {

    public int status;

    @SerializedName("data")
    public List<CarsList> cars;

    public int getStatus() {
        return status;
    }

    public List<CarsList> getCars() {
        return cars;
    }

}
