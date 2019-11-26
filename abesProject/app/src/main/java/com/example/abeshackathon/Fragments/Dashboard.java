package com.example.abeshackathon.Fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaActionSound;
import android.media.MediaPlayer;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.abeshackathon.PanicActivity;
import com.example.abeshackathon.R;
import com.example.abeshackathon.Receiveddata.Loginresponse;
import com.example.abeshackathon.SearchClinic;
import com.example.abeshackathon.Searchdoc;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import static android.content.Context.MODE_PRIVATE;

public class Dashboard extends Fragment {
    Gson gson=new Gson();

    public Dashboard() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View parentView = inflater.inflate(R.layout.fragment_dashboard, container, false);
        TextView remarks=parentView.findViewById(R.id.remarks);
        Button button=parentView.findViewById(R.id.panicb);
        TextView fromdate=parentView.findViewById(R.id.lastdate);
        CardView doctors=parentView.findViewById(R.id.doctor);
        CardView clinic=parentView.findViewById(R.id.clinic);



        TextView nextdate=parentView.findViewById(R.id.nextdate);
       SharedPreferences sharedPreferences = getActivity().getSharedPreferences("data", MODE_PRIVATE);
       String string=sharedPreferences.getString("logindata","");
        Log.e("datacheck",string);
       Type type=new TypeToken<Loginresponse>() {
       }.getType();
       Loginresponse loginresponse=gson.fromJson(string,type);
       remarks.setText(loginresponse.getRemark());
       fromdate.setText(loginresponse.getFromdate());
       nextdate.setText(loginresponse.getNextdate());
       button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
//               ToneGenerator toneGen1 = new ToneGenerator(AudioManager.STREAM_MUSIC, 100);
//               toneGen1.startTone(ToneGenerator.TONE_CDMA_ONE_MIN_BEEP,15000);
//               final MediaPlayer mp = MediaPlayer.create(getContext(),R.raw.beep);
//               mp.start();

               Intent intent=new Intent(getActivity(), PanicActivity.class);
               startActivity(intent);
           }
       });

       doctors.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent=new Intent(getActivity(), Searchdoc.class);
               startActivity(intent);
           }
       });

       clinic.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent=new Intent(getActivity(), SearchClinic.class);
               startActivity(intent);
           }
       });
       return parentView;

    }

}
