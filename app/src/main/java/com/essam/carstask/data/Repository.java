package com.essam.carstask.data;

import androidx.lifecycle.LiveData;

import com.essam.carstask.data.api.APIService;
import com.essam.carstask.data.api.apiResponseDataClasses.CarsList;
import com.essam.carstask.utls.Resource;

import java.util.List;

public class Repository {
    private APIService apiService = new APIService();

    //we will call this method to get the data
    public LiveData<Resource<List<CarsList>>> getItems(int page){
        return apiService.loadData(page);
    }
}
