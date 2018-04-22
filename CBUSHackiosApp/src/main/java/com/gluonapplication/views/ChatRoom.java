package com.gluonapplication.views;

import com.gluonapplication.ChatMessage;
import com.gluonhq.charm.glisten.control.CharmListView;
import com.gluonhq.charm.glisten.mvc.View;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class ChatRoom extends View {

    private FileInputStream serviceAccount;
    private FirebaseOptions options;

    public ChatRoom(String name) {
        super(name);


        DatabaseReference ref = FirebaseDatabase.getInstance()
                .getReference("Chatroom");

        DatabaseReference userRef = ref;


        TextField input = new TextField();
        input.setPromptText("Message");


        Button send = new Button("Send");

        send.setOnAction((ActionEvent e) -> {

            ref.push().setValueAsync(new ChatMessage(input.getText(), SecondaryView.emailL));
            input.setText("");


        });

        CharmListView<String, String> charmlist = new CharmListView<>();

        userRef.child("jkim").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String IDref = dataSnapshot.getValue(String.class);
                ObservableList<String> message = FXCollections.observableArrayList();
                message.add(IDref);
                charmlist.setItems(message);



            }

            @Override
            public void onCancelled(DatabaseError error) {
                System.out.println("nooooo");
            }


        });





    }


    private String sendMessage(TextArea message){



    return null;

    }


}
