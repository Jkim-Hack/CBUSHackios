package com.gluonapplication.views;

import com.gluonapplication.ChatMessage;
import com.gluonapplication.ChatMessageCell;
import com.gluonapplication.DrawerManager;
import com.gluonapplication.GluonApplication;
import com.gluonhq.charm.down.Services;
import com.gluonhq.charm.down.plugins.DialerService;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.*;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
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

        String image = encodeImage(DrawerManager.profpic);

        Button send = new Button("Send");

        send.setOnAction((ActionEvent e) -> {


            ChatMessage messageo = new ChatMessage(input.getText(), SecondaryView.emailL, image);


            ref.push().setValueAsync(messageo);
            input.setText("");


        });

        CharmListView<ChatMessage, Long> charmlist = new CharmListView<>();
        ObservableList<ChatMessage> message = FXCollections.observableArrayList();
        charmlist.setItems(message);
        charmlist.setCellFactory(p -> new ChatMessageCell());

        userRef.orderByChild("messageTime").limitToLast(1).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {


                ChatMessage mee = new ChatMessage(dataSnapshot.child("messageText").getValue(String.class), dataSnapshot.child("messageUser").getValue(String.class)
                        , dataSnapshot.child("profilepicture").getValue(String.class));

                message.add(mee);

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



        HBox box = new HBox();
        box.getChildren().addAll(input, send);
        box.setSpacing(10);
        box.setPadding(new Insets(20));
        setCenter(charmlist);
        setBottom(box);


    }

    @Override
    protected void updateAppBar(AppBar appBar) {
        appBar.setNavIcon(MaterialDesignIcon.MENU.button(e -> MobileApplication.getInstance().showLayer(GluonApplication.MENU_LAYER)));
        appBar.setTitleText("Chat Room");
        appBar.getActionItems().add(MaterialDesignIcon.PHONE.button(e -> { Services.get(DialerService.class).ifPresent(service -> {
            service.call("+16144205771");
        });}));

    }


    private String encodeImage(Image sample){
        BufferedImage bImage = SwingFXUtils.fromFXImage(sample, null);
        ByteArrayOutputStream s = new ByteArrayOutputStream();
        try {
            ImageIO.write(bImage, "png", s);
        } catch (Exception e){
            System.out.println("NOOOOOo");
        }
        byte[] res  = s.toByteArray();
        try {
            s.close();
        }catch (Exception e){
            System.out.println("NOOOOOl");
        }
        String encoded = java.util.Base64.getEncoder().encodeToString(res);
        return encoded;
    }



}