package com.furrycatxd.RocketNews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.shobhitpuri.custombuttons.GoogleSignInButton;

import static android.content.ContentValues.TAG;

public class MAuthentication extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    int RC_SIGN_IN=0;
    String database_email;
    TextView textView;
    ConstraintLayout constraintLayout;


    GoogleSignInClient mGoogleSignInClient;
    LottieAnimationView lottieAnimationView;
    SignInButton signInButton;

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent,RC_SIGN_IN);
        //overridePendingTransition(R.anim.slide_in_up,R.anim.slide_out_up);
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            constraintLayout.animate().translationY(-1000f).setDuration(500).start();

            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            // Signed in successfully, show authenticated UI.
            Intent intent=new Intent(this,Genre.class);
            account.getEmail();
            editor.putString("Email",account.getEmail());
            editor.apply();
            editor.commit();

            lottieAnimationView.pauseAnimation();
            lottieAnimationView.cancelAnimation();
            startActivity(intent);
            finish();
            finishAndRemoveTask();
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);

        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
//            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_NewsX);
        setContentView(R.layout.mauthentication);

        sharedPreferences =getSharedPreferences("EMAILPREFERENCES",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        database_email=sharedPreferences.getString("Email",null);

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        if(account!=null) {
            Intent intent;
            if (database_email != null) {
                intent = new Intent(getApplicationContext(), MainActivity.class);
            } else {
                intent = new Intent(getApplicationContext(), Genre.class);
            }
            startActivity(intent);
            finish();
            finishAndRemoveTask();
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
        }
        constraintLayout=findViewById(R.id.constraintLayout);
        signInButton=findViewById(R.id.signInButton);
        lottieAnimationView=findViewById(R.id.lottieAnimation);
        lottieAnimationView.playAnimation();
        signInButton.setOnClickListener(v -> {
            switch (v.getId()){
                case R.id.signInButton:
                    signIn();
                    break;
            }
        });

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(getApplicationContext(), gso);

    }



}