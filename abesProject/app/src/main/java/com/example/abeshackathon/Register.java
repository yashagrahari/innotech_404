package com.example.abeshackathon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        WebView browser = (WebView) findViewById(R.id.webview);
        browser.loadUrl("https://www.nhs.uk/Service-Search/Hospital/LocationSearch/7");

    }
}
