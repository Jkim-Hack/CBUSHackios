package com.gluonapplication;

import com.gluonhq.charm.glisten.control.Avatar;

public class UserP {



    private String Username;
    private String Password;
    private Avatar user;

    public UserP(String name , String pw, Avatar userp){
        Username = name;
        Password = pw;
        user = userp;
    }

    public String getUsername() {
        return Username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public void setUsername(String username) {
        Username = username;
    }


    public Avatar getUser() {
        return user;
    }

    public void setUser(Avatar user) {
        this.user = user;
    }
}