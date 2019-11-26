package com.example.abeshackathon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

import com.example.abeshackathon.R;

public class SearchClinic extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_clinic);

        WebView browser = (WebView) findViewById(R.id.webview);

        browser.loadUrl("https://www.nhs.uk/Service-Search/Hospital/LocationSearch/7");
    }
}
