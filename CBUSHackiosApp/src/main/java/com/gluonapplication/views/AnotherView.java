package com.gluonapplication.views;

import com.gluonapplication.UserP;
import com.gluonhq.charm.glisten.animation.FadeInTransition;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import com.gluonapplication.GluonApplication;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.*;
import javafx.animation.FillTransition;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class AnotherView extends View {

    private FileInputStream serviceAccount;
    private FirebaseOptions options;


    public AnotherView(String name) {
        super(name);

        //getStylesheets().add(SecondaryView.class.getResource("secondary.css").toExternalForm());

        try {
            serviceAccount =
                    new FileInputStream("cbushack-save-the-world-604e9-firebase-adminsdk-kvlkk-37abcc4355.json");
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


        Rectangle topRect = new Rectangle(340.0, 70);

        VBox box = new VBox();


        Label label = new Label("Welcome User");
        label.getStylesheets().add("/welcome.css");
        StackPane controls = new StackPane(topRect, label);
        topRect.setStyle("-fx-fill: #00ccff");
        label.setAlignment(Pos.TOP_CENTER);
        label.setPadding(new Insets(20));
        box.getChildren().add(controls);
        setTop(box);

        FadeInTransition fade = new FadeInTransition(label);
        fade.setRate(.75);
        fade.play();

        FillTransition filltrans = new FillTransition(Duration.millis(1000), topRect, Color.WHITE, Color.valueOf("#00ccff"));
        filltrans.play();


        TextField email = new TextField();
        email.setPromptText("Email");


        PasswordField pw = new PasswordField();
        pw.setPromptText("Password");

        Button login = new Button("Login");
        Button register = new Button("Register");
        register.setPrefWidth(100);
        register.getStylesheets().add("/ButtonSec.css");


        VBox box1 = new VBox(email, pw, login, register);
        box1.setSpacing(20);
        box1.setAlignment(Pos.CENTER);
        box1.setPadding(new Insets(20));

        setCenter(box1);

        FadeInTransition fade1 = new FadeInTransition(box1);
        fade1.setRate(2);
        fade.setOnFinished((ActionEvent e) ->{fade1.play();});

        //Retrieves data from firebase and sees if this exists.
        login.setOnAction((ActionEvent e) -> {
            userRef.child(email.getText()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String userID = dataSnapshot.child("password").getValue(String.class);
                    if(userID == null){
                        System.out.println("Invalid username or password");
                    } else {
                        MobileApplication.getInstance().switchView("USER_VIEW");
                    }

                }
                @Override
                public void onCancelled(DatabaseError error) {
                    System.out.println("nooooo");
                }
            });

        });

       //Currently this register button's action gets the input lines and puts it into the firebase daatabase.

        register.setOnAction((ActionEvent e) -> {
            //The commented line below makes it so that when you click this button it goes to the next view. This case, the ThirdView class
            //MobileApplication.getInstance().switchView("THIRD_VIEW");
            UserP newUser = new UserP(email.getText(), pw.getText()); //Makes new User object
            userRef.child(newUser.getUsername()).setValueAsync(newUser); //Sets values in the child
        });




    }

    @Override
    protected void updateAppBar(AppBar appBar) {
        appBar.setNavIcon(MaterialDesignIcon.MENU.button(e -> MobileApplication.getInstance().showLayer(GluonApplication.MENU_LAYER)));
        appBar.setTitleText("Secondary");

    }
    
}
