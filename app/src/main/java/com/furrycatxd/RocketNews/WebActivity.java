package com.furrycatxd.RocketNews;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.webkit.WebView;
import java.util.HashMap;

public class WebActivity extends AppCompatActivity {
    WebView webView;
    Intent intent;
    String newsurl;

    public void goToMainActivity(View view){
        intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    public String getWebSiteFromIntent(){
        intent=getIntent();
        return intent.getStringExtra("NEWSURL");
    }
    public void loadWebSite(String newsurl){
        webView.loadUrl(newsurl);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        webView=findViewById(R.id.webView);
        //newsurl=getWebSiteFromIntent();
        newsurl="https://www.google.com";
        loadWebSite(newsurl);

    }
}