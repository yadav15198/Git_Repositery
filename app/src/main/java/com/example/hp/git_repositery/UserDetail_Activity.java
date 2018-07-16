package com.example.hp.git_repositery;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserDetail_Activity extends AppCompatActivity implements View.OnClickListener {
    TextView username;
    Button repositery;
    Button Followers;
    Button follow;
    ImageView userImage;
    Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .addConverterFactory(GsonConverterFactory.create());
    Retrofit retrofit = builder.build();
    GitService gitService = retrofit.create(GitService.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userdetail);
        username = findViewById(R.id.username);
        userImage = findViewById(R.id.userImage);
        Followers = findViewById(R.id.followerbutton);
        follow = findViewById(R.id.followingbutton);
        repositery = findViewById(R.id.repositery);
        Intent intent1 = getIntent();
        String user = intent1.getStringExtra("login");
        username.setText(user);
        }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.followerbutton) {
            Retrofit.Builder builder = new Retrofit.Builder()
                    .baseUrl("https://api.github.com")
                    .addConverterFactory(GsonConverterFactory.create());
            Retrofit retrofit = builder.build();
            GitService gitService = retrofit.create(GitService.class);
            Call<Integer>call = gitService.getnoOfFollower();
            call.enqueue(new Callback<Integer>() {
                @Override
                public void onResponse(Call<Integer> call, Response<Integer> response) {
                    Followers.setText("");
                    Followers.setText(response.body());
                }

                @Override
                public void onFailure(Call<Integer> call, Throwable t) {

                }
            });
        }
        else if(id == R.id.repositery){
            Retrofit.Builder builder = new Retrofit.Builder()
                    .baseUrl("https://api.github.com")
                    .addConverterFactory(GsonConverterFactory.create());
            Retrofit retrofit = builder.build();
            GitService gitService = retrofit.create(GitService.class);
            Call<Integer>call = gitService.getnoOfFollower();
            call.enqueue(new Callback<Integer>() {
                @Override
                public void onResponse(Call<Integer> call, Response<Integer> response) {
                    repositery.setText("");
                    repositery.setText(response.body());
                }

                @Override
                public void onFailure(Call<Integer> call, Throwable t) {

                }
            });
        }
        else if( id == R.id.followingbutton){
            Retrofit.Builder builder = new Retrofit.Builder()
                    .baseUrl("https://api.github.com")
                    .addConverterFactory(GsonConverterFactory.create());
            Retrofit retrofit = builder.build();
            GitService gitService = retrofit.create(GitService.class);
            Call<Integer>call = gitService.getnoOfFollower();
            call.enqueue(new Callback<Integer>() {
                @Override
                public void onResponse(Call<Integer> call, Response<Integer> response) {
                    follow.setText("");
                    follow.setText(response.body());
                }

                @Override
                public void onFailure(Call<Integer> call, Throwable t) {

                }
            });
        }
        }
    }



