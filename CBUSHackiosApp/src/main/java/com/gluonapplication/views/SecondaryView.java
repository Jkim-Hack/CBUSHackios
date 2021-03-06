package com.gluonapplication.views;

import com.gluonapplication.Hash;
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
import com.google.firebase.database.annotations.NotNull;
import com.jfoenix.controls.JFXButton;
import javafx.animation.FillTransition;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import jdk.nashorn.internal.codegen.CompilerConstants;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class SecondaryView extends View {

    private InputStream serviceAccount;
    private FirebaseOptions options;
    public static boolean isuserIDVal;
    public static String emailL;
    public static String passwordD;
    public static String profilePic;
    public static String key;



    public SecondaryView(String name) {
        super(name);

        boolean keepD = false;

        //getStylesheets().add(SecondaryView.class.getResource("secondary.css").toExternalForm());

        try {
            serviceAccount =
                     SecondaryView.class.getResourceAsStream("/cbushack-save-the-world-604e9-firebase-adminsdk-kvlkk-37abcc4355.json");
        } catch (Exception e){
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
        Label or = new Label("OR");

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



        FillTransition filltrans = new FillTransition(Duration.millis(1000), topRect, Color.WHITE, Color.valueOf("#00ccff"));
        filltrans.play();


        TextField email = new TextField();
        email.setPromptText("Username");
        email.setText(UserView.userName);


        PasswordField pw = new PasswordField();
        pw.setPromptText("Password");

        JFXButton login = new JFXButton("Login");
        login.setOnMouseEntered(event -> {
            login.setStyle("-fx-text-fill: #49bcff;");
        });
        login.setOnMouseExited(event -> {
            login.setStyle("-fx-text-fill: #ffffff;");
        });
        JFXButton register = new JFXButton("Register");
        register.setOnMouseEntered(event -> {
            register.setStyle("-fx-text-fill: #49bcff;");
        });
        register.setOnMouseExited(event -> {
            register.setStyle("-fx-text-fill: #ffffff;");
        });

        register.setPrefWidth(100);
        register.getStylesheets().add("/butt.css");

        RadioButton keepData = new RadioButton("Remember Me");
        keepData.setAlignment(Pos.CENTER_LEFT);

        VBox box1 = new VBox(email, pw, keepData);
        box1.setSpacing(20);
        box1.setAlignment(Pos.CENTER);
        box1.setPadding(new Insets(20));


        VBox box2 = new VBox(login, or, register);
        box2.setSpacing(30);
        box2.setAlignment(Pos.CENTER);

        box2.setTranslateY(-120);

        setCenter(box1);
        setBottom(box2);
        FadeInTransition fade = new FadeInTransition(label);
        fade.setRate(.75);
        fade.play();
        FadeInTransition fade2 = new FadeInTransition(box2);
        fade2.setRate(1.5);
        FadeInTransition fade1 = new FadeInTransition(box1);
        fade1.setRate(1.5);
        fade1.play();
        fade2.play();


        com.gluonhq.charm.glisten.control.Dialog error = new com.gluonhq.charm.glisten.control.Dialog();
        error.setContent(new Label("Incorrect Username or Password"));
        Button ok = new Button("OK");
        ok.setOnAction(e  -> {
            error.hide();
        });
        error.getButtons().add(ok);

        Query query = ref.orderByKey();

        //Retrieves data from firebase and sees if this exists.
        login.setOnAction((ActionEvent e) -> {




            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for(DataSnapshot child : dataSnapshot.getChildren()){
                        System.out.println(child.child("password").getValue(String.class));
                        String lol = pw.getText();
                        lol = Hash.MD5(lol);


                        if(child.child("password").getValue(String.class).equals(lol)){
                            isuserIDVal = true;
                            String IDref = child.child("password").getValue(String.class);
                            IDref = Hash.MD5(IDref);
                            System.out.println(IDref);
                            String ref = child.child("encodedImage").getValue(String.class);
                            int counter = child.child("counter").getValue(Integer.class);
                            boolean findmatch = child.child("findingMatch").getValue(Boolean.class);
                            emailL = email.getText();
                            passwordD = IDref;
                            profilePic = ref;
                            key = child.getKey();
                            break;
                        }
                    }


                }

                @Override
                public void onCancelled(DatabaseError error) {
                    System.out.println("nooooo");
                }


            });


            try {
                Thread.sleep(1300);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }

            if(isuserIDVal){
                pw.clear();
                MobileApplication.getInstance().switchView(GluonApplication.THIRD_VIEW);
            } else {
                pw.clear();
                error.showAndWait();
            }

        });

       //Currently this register button's action gets the input lines and puts it into the firebase database.

        register.setOnAction((ActionEvent e) -> {

            MobileApplication.getInstance().switchView(GluonApplication.USER_VIEW);

        });




    }

    @Override
    protected void updateAppBar(AppBar appBar) {
        appBar.setVisible(false);
        appBar.setNavIcon(MaterialDesignIcon.MENU.button(e -> MobileApplication.getInstance().showLayer(GluonApplication.MENU_LAYER)));
        appBar.setTitleText("Secondary");

    }

    public void validate(Query query, com.gluonapplication.Callback callback){





    }

    }





