package com.example.hp.git_repositery;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener  {
  ImageButton searchbutton;
  EditText searchEditText;
  ListView listView;
  ArrayList<UserDetail> UserArrayList ;
    ArrayAdapter adapter;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchEditText = findViewById(R.id.textView);
        searchbutton = findViewById(R.id.image);
        //imageView = findViewById(R.id.imageView4);
        listView = findViewById(R.id.list_item);
        searchbutton.setOnClickListener(this);
        progressBar  = findViewById(R.id.progressbar);
        UserArrayList = new ArrayList<>();
        adapter = new usernameAdapter(this,UserArrayList);
        listView.setAdapter(adapter);
        progressBar.setVisibility(View.INVISIBLE);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                UserDetail userDetail = UserArrayList.get(i);
                String login = userDetail.getLogin();
                String avtar_url = userDetail.getAvatar_url();
               Intent intent = new Intent(MainActivity.this,UserDetail_Activity.class);
               intent.putExtra("login",login);
               intent.putExtra("avtar_url",avtar_url);
               startActivity(intent);
            }
        });

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.main_menu,menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        if(id == R.id.like_menu){
//         Intent intent = new Intent();
//         intent.setAction(Intent.ACTION_VIEW);
//            Uri uri = Uri.parse("https://github.com/yadav15198");
//            intent.setData(uri);
//        }
//        return true;
//    }

    @Override
    public void onClick(View view) {
       // Toast.makeText(this, "Username can't be empty", Toast.LENGTH_SHORT).show();
        int id = view.getId();
        if(id == R.id.image) {
            String login = searchEditText.getText().toString();
            Retrofit.Builder builder = new Retrofit.Builder()
                    .baseUrl("https://api.github.com")
                    .addConverterFactory(GsonConverterFactory.create());
            Retrofit retrofit = builder.build();
            progressBar.setVisibility(View.VISIBLE);
            listView.setVisibility(View.INVISIBLE);
            GitService Service = retrofit.create(GitService.class);
                Log.d("login", login + " ");
                Call<UserResponse> call = Service.findUsername(login);
                call.enqueue(new Callback<UserResponse>() {
                    @Override
                    public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                        UserArrayList.clear();
                        UserResponse userResponse = response.body();
                        if(userResponse == null){
                            progressBar.setVisibility(View.INVISIBLE);
                            searchEditText.setError("username cant be null");
                        }
                        else {
                            UserArrayList.addAll(userResponse.items);
                            listView.setVisibility(View.VISIBLE);
                            adapter.notifyDataSetChanged();
                            Log.d("checkkkk", "lksdfmlksd");
                            progressBar.setVisibility(View.INVISIBLE);
                        }
                    }

                    @Override
                    public void onFailure(Call<UserResponse> call, Throwable t) {
                        Log.d("checkkkk", t.getMessage());
                        Log.d("checkkkk", t.getLocalizedMessage());
                    }
                });
            }
        }
    }


