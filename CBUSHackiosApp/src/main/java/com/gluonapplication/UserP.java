package com.gluonapplication;


import javafx.scene.image.Image;

public class UserP {



    private String Username;
    private String Password;
    private String encodedImage;

    public UserP(String name , String pw, String encodedImg){
        Username = name;
        Password = pw;
        encodedImage = encodedImg;
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

    public String getEncodedImage() {
        return encodedImage;
    }

    public void setEncodedImage(String encodedImage) {
        this.encodedImage = encodedImage;
    }
}