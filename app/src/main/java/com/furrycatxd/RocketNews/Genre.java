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
import android.widget.Button;
import android.widget.ImageView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Set;

public class Genre extends AppCompatActivity {
    int back_press_count=0;
    protected View content;
    MaterialButton button;
    ConstraintLayout constraintLayoutGenre;
    Button[] genre;
    ArrayList<String> genreList;

    public void openMainActivity(View view){
        button.performHapticFeedback(HapticFeedbackConstants.CLOCK_TICK);
        constraintLayoutGenre.animate().translationY(-1000f).setDuration(500).start();
        button.setAlpha(0f);
        Intent intent=new Intent(this,MainActivity.class);

        String[] genreArray =new String[genreList.size()];
        for(int i=0;i<genreList.size();i++){
            genreArray[i]=genreList.get(i);
        }
        intent.putExtra("GenreArray",genreArray);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_out_up,R.anim.slide_in_up);
        //overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
        //finish();
        //finishAndRemoveTask();
    }


    public int genreExistsInGenreList(String genreKeyWord){
        int genreListSize=genreList.size();
        for(int i=0;i<genreListSize;i++){
            if(genreList.get(i).equals(genreKeyWord)){
                return i;
            }
        }
        return -1;
    }

    public void saveGenre(View view){
        int existsG;
        if(R.id.politics==view.getId()){
            existsG=genreExistsInGenreList("Politics");
            if(existsG==-1){
                genreList.add("Politics");
            }
            else{
                genreList.remove("Politics");
            }

        }
        else if(R.id.science==view.getId()){
            existsG=genreExistsInGenreList("Science");
            if(existsG==-1){
                genreList.add("Science");
            }
            else{
                genreList.remove("Science");
            }

        }
        else if(R.id.space==view.getId()){
            existsG=genreExistsInGenreList("Space");
            if(existsG==-1){
                genreList.add("Space");
            }
            else{
                genreList.remove("Space");
            }
        }
        else if(R.id.sports==view.getId()){
            existsG=genreExistsInGenreList("Sports");
            if(existsG==-1){
                genreList.add("Sports");
            }
            else{
                genreList.remove("Sports");
            }
        }
        else if(R.id.football==view.getId()){
            existsG=genreExistsInGenreList("Football");
            if(existsG==-1){
                genreList.add("Football");
            }
            else{
                genreList.remove("Football");
            }
        }
        else if(R.id.business==view.getId()){
            existsG=genreExistsInGenreList("Business");
            if(existsG==-1){
                genreList.add("Business");
            }
            else{
                genreList.remove("Business");
            }
        }
        else if(R.id.cricket==view.getId()){
            existsG=genreExistsInGenreList("Cricket");
            if(existsG==-1){
                genreList.add("Cricket");
            }
            else{
                genreList.remove("Cricket");
            }
        }
        else if(R.id.medical==view.getId()){
            existsG=genreExistsInGenreList("Medical");
            if(existsG==-1){
                genreList.add("Medical");
            }
            else{
                genreList.remove("Medical");
            }
        }
        else if(R.id.lifestyle==view.getId()){
            existsG=genreExistsInGenreList("Lifestyle");
            if(existsG==-1){
                genreList.add("Lifestyle");
            }
            else{
                genreList.remove("Lifestyle");
            }
        }
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
        genre=new Button[10];
        genreList=new ArrayList<String>();

        genre[0]=findViewById(R.id.politics);
        genre[1]=findViewById(R.id.science);
        genre[2]=findViewById(R.id.space);
        genre[3]=findViewById(R.id.sports);
        genre[4]=findViewById(R.id.football);
        genre[5]=findViewById(R.id.business);
        genre[6]=findViewById(R.id.cricket);
        genre[7]=findViewById(R.id.medical);
        genre[8]=findViewById(R.id.lifestyle);

    }

//    @Override
//    public void onBackPressed() {
//        back_press_count++;
//        if(back_press_count%2==0){
//            finish();
//        }
//        else{
//            Snackbar.make(content,"Press again to exit",Snackbar.LENGTH_SHORT).show();
//        }
//
//    }
}