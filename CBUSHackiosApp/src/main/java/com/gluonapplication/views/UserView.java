package com.gluonapplication.views;

import com.gluonapplication.GluonApplication;
import com.gluonapplication.UserP;
import com.gluonhq.charm.down.Services;
import com.gluonhq.charm.down.plugins.PicturesService;
import com.gluonhq.charm.glisten.animation.FadeInTransition;
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
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import org.apache.commons.codec.binary.Base64;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;


public class UserView extends View{

    private FileInputStream serviceAccount;
    private FirebaseOptions options;
    public static String userName;
        public UserView(String name) {
            super(name);


            Rectangle topRect = new Rectangle(340.0, 70);

            VBox box1 = new VBox();



            Label label = new Label("Register");
            label.getStylesheets().add("/welcome.css");
            StackPane controls = new StackPane(topRect, label);
            topRect.setStyle("-fx-fill: #00ccff");
            label.setAlignment(Pos.TOP_CENTER);
            label.setPadding(new Insets(20));
            box1.getChildren().add(controls);
            setTop(box1);

            FadeInTransition fade = new FadeInTransition(label);
            fade.setRate(.75);
            fade.play();

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

            setCenter(box);
            FadeInTransition fade1 = new FadeInTransition(box);
            fade1.setRate(2.5);
            fade.setOnFinished((ActionEvent e) ->{fade1.play();});

            Image imag = new Image("download.png");

            ImageView sample = new ImageView();
            sample.setImage(imag);


            BufferedImage bImage = SwingFXUtils.fromFXImage(sample.getImage(), null);
            ByteArrayOutputStream s = new ByteArrayOutputStream();
            try {
                ImageIO.write(bImage, "png", s);
            } catch (Exception e){
                System.out.println("NOOOOO");
            }
            byte[] res  = s.toByteArray();
            try {
                s.close();
            }catch (Exception e){
                System.out.println("NOOOOO");
            }
            String encoded = java.util.Base64.getEncoder().encodeToString(res);


            register.setOnAction((ActionEvent e) -> {
                UserP newUser = new UserP(userPut.getText(), pwPut.getText(), encoded, 0, false);
                userRef.child(newUser.getUsername()).setValueAsync(newUser);
                MobileApplication.getInstance().switchView(GluonApplication.SECONDARY_VIEW);
                userName = userPut.getText();

            });


        }






        @Override
        protected void updateAppBar(AppBar appBar) {
            appBar.setVisible(false);
            appBar.setNavIcon(MaterialDesignIcon.MENU.button(e -> MobileApplication.getInstance().showLayer(GluonApplication.MENU_LAYER)));
            appBar.setTitleText("\t\t     Register");

        }



}
