package com.example.hp.git_repositery;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserDetail_Activity extends AppCompatActivity  {
    TextView username;
    TextView repositery;
    TextView Followers;
    TextView follow;
    ImageView userImage;
    Button button;
    TextView id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userdetail);
        username = findViewById(R.id.username);
        userImage = findViewById(R.id.userImage);
        Followers = findViewById(R.id.followerbutton);
        follow = findViewById(R.id.followingbutton);
        repositery = findViewById(R.id.repositery);
        id = findViewById(R.id.id);
        button = findViewById(R.id.button);
        Intent intent1 = getIntent();
       final String user = intent1.getStringExtra("login");
        String url = intent1.getStringExtra("avtar_url");
        Picasso.get().load(url).into(userImage);
        username.setText(user);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(UserDetail_Activity.this,repositeryActivity.class);
                intent2.putExtra("username",user);
                startActivity(intent2);
            }
        });

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        GitService gitService = retrofit.create(GitService.class);
        Call<Users> call = gitService.findUsers(user);
        call.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                 Users users = response.body();
                 Followers.setText(users.followers+"");
                 follow.setText(users.following+"");
                 repositery.setText(users.public_repos+"");
                 id.setText(users.id+"");
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {

            }
        });

    }
}
//           call.enqueue(new Callback<Integer>() {
//                @Override
//                public void onResponse(Call<Integer> call, Response<Integer> response) {
//                    Followers.setText("");
//                    Followers.setText(response.body());
//                }
//
//                @Override
//                public void onFailure(Call<Integer> call, Throwable t) {
//
//                }
//            });
//        }
//        else if(id == R.id.repositery){
//            Retrofit.Builder builder = new Retrofit.Builder()
//                    .baseUrl("https://api.github.com")
//                    .addConverterFactory(GsonConverterFactory.create());
//            Retrofit retrofit = builder.build();
//            GitService gitService = retrofit.create(GitService.class);
//            Call<Integer>call = gitService.getnoOfFollower();
//            call.enqueue(new Callback<Integer>() {
//                @Override
//                public void onResponse(Call<Integer> call, Response<Integer> response) {
//                    repositery.setText("");
//                    repositery.setText(response.body());
//                }
//
//                @Override
//                public void onFailure(Call<Integer> call, Throwable t) {
//
//                }
//            });

//        else if( id == R.id.followingbutton){
//            Retrofit.Builder builder = new Retrofit.Builder()
//                    .baseUrl("https://api.github.com")
//                    .addConverterFactory(GsonConverterFactory.create());
//            Retrofit retrofit = builder.build();
//            GitService gitService = retrofit.create(GitService.class);
//            Call<Integer>call = gitService.getnoOfFollower();
//            call.enqueue(new Callback<Integer>() {
//                @Override
//                public void onResponse(Call<Integer> call, Response<Integer> response) {
//                    follow.setText("");
//                    follow.setText(response.body());
//                }
//
//                @Override
//                public void onFailure(Call<Integer> call, Throwable t) {
//
//                }
//            });
//        }




