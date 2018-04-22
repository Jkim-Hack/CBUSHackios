package com.gluonapplication.views;

import com.gluonapplication.ChatMessage;
import com.gluonhq.charm.glisten.control.Avatar;
import com.gluonhq.charm.glisten.control.CharmListCell;
import com.gluonhq.charm.glisten.control.CharmListView;
import com.gluonhq.charm.glisten.control.ListTile;
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

        userRef.orderByChild("messageTime").limitToLast(1).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                System.out.println(dataSnapshot.getKey());
                ObservableList<String> message = FXCollections.observableArrayList();
                message.add(dataSnapshot.child("messageText").getKey());


                charmlist.setHeaderCellFactory(p -> new CharmListCell<String>() {

                    private final ListTile tile = new ListTile();

                    {
                        Avatar avatar = new Avatar(16);
                        tile.setPrimaryGraphic(avatar);
                        setText(null);
                    }

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null && !empty) {
                            tile.textProperty().setAll("Density", charmlist.toString(item));
                            setGraphic(tile);
                        } else {
                            setGraphic(null);
                        }
                    }

                });

                charmlist.setItems(message);
            }

            @Override
            public void onChildChanged(DataSnapshot snapshot, String previousChildName) {

            }

            @Override
            public void onChildRemoved(DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot snapshot, String previousChildName) {

            }

            @Override
            public void onCancelled(DatabaseError error) {

            }

        });






    }


    private String sendMessage(TextArea message){



    return null;

    }


}
