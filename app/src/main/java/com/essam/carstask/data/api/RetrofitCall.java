package com.essam.carstask.data.api;

import com.essam.carstask.data.api.apiResponseDataClasses.Root;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitCall {
    @GET("cars")
    Call<Root> getItems(@Query("page") int page);
}
