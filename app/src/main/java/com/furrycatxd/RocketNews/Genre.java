package com.furrycatxd.RocketNews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ActionBarContextView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;

import com.google.android.material.snackbar.Snackbar;

public class Genre extends AppCompatActivity {
    int backpresscount=0;
    protected View content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.genre);
        content=findViewById(android.R.id.content);
    }
    @Override
    public void onBackPressed() {
        backpresscount++;
        if(backpresscount%2==0){
            finish();
        }
        else{
            Snackbar.make(content,"Press again to exit",Snackbar.LENGTH_SHORT).show();
        }

    }
}