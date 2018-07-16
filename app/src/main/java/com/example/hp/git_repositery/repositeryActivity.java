package com.example.hp.git_repositery;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class repositeryActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<repo> repoArrayList;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repositery);
        listView = findViewById(R.id.list_item);
        repoArrayList = new ArrayList<>();
        progressBar = findViewById(R.id.progressbar);
    }
    Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .addConverterFactory(GsonConverterFactory.create());
    Retrofit retrofit = builder.build();

    GitService service =retrofit.create(GitService.class);
   //  Call<ArrayList<repo>>call = service.getRepos();

}
