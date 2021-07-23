package com.furrycatxd.RocketNews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.HapticFeedbackConstants;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;


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
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            // Signed in successfully, show authenticated UI.
            Intent intent=new Intent(this,MainActivity.class);
            account.getEmail();
            editor.putString("Email",account.getEmail());
            editor.apply();
            editor.commit();

            lottieAnimationView.pauseAnimation();
            lottieAnimationView.cancelAnimation();
            startActivity(intent);
            //constraintLayout.animate().translationY(-1000f).setDuration(500).start();
            overridePendingTransition(R.anim.slide_in_up,R.anim.slide_out_up);

            finish();
            finishAndRemoveTask();
            //overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);

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
            intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
            finishAndRemoveTask();
            //overridePendingTransition(R.anim.slide_in_up,R.anim.slide_out_up);
        }
        constraintLayout=findViewById(R.id.constraintLayout);
        signInButton=findViewById(R.id.signInButton);
        lottieAnimationView=findViewById(R.id.lottieAnimation);
        lottieAnimationView.playAnimation();
        signInButton.setOnClickListener(v -> {
            signInButton.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS);
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