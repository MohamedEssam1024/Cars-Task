package com.essam.carstask.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.essam.carstask.data.Repository;
import com.essam.carstask.data.api.apiResponseDataClasses.CarsList;
import com.essam.carstask.utls.Constants;
import com.essam.carstask.utls.Resource;

import java.util.List;

public class CarsListViewModel extends ViewModel {
    private Repository repository = new Repository();
    int page = Constants.INITIAL_PAGE;
    public LiveData<Resource<List<CarsList>>> getCars(){
        return repository.getItems(page++);
    }

    void reset(){
        page = Constants.INITIAL_PAGE;
    }
}
