package com.example.hp.git_repositery;

import android.media.Image;

import java.util.ArrayList;

public class UserDetail {

    String avatar_url;
    String login;




    int followers;
    int following;
    int public_repos;

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

}
