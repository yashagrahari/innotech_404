package com.example.abeshackathon.Apiinterface;

import com.example.abeshackathon.Receiveddata.Medicaldatarersponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Medicalrequest {

    @GET("health/show/")
    Call<List<Medicaldatarersponse>> requestresponse(@Query("id") String id);
}
