package com.example.hp.git_repositery;

import android.widget.EditText;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GitService {
   @GET("/search/users")
    Call<UserResponse> findUsername(@Query("q") String login);
    @GET("/users/{username}")
    Call<Users>findUsers(@Path("username")String username);
    @GET("/users/{username}/repos")
    Call<repoarray>getRepositery(@Path("username")String username);
}
