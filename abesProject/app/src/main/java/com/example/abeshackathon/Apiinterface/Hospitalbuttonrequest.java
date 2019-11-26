package com.example.abeshackathon.Apiinterface;

import com.example.abeshackathon.JsonBody.WardData;
import com.example.abeshackathon.Receiveddata.Hospitalbuttonresponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Hospitalbuttonrequest {

    @POST("health/hospital/")
    Call<Hospitalbuttonresponse> requestresponse(@Body WardData wardData);

}
