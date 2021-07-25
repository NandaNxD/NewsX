package com.furrycatxd.RocketNews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.security.Key;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    String API_KEY = "115e4b65479f43f29bbdb75f1d9047c0";
    RecyclerView recyclerView;
    List<Articles> articles;
    EditText searchTextView;
    Adapter adapter;
    SwipeRefreshLayout swipeRefreshLayout;

    public void searchNews(View view){
        if(searchTextView.getText().length()==0){
            Toast.makeText(getApplicationContext(),"SearchBar Empty!!",Toast.LENGTH_SHORT).show();
        }
        else{
            Call<News> call=ApiClient.getInstance().getApi().getEverything(searchTextView.getText().toString(),API_KEY);
            call.enqueue(new Callback<News>() {
                @Override
                public void onResponse(Call<News> call, Response<News> response) {
                    Log.i("Response",(Boolean.toString(response.isSuccessful())));
                    if(response.isSuccessful() && response.body().getArticles()!=null){
                        articles.clear();
                        articles=response.body().getArticles();
                        Log.i("ArticlesSize",Integer.toString(articles.size()));
                        adapter=new Adapter(MainActivity.this,articles);
                        recyclerView.setAdapter(adapter);
                    }
                    else{
                        Log.i("ArticlesError","No articles");
                    }
                }
                @Override
                public void onFailure(Call<News> call, Throwable t) {
                    Toast.makeText(getApplicationContext(),"No Response",Toast.LENGTH_SHORT).show();
                }
            });
        }
        searchTextView.clearFocus();
    }

    public void searchNews(){
        if(searchTextView.getText().length()==0){
            Toast.makeText(getApplicationContext(),"SearchBar Empty!!",Toast.LENGTH_SHORT).show();
        }
        else{
            Call<News> call=ApiClient.getInstance().getApi().getEverything(searchTextView.getText().toString(),API_KEY);
            call.enqueue(new Callback<News>() {
                @Override
                public void onResponse(Call<News> call, Response<News> response) {
                    Log.i("Response",(Boolean.toString(response.isSuccessful())));
                    if(response.isSuccessful() && response.body().getArticles()!=null){
                        articles.clear();
                        articles=response.body().getArticles();
                        Log.i("ArticlesSize",Integer.toString(articles.size()));
                        adapter=new Adapter(MainActivity.this,articles);
                        recyclerView.setAdapter(adapter);
                    }
                    else{
                        Log.i("ArticlesError","No articles");
                    }
                }
                @Override
                public void onFailure(Call<News> call, Throwable t) {
                    Toast.makeText(getApplicationContext(),"No Response",Toast.LENGTH_SHORT).show();
                }
            });
        }
        searchTextView.clearFocus();
    }

    public void loadNews() {
        // Fetch Data from internet
        Call<News> call=ApiClient.getInstance().getApi().getNews("in",API_KEY);
        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                Log.i("Response",(Boolean.toString(response.isSuccessful())));
                if(response.isSuccessful() && response.body().getArticles()!=null){
                    articles.clear();
                    articles=response.body().getArticles();
                    Log.i("ArticlesSize",Integer.toString(articles.size()));
                    adapter=new Adapter(MainActivity.this,articles);
                    recyclerView.setAdapter(adapter);

                }
                else{
                    Log.i("ArticlesError","No articles");
                }
            }
            @Override
            public void onFailure(Call<News> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"No Response",Toast.LENGTH_SHORT).show();
            }
        });
        //hideKeyboard();
    }

    public void refreshNews() {
        // refresh ListView for news app

    }


    @Override
    public void onBackPressed() {
        if(!searchTextView.isFocused()){
            super.onBackPressed();
        }
        else{
            searchTextView.clearFocus();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.mainActivityTheme);
        setContentView(R.layout.activity_main);

        articles=new ArrayList<>();
        recyclerView=findViewById(R.id.recyclerView);
        searchTextView=findViewById(R.id.searchTextView);
        swipeRefreshLayout=findViewById(R.id.swipeRefresh);

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        loadNews();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                searchTextView.setText("Refreshed");
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        searchTextView.setOnEditorActionListener((v, actionId, event) -> {
            if(actionId==EditorInfo.IME_ACTION_SEND|| event.getAction() == event.ACTION_DOWN
                    || event.getAction() == KeyEvent.KEYCODE_ENTER){
                searchNews();
                return true;
            }
            else{
                return false;
            }

        });

    }
}
