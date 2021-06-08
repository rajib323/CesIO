package com.google.cesio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.cesio.Adapter.NewsAdapter;
import com.google.cesio.Model.Article;
import com.google.cesio.Model.Results;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    NewsAdapter adapter;
    SwipeRefreshLayout srp;
    ImageButton sett;
    EditText srch_bar;
    final String apiKey= "570c273afcd64c548822b19634387d92";
    List<Article> mArt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mArt=new ArrayList<>();
        srch_bar=findViewById(R.id.search_bar);
        recyclerView=findViewById(R.id.list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        sett=findViewById(R.id.settings);
        srp=findViewById(R.id.swipe);

        retrieveJson("","in",apiKey);

        srp.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(!srch_bar.getText().toString().equals(""))
                    retrieveJson(srch_bar.getText().toString(),"in",apiKey);
                else
                    retrieveJson("","in",apiKey);
            }
        });

        srch_bar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                retrieveJson(s.toString(),"in",apiKey);
            }
        });

        sett.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Settings",Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void retrieveJson(String q,String in, String apiKey) {

        Call<Results> call;
        if(q.equals(""))
            call=ApiClient.getInstance().getApi().getResults(in,apiKey);
        else
            call=ApiClient.getInstance().getApi().getQuery(q,apiKey);


        call.enqueue(new Callback<Results>() {
            @Override
            public void onResponse(Call<Results> call, Response<Results> response) {
                if(response.isSuccessful()&&response.body().getStatus().equals("ok"))
                {
                    srp.setRefreshing(false);
                    mArt.clear();
                    mArt=response.body().getArticles();
                    adapter=new NewsAdapter(MainActivity.this,mArt);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<Results> call, Throwable t) {
                srp.setRefreshing(false);
                Toast.makeText(MainActivity.this, "No connectivity", Toast.LENGTH_SHORT).show();
            }
        });
    }
}