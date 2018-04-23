package com.gluonapplication;

import com.gluonapplication.views.SecondaryView;
import javafx.scene.image.Image;

import java.io.ByteArrayInputStream;
import java.sql.Timestamp;
import java.util.Base64;
import java.util.Date;

public class ChatMessage {

    public String messageText;
    public String messageUser;
    public long messageTime;
    public String profilepicture;

    public ChatMessage(String messageText, String messageUser, String profpic) {
        this.messageText = messageText;
        this.messageUser = messageUser;
        profilepicture = profpic;
        // Initialize to current time
        messageTime = new Timestamp(System.currentTimeMillis()).getTime();
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getMessageUser() {
        return messageUser;
    }

    public void setMessageUser(String messageUser) {
        this.messageUser = messageUser;
    }

    public long getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(long messageTime) {
        this.messageTime = messageTime;
    }

    public Image getUserImage(){

        Image img = null;

        try {
            byte[] barr = Base64.getDecoder().decode(profilepicture);
            img = new Image(new ByteArrayInputStream(barr));
        } catch (Exception e){
            System.out.println("NOOOOO");
        }


        return img;
    }

}
