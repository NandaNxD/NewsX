package com.furrycatxd.RocketNews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.widget.LinearLayout.LayoutParams;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.volley.toolbox.Volley;


public class MainActivity extends AppCompatActivity {
    String api_key = "115e4b65479f43f29bbdb75f1d9047c0";
    View content;
    int back_press_count = 0;
    CardView cardView;
    Intent webIntent;
    Intent backPressed;
    ScrollView scrollView;
    LinearLayout linearLayout;
    RecyclerView recyclerView;
    Volley volley;


    public void goToGenre(View view) {
        view.performHapticFeedback(HapticFeedbackConstants.GESTURE_END);
        Intent intent = new Intent(this, Genre.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
        //finish();
    }

    // For the button
    public void goToWebActivity(View view) {
        webIntent = new Intent(this, WebActivity.class);
        startActivity(webIntent);
    }

    // For the usual stuff
    public void goToWebActivity() {
        webIntent = new Intent(this, WebActivity.class);
        startActivity(webIntent);
    }

    public void loadNews() {
        // Fetch Data from internet
        // Change returntype

    }

    public void sendNewsWebsite(String urlx) {
        webIntent.putExtra("NEWSURL", urlx);
        goToWebActivity();
    }

    public void setListView() {
        // Set listView for news
    }

    public void refreshListView() {
        // refresh ListView for news app
    }

//    @Override
//    public void onBackPressed(){
//        back_press_count++;
//        if (back_press_count % 2 == 0) {
//            finish();
//        } else {
//            Snackbar.make(content, "Press again to exit", Snackbar.LENGTH_SHORT).show();
//        }
//    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.mainActivityTheme);
        setContentView(R.layout.activity_main);

        content = findViewById(android.R.id.content);
        linearLayout = findViewById(R.id.linearLayout);
        scrollView=findViewById(R.id.scrollView);
        LayoutParams wrap_contents=new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        LayoutParams innerCardLayout=new LayoutParams(LayoutParams.MATCH_PARENT,500);



        for(int i = 0; i <= 10; i++){
            CardView outerCard = new CardView(this);
            CardView innerCard= new CardView(this);
            TextView newslink = new TextView(this);
            TextView briefDescription=new TextView(this);
            LinearLayout innerLayout=new LinearLayout(this);

            briefDescription.setLayoutParams(wrap_contents);
            newslink.setLayoutParams(wrap_contents);

            outerCard.setRadius(50);
            innerCard.setRadius(50);

            innerCard.setBackgroundResource(R.drawable.btn_google_dark_disabled);
            innerCard.setLayoutParams(innerCardLayout);
            outerCard.setMinimumHeight(400);
            outerCard.setMinimumHeight(400);
            //innerCard.setMinimumWidth(400);

            newslink.setPadding(40,0,40,0);
            briefDescription.setPadding(40,0,40,0);

            newslink.setText("www.google.com");
            newslink.setTextColor(Color.BLUE);
            briefDescription.setText("The HighLights are ...");

            innerLayout.addView(innerCard);
            innerLayout.addView(newslink);
            innerLayout.addView(briefDescription);
            innerLayout.setOrientation(LinearLayout.VERTICAL);

            outerCard.addView(innerLayout);
            recyclerView.addView(outerCard);
            //linearLayout.addView(outerCard);
        }
    }
}