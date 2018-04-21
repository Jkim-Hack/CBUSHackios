package com.gluonapplication.views;

import com.gluonhq.charm.glisten.mvc.View;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ChatRoom extends View {





    public ChatRoom(String name) {
        super(name);



        DatabaseReference ref = FirebaseDatabase.getInstance()
                .getReference("Chatroom1");

        DatabaseReference userRef = ref;


        TextField input = new TextField();
        input.setPromptText("Message");

        Button send = new Button("Send");

        send.setOnAction((ActionEvent e) -> {

            ref.setValueAsync(input.getText());

        });





    }





}
