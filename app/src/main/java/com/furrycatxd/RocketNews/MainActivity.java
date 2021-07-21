package com.furrycatxd.RocketNews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    String API_KEY = "115e4b65479f43f29bbdb75f1d9047c0";
    Intent webIntent;
    RecyclerView recyclerView;
    List<Articles> articles=new ArrayList<>();
    Adapter adapter;

    public void goToGenre(View view) {
        view.performHapticFeedback(HapticFeedbackConstants.GESTURE_END);
        Intent intent = new Intent(this, Genre.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
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

        Call<News> call=ApiClient.getInstance().getApi().getNews(getCountry(),API_KEY);
        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                articles.clear();
                assert response.body() != null;
                articles=response.body().getArticles();
                adapter=new Adapter(MainActivity.this,articles);
                recyclerView.setAdapter(adapter);
            }
            @Override
            public void onFailure(Call<News> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Hello",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public String getCountry(){
        Locale locale=Locale.getDefault();
        String country=locale.getCountry();
        return country.toLowerCase();
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.mainActivityTheme);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        loadNews();
    }
}
