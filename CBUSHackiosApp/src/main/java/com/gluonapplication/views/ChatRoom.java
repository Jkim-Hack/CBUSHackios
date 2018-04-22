package com.gluonapplication.views;

import com.gluonhq.charm.glisten.mvc.View;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.*;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ChatRoom extends View {

    private FileInputStream serviceAccount;
    private FirebaseOptions options;

    public ChatRoom(String name) {
        super(name);


        try {
            serviceAccount =
                    new FileInputStream("CBUSHackiosApp/src/main/cbushack-save-the-world-604e9-firebase-adminsdk-kvlkk-37abcc4355.json");
        } catch (FileNotFoundException e){
            System.out.println("Error1");
        }
        try {
            options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://cbushack-save-the-world-604e9.firebaseio.com")
                    .build();
        } catch (IOException e){
            System.out.println("Error2");
        }


        FirebaseApp.initializeApp(options);

        DatabaseReference ref = FirebaseDatabase.getInstance()
                .getReference("Users");

        DatabaseReference userRef = ref;


        TextField input = new TextField();
        input.setPromptText("Message");

        Button send = new Button("Send");

        send.setOnAction((ActionEvent e) -> {

            ref.setValueAsync(input.getText());

        });

        TextField lol = new TextField();


        userRef.child("jkim").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String IDref = dataSnapshot.child("password").getValue(String.class);

                lol.setText(IDref);
                setCenter(lol);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                System.out.println("nooooo");
            }


        });







    }





}
