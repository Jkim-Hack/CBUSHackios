package com.gluonapplication.views;

import com.gluonapplication.UserP;
import com.gluonhq.charm.down.Services;
import com.gluonhq.charm.down.plugins.DialerService;
import com.gluonhq.charm.glisten.animation.FadeInTransition;
import com.gluonhq.charm.glisten.animation.FadeOutTransition;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.control.LifecycleEvent;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import com.gluonapplication.GluonApplication;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.*;
import com.google.firebase.database.annotations.NotNull;
import javafx.animation.FillTransition;
import javafx.animation.PauseTransition;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Callback;
import javafx.util.Duration;
import jdk.nashorn.internal.codegen.CompilerConstants;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static com.oracle.jrockit.jfr.Transition.To;

public class ChatSplash extends View {

    public ChatSplash(String name) {
        super(name);

        //ImageView profile = new ImageView(ThirdView.profpic);
        Rectangle rect = new Rectangle(335,600);
        rect.setStyle("-fx-fill: #00ccff;");
        Label name1 = new Label("You have been paired!");
        name1.getStylesheets().add("/welcome.css");
        name1.setStyle("-fx-font: 20 Arial;");
        StackPane controls = new StackPane(rect, name1);
        setCenter(controls);
        FadeInTransition fade = new FadeInTransition(controls);
        fade.setRate(1);
        fade.play();


        Transition up = new TranslateTransition(Duration.seconds(1));

        ((TranslateTransition) up).setDuration(Duration.seconds(1.3));
        ((TranslateTransition) up).setToY(-546);
        ((TranslateTransition) up).setNode(controls);

        Transition up1 = new TranslateTransition(Duration.seconds(1));

        ((TranslateTransition) up1).setDuration(Duration.seconds(1.3));
        ((TranslateTransition) up1).setToY(0);
        ((TranslateTransition) up1).setNode(controls);


        PauseTransition pause = new PauseTransition(Duration.seconds(3));
        pause.play();
        pause.setOnFinished(e -> {
            up.play();
            up.setOnFinished(f -> {
                MobileApplication.getInstance().switchView(GluonApplication.CHAT_VIEW);
                up1.play();
            });
        });



    }


    @Override
    protected void updateAppBar(AppBar appBar) {
        appBar.setVisible(false);
        appBar.setNavIcon(MaterialDesignIcon.MENU.button(e -> MobileApplication.getInstance().showLayer(GluonApplication.MENU_LAYER)));
        appBar.setTitleText("Chat Room");

    }

    }

