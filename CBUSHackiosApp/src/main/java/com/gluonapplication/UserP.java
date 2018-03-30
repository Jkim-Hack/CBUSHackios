package com.gluonapplication;

public class UserP {



    private String Username;
    private String Password;

    public UserP(String name , String pw){
        Username = name;
        Password = pw;
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


}