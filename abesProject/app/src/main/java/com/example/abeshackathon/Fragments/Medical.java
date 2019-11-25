package com.example.abeshackathon.Fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.abeshackathon.Apiinterface.Medicalrequest;
//import com.example.abeshackathon.JsonBody.Medicaldata;
import com.example.abeshackathon.MedicalAdapter;
import com.example.abeshackathon.Receiveddata.Medicaldatarersponse;
import com.example.abeshackathon.R;
import com.example.abeshackathon.Receiveddata.Loginresponse;
import com.example.abeshackathon.Retro;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class Medical extends Fragment {
    RecyclerView recyclerView;

    Gson gson=new Gson();
    List<Medicaldatarersponse> medicaldata;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View parentView = inflater.inflate(R.layout.fragment_medical, container, false);
         recyclerView=parentView.findViewById(R.id.recyclerview_medical);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("data", MODE_PRIVATE);
        String string=sharedPreferences.getString("logindata","");
        Log.e("datacheck",string);
        Type type=new TypeToken<Loginresponse>() {
        }.getType();
        Loginresponse loginresponse=gson.fromJson(string,type);
        Medicalrequest medicalrequest= Retro.createService(Medicalrequest.class);
        Call<List<Medicaldatarersponse>> call=medicalrequest.requestresponse(loginresponse.getId());
        call.enqueue(new Callback<List<Medicaldatarersponse>>() {
            @Override
            public void onResponse(Call<List<Medicaldatarersponse>> call, Response<List<Medicaldatarersponse>> response) {
                 medicaldata=response.body();
                Log.e("medicaldta",gson.toJson(medicaldata));
                LinearLayoutManager llm=new LinearLayoutManager(getActivity());
                llm.setOrientation(RecyclerView.VERTICAL);
                recyclerView.setLayoutManager(llm);
                MedicalAdapter medicalAdapter=new MedicalAdapter(medicaldata,getContext());
                recyclerView.setAdapter(medicalAdapter);
            }

            @Override
            public void onFailure(Call<List<Medicaldatarersponse>> call, Throwable t) {

            }
        });

        return parentView;
    }

}
