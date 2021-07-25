package com.furrycatxd.RocketNews;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;


public class WebActivity extends AppCompatActivity {
    WebView webView;
    Intent intent;
    TextView sourceTextView;
    TextView topurldisplay;
    String newsurl;

    public void endWebActivity(View view){
        finish();
    }

    public void shareLink(View view){
        Intent shareLinkIntent=new Intent();
        shareLinkIntent.setAction(Intent.ACTION_SEND);
        shareLinkIntent.putExtra(Intent.EXTRA_TEXT,newsurl);
        shareLinkIntent.setType("text/plane");
        startActivity(shareLinkIntent);
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
        finish();
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