package com.essam.carstask.data.api;

import androidx.lifecycle.MutableLiveData;

import com.essam.carstask.data.api.apiResponseDataClasses.CarsList;
import com.essam.carstask.data.api.apiResponseDataClasses.Root;
import com.essam.carstask.utls.Constants;
import com.essam.carstask.utls.Resource;

import java.net.HttpURLConnection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIService {

    public MutableLiveData<Resource<List<CarsList>>> loadData(int page) {
        MutableLiveData<Resource<List<CarsList>>> carsList = new MutableLiveData<Resource<List<CarsList>>>();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitCall retrofitCall = retrofit.create(RetrofitCall.class);
        Call<Root> call = retrofitCall.getItems(page);
        call.enqueue(new Callback<Root>() {

            @Override
            public void onFailure(Call<Root> call, Throwable t) {
                carsList.postValue(new Resource().error(t));
            }

            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {

                Resource<List<CarsList>> resource = new Resource() ;
                if (response.code() == HttpURLConnection.HTTP_OK){
                    carsList.postValue(resource.success(response.body().getCars()));
                }
            }
        });
        return carsList;
    }
}
