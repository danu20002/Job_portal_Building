package com.daneshnaik.jobsearchapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class portal_link_opner extends AppCompatActivity {
WebView mywebview;
ProgressBar progressbar_web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portal_link_opner);
        mywebview =findViewById(R.id.webview);
        progressbar_web=findViewById(R.id.progressbar_web);
        progressbar_web.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=getIntent();
                String links=intent.getStringExtra("link");
                mywebview.loadUrl(links);
                WebSettings settings=mywebview.getSettings();
                settings.setJavaScriptEnabled(true);
                mywebview.setWebViewClient(new WebViewClient());
                progressbar_web.setVisibility(View.INVISIBLE);
            }
        },2000);

    }
}