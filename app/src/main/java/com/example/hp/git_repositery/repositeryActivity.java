package com.example.hp.git_repositery;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class repositeryActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<repo> repoArrayList;
    ProgressBar progressBar;
    ArrayAdapter adapter;
  // Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repositery);
        listView = findViewById(R.id.list_item1);
        repoArrayList = new ArrayList<>();
       // button = findViewById(R.id.button10);
        adapter = new repoAdapter(this,repoArrayList);
        listView.setAdapter(adapter);

        progressBar = findViewById(R.id.progressbar10);
        Intent intent = getIntent();
       final String username = intent.getStringExtra("username");
       listView.setVisibility(View.INVISIBLE);
       progressBar.setVisibility(View.VISIBLE);
                   Retrofit.Builder builder = new Retrofit.Builder()
                           .baseUrl("https://api.github.com")
                           .addConverterFactory(GsonConverterFactory.create());
                   Retrofit retrofit = builder.build();

                   GitService service = retrofit.create(GitService.class);
                   Call<ArrayList<repo>> call = service.getRepositery(username);
                   call.enqueue(new Callback<ArrayList<repo>>() {
                       @Override
                       public void onResponse(Call<ArrayList<repo>> call, Response<ArrayList<repo>> response) {
                           repoArrayList.clear();
                           repoArrayList.addAll(response.body());
                           adapter.notifyDataSetChanged();
                           listView.setVisibility(View.VISIBLE);
                           progressBar.setVisibility(View.INVISIBLE);


                       }

                       @Override
                       public void onFailure(Call<ArrayList<repo>> call, Throwable t) {

                           Log.d("check",t.getMessage());

                       }
                   });
    }
}