package com.example.abeshackathon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class Searchdoc extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchdoc);

        WebView browser = (WebView) findViewById(R.id.webview);

        browser.loadUrl("https://www.sharecare.com/find-a-doctor");


    }
}
