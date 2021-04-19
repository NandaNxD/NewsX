package com.furrycatxd.RocketNews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    String api_key="115e4b65479f43f29bbdb75f1d9047c0";
    View content;
    int back_press_count=0;
    public void goToGenre(View view){
        Intent intent=new Intent(this,Genre.class);
        startActivity(intent);
        finish();
    }
    @Override
    public void onBackPressed() {
        back_press_count++;
        if (back_press_count % 2 == 0) {
            finish();
        } else {
            Snackbar.make(content, "Press again to exit", Snackbar.LENGTH_SHORT).show();
        }
    }
        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.mainActivityTheme);
        setContentView(R.layout.activity_main);
        content=findViewById(android.R.id.content);
    }
}