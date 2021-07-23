package com.furrycatxd.RocketNews;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;
import java.util.HashMap;


public class WebActivity extends AppCompatActivity {
    WebView webView;
    Intent intent;
    TextView sourceTextView;
    TextView topurldisplay;
    String newsurl;

    public void goToMainActivity(View view){
        intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    public String getWebSiteFromIntent(){
        intent=getIntent();
        return intent.getStringExtra("NEWSURL");
    }
    public String getSourceFromIntent(){
        intent=getIntent();
        return intent.getStringExtra("NEWSSOURCE");
    }
    public void loadWebSite(String newsurl){
        webView.loadUrl(newsurl);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        webView=findViewById(R.id.webView);
        sourceTextView=findViewById(R.id.sourceTextView);
        sourceTextView.setText(getSourceFromIntent());
        newsurl=getWebSiteFromIntent();
        loadWebSite(newsurl);


    }
}