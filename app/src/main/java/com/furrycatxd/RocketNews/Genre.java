package com.furrycatxd.RocketNews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ActionBarContextView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.WindowManager;
import android.widget.ImageView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;

public class Genre extends AppCompatActivity {
    int back_press_count=0;
    protected View content;
    MaterialButton button;
    ConstraintLayout constraintLayoutGenre;
    public void openMainActivity(View view){
        button.performHapticFeedback(HapticFeedbackConstants.CLOCK_TICK);
        constraintLayoutGenre.animate().translationY(-1000f).setDuration(500).start();
        button.setAlpha(0f);
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
        finish();
        finishAndRemoveTask();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.genre);
        content=findViewById(android.R.id.content);
        constraintLayoutGenre=findViewById(R.id.constraintLayoutGenre);
        constraintLayoutGenre.clearAnimation();
        button=findViewById(R.id.openrocket);
        button.setAlpha(1f);
    }
    @Override
    public void onBackPressed() {
        back_press_count++;
        if(back_press_count%2==0){
            finish();
        }
        else{
            Snackbar.make(content,"Press again to exit",Snackbar.LENGTH_SHORT).show();
        }

    }
}