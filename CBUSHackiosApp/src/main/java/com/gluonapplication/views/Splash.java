package com.gluonapplication.views;

import com.gluonapplication.UserP;
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

public class Splash extends View {

    public Splash(String name) {
        super(name);

        Rectangle back = new Rectangle(335,650);
        back.setStyle("-fx-fill: #00ccff;");
        Label label = new Label("Views");
        label.setTranslateY(-160);
        label.getStylesheets().add("/welcome.css");
        label.setStyle("-fx-font: 45 Arial;");
        Label shred = new Label("Shredd Squad");
        shred.getStylesheets().add("/welcome.css");
        shred.setStyle("-fx-font: 20 Arial;");
        shred.setTranslateY(100);

        StackPane controls = new StackPane(back, label,shred);
        setCenter(controls);


        Task<Void> task = new Task<Void>() {

            @Override
            protected Void call() throws Exception {
                Platform.runLater(() -> MobileApplication.getInstance().switchView(GluonApplication.SECONDARY_VIEW));
                return null;
            }
        };

        addEventHandler(LifecycleEvent.SHOWN, e -> {
            PauseTransition pause = new PauseTransition(Duration.seconds(3));
            pause.setOnFinished(f -> {


                FadeOutTransition fade = new FadeOutTransition(label);
                fade.setRate(1.5);
                fade.play();

                FadeOutTransition fade1 = new FadeOutTransition(shred);
                fade1.setRate(1.5);
                fade1.play();


                Transition trans = new TranslateTransition();
                ((TranslateTransition) trans).setDuration(Duration.seconds(1.3));
                ((TranslateTransition) trans).setToY(-580);
                ((TranslateTransition) trans).setNode(back);

                trans.play();

                fade.setOnFinished(event -> {
                    label.setVisible(false);
                    shred.setVisible(false);

                });

                PauseTransition pause1 = new PauseTransition(Duration.seconds(1.4));
                pause1.play();

                pause1.setOnFinished(g -> {
                new Thread(task).start();

                });

            });

            pause.play();

        });

    }




    @Override
    protected void updateAppBar(AppBar appBar) {
        appBar.setVisible(false);
        appBar.setNavIcon(MaterialDesignIcon.MENU.button(e -> MobileApplication.getInstance().showLayer(GluonApplication.MENU_LAYER)));
        appBar.setTitleText("Secondary");


    }
}
