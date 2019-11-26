package com.example.abeshackathon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.abeshackathon.Apiinterface.Hospitalbuttonrequest;
import com.example.abeshackathon.Apiinterface.Hospitalrequest;
import com.example.abeshackathon.Apiinterface.Medicalrequest;
import com.example.abeshackathon.Apiinterface.Warnresetrequest;
import com.example.abeshackathon.Receiveddata.Hospitalbuttonresponse;
import com.example.abeshackathon.Receiveddata.Hospitaldataresponse;
import com.example.abeshackathon.Receiveddata.Medicaldatarersponse;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HospitalActivity extends AppCompatActivity {

    Gson gson=new Gson();
    Button reset,add;
    Spinner spinner;
   public  ViewPager viewPager;
    TabLayout indicator;
    String selectedSubjectId;
    String selectedsubject;
    List<Integer> image;
    List<Hospitaldataresponse> hospitaldataresponses;
    List<String> c = new ArrayList<String>();
    List<String> d = new ArrayList<String>();

//    String[] spinnerContent = {};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital);
        Hospitalrequest hospitalrequest= Retro.createService(Hospitalrequest.class);
        spinner=findViewById(R.id.spinner);
        viewPager=(ViewPager)findViewById(R.id.viewPager);
        indicator=(TabLayout)findViewById(R.id.indicator);

        image=new ArrayList<>();
        image.add(R.drawable.h);
        image.add(R.drawable.hos);
        image.add(R.drawable.hosone);

        viewPager.setAdapter(new SliderAdapter(this, image));
        indicator.setupWithViewPager(viewPager,true);
//        Timer timer = new Timer();
//        timer.scheduleAtFixedRate(new SliderTimer(), 4000, 6000);



        Call<List<Hospitaldataresponse>> call=hospitalrequest.requestresponse();
        call.enqueue(new Callback<List<Hospitaldataresponse>>() {
            @Override
            public void onResponse(Call<List<Hospitaldataresponse>> call, Response<List<Hospitaldataresponse>> response) {
                 hospitaldataresponses=response.body();
                Log.e("medicaldta",gson.toJson(hospitaldataresponses));

                for (int i=0;i<hospitaldataresponses.size();i++){
                    String name=hospitaldataresponses.get(i).getName();
                    String id=hospitaldataresponses.get(i).getId();
                    c.add(name);
                    d.add(id);
                }

                ArrayAdapter<String> adapterC = new ArrayAdapter<String>(HospitalActivity.this, R.layout.support_simple_spinner_dropdown_item, c);
                adapterC.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                spinner.setAdapter(adapterC);

            }

            @Override
            public void onFailure(Call<List<Hospitaldataresponse>> call, Throwable t) {

            }
        });


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                selectedSubjectId = "" + d.get(i);
                selectedsubject = c.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        add=findViewById(R.id.addwarn);
        reset=findViewById(R.id.reset);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Hospitalbuttonrequest hospitalbuttonrequest= Retro.createService(Hospitalbuttonrequest.class);

                Call<Hospitalbuttonresponse> call=hospitalbuttonrequest.requestresponse(selectedSubjectId);
                call.enqueue(new Callback<Hospitalbuttonresponse>() {
                    @Override
                    public void onResponse(Call<Hospitalbuttonresponse> call, Response<Hospitalbuttonresponse> response) {
                      Hospitalbuttonresponse hospitaldataresponses=response.body();
                      Log.e("response",gson.toJson(hospitaldataresponses));
                    }

                    @Override
                    public void onFailure(Call<Hospitalbuttonresponse> call, Throwable t) {

                    }
                });

            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Warnresetrequest hospitalbuttonrequest= Retro.createService(Warnresetrequest.class);
                Call<Hospitalbuttonresponse> call=hospitalbuttonrequest.requestresponse("19");
                call.enqueue(new Callback<Hospitalbuttonresponse>() {
                    @Override
                    public void onResponse(Call<Hospitalbuttonresponse> call, Response<Hospitalbuttonresponse> response) {
                        Hospitalbuttonresponse hospitaldataresponses=response.body();
                        Log.e("response",gson.toJson(hospitaldataresponses));

                    }

                    @Override
                    public void onFailure(Call<Hospitalbuttonresponse> call, Throwable t) {

                    }
                });

            }
        });
    }


}


