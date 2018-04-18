package com.gluonapplication.views;

import com.gluonapplication.GluonApplication;
import com.gluonapplication.UserP;
import com.gluonhq.charm.down.Services;
import com.gluonhq.charm.down.plugins.PicturesService;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.control.Avatar;
import com.gluonhq.charm.glisten.control.Dialog;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Storage;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import org.apache.commons.codec.binary.Base64;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class UserView extends View{

    private FileInputStream serviceAccount;
    private FirebaseOptions options;

        public UserView(String name){
            super(name);

            Avatar avat = new Avatar();

            Button profilepic = new Button("      Upload \n\t   or \nTake a picture");
            profilepic.getStylesheets().add("CircleButton.css");

            Button register = new Button("Register!");

            avat.setRadius(20);

            VBox box = new VBox();
            VBox inputs = new VBox();

            TextField userPut = new TextField();
            userPut.setPromptText("Enter Username");

            TextField pwPut = new TextField();
            pwPut.setPromptText("Enter Password");

            Button avatar = new Button("Choose from gallery");
            Button takePic = new Button("Take a picture");

            Dialog dialog = new Dialog();

            avatar.setOnAction((ActionEvent e) -> {
                ImageView imageView = new ImageView();
                Services.get(PicturesService.class).ifPresent(service -> {
                    service.takePhoto(false).ifPresent(image -> imageView.setImage(image));
                });

                avat.setImage(imageView.getImage());
                dialog.hide();

            });

            takePic.setOnAction((ActionEvent e) -> {
                ImageView imageView = new ImageView();
                Services.get(PicturesService.class).ifPresent(service -> {
                    service.loadImageFromGallery().ifPresent(image -> imageView.setImage(image));
                });

                avat.setImage(imageView.getImage());
                dialog.hide();

            });

            dialog.getButtons().addAll(avatar, takePic);
            profilepic.setOnAction((ActionEvent e) -> {dialog.showAndWait();});


            DatabaseReference ref = FirebaseDatabase.getInstance()
                    .getReference("Users");

            DatabaseReference userRef = ref;

            profilepic.setTranslateX(100);
            register.setTranslateX(100);

            inputs.getChildren().addAll(userPut, pwPut, register);
            inputs.setSpacing(30);
            inputs.setPadding(new Insets(20));

            box.getChildren().addAll(profilepic, inputs);
            box.setSpacing(30);
            box.setPadding(new Insets(20));

            Image imag = new Image("download.png");

            ImageView sample = new ImageView();
            sample.setImage(imag);




            register.setOnAction((ActionEvent e) -> {
                UserP newUser = new UserP(userPut.getText(), pwPut.getText(), imag);
                userRef.child(newUser.getUsername()).setValueAsync(newUser);

            });



            setCenter(box);

        }





        @Override
        protected void updateAppBar(AppBar appBar) {
            appBar.setNavIcon(MaterialDesignIcon.MENU.button(e -> MobileApplication.getInstance().showLayer(GluonApplication.MENU_LAYER)));
            appBar.setTitleText("\t\t     Register");

        }

        private static String encodeFileToBase64Binary(File file){
         String encodedfile = null;
            try {
                FileInputStream fileInputStreamReader = new FileInputStream(file);
                byte[] bytes = new byte[(int)file.length()];
                fileInputStreamReader.read(bytes);
                encodedfile = encodedfile = new String(Base64.encodeBase64(bytes), "UTF-8");
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return encodedfile;
        }




}
