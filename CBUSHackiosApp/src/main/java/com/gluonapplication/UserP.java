package com.gluonapplication;


import javafx.scene.image.Image;

public class UserP {



    private String Username;
    private String Password;
    private Image user;

    public UserP(String name , String pw, Image userp){
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


    public Image getUser() {
        return user;
    }

    public void setUser(Image user) {
        this.user = user;
    }
}