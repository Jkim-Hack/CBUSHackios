package com.gluonapplication;


import javafx.scene.image.Image;

public class UserP {



    private String Username;
    private String Password;
    private String encodedImage;
    private int counter;
    private boolean FindingMatch;

    public UserP(String name , String pw, String encodedImg, boolean findingMatch){
        Username = name;
        Password = pw;
        encodedImage = encodedImg;
        FindingMatch = findingMatch;
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

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public boolean isFindingMatch() {
        return FindingMatch;
    }

    public void setFindingMatch(boolean findingMatch) {
        FindingMatch = findingMatch;
    }
}