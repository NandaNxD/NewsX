package com.furrycatxd.RocketNews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.TypedValue;
import android.widget.LinearLayout.LayoutParams;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    String api_key="115e4b65479f43f29bbdb75f1d9047c0";
    View content;
    int back_press_count=0;
    CardView cardView;
    Intent webIntent;
    Intent backPressed;
    LinearLayout linearLayout;

    public void goToGenre(View view){
        view.performHapticFeedback(HapticFeedbackConstants.GESTURE_END);
        Intent intent=new Intent(this,Genre.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_up,R.anim.slide_out_up);
        //finish();
    }

    // For the button
    public void goToWebActivity(View view){
        webIntent=new Intent(this,WebActivity.class);
        startActivity(webIntent);
    }

    // For the usual stuff
    public void goToWebActivity(){
        webIntent=new Intent(this,WebActivity.class);
        startActivity(webIntent);
    }

    public void loadNews(){
        // Fetch Data from internet
        // Change returntype
    }

    public void sendNewsWebsite(String urlx){
        webIntent.putExtra("NEWSURL",urlx);
        goToWebActivity();
    }

    public void setListView(){
        // Set listView for news
    }

    public void refreshListView(){
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
        content=findViewById(android.R.id.content);
        linearLayout=findViewById(R.id.linearLayout);

        CardView card = new CardView(getApplicationContext()); // Set the CardView layoutParams
        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        linearLayout.setDividerPadding(20);
        for(int i=0;i<=10;i++){
            CardView cardView=new CardView(this);
            TextView textView=new TextView(this);
            cardView.setRadius(10F);
            textView.setText("Hello");
            cardView.addView(textView);
            linearLayout.addView(cardView);
        }



    }
}