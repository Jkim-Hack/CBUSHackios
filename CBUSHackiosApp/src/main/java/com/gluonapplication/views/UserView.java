package com.gluonapplication.views;

import com.gluonapplication.GluonApplication;
import com.gluonapplication.UserP;
import com.gluonhq.charm.down.Services;
import com.gluonhq.charm.down.plugins.PicturesService;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.control.Avatar;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class UserView extends View{

    private FileInputStream serviceAccount;
    private FirebaseOptions options;

        public UserView(String name){
            super(name);

            Avatar avat = new Avatar();

            avat.setRadius(20);


            TextField emailPut = new TextField();
            emailPut.setPromptText("Enter Email Address");

            TextField pwPut = new TextField();
            pwPut.setPromptText("Enter Password");

            Button avatar = new Button("Upload a profile image");
            Button takePic = new Button("Take a picture");

            avatar.setOnAction((ActionEvent e) -> {
                ImageView imageView = new ImageView();
                Services.get(PicturesService.class).ifPresent(service -> {
                    service.takePhoto(false).ifPresent(image -> imageView.setImage(image));
                });

                avat.setImage(imageView.getImage());


            });

            takePic.setOnAction((ActionEvent e) -> {
                ImageView imageView = new ImageView();
                Services.get(PicturesService.class).ifPresent(service -> {
                    service.loadImageFromGallery().ifPresent(image -> imageView.setImage(image));
                });

                avat.setImage(imageView.getImage());

            });


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


            UserP newUser = new UserP(email.getText(), pw.getText()); //Makes new User object
            userRef.child(newUser.getUsername()).setValueAsync(newUser); //Sets values in the child



        }





        @Override
        protected void updateAppBar(AppBar appBar) {
            appBar.setNavIcon(MaterialDesignIcon.MENU.button(e -> MobileApplication.getInstance().showLayer(GluonApplication.MENU_LAYER)));
            appBar.setTitleText("\t\t Regsiter");

        }






}
