package com.gluonapplication.views;

import com.gluonapplication.GluonApplication;
import com.gluonhq.charm.glisten.animation.FadeInTransition;
import com.gluonhq.charm.glisten.animation.FadeOutTransition;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXNodesList;
import com.jfoenix.controls.JFXSlider;
import javafx.animation.PauseTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.awt.event.MouseEvent;
import java.io.ByteArrayInputStream;
import java.util.Base64;

import static com.gluonapplication.views.SecondaryView.isuserIDVal;


public class ThirdView extends View{


    private static final String FX_TEXT_FILL_WHITE = "-fx-text-fill:WHITE";
    private static final String ANIMATED_OPTION_BUTTON = "animated-option-button";
    private static final String ANIMATED_OPTION_SUB_BUTTON = "animated-option-sub-button";
    private static final String ANIMATED_OPTION_SUB_BUTTONTWO = "animated-option-sub-buttontwo";

    public static Image profpic;


    public ThirdView(String name){
        super(name);

        Image img = new Image("/profile.png");

        try{
            byte[] barr = Base64.getDecoder().decode(SecondaryView.profilePic);
            img = new Image(new ByteArrayInputStream(barr));
            profpic = img;
        }catch (Exception e){
            System.out.println("NOOOOOOy");
        }

/*
        ImageView img = new ImageView("/npr.png");
        img.setFitHeight(50);
        img.setFitWidth(50);

        Label welcome = new Label("Welcome, " + SecondaryView.emailL);

        welcome.setStyle("-fx-font: 21 Ariel;");

        VBox header = new VBox(welcome);
            header.setPadding(new Insets(20));
            setTop(header);
            header.setAlignment(Pos.CENTER);

        Rectangle rect = new Rectangle(300,175);
        Label label = new Label("This Week's Issue");
        label.setStyle("-fx-font: 17 Ariel;");
        label.setTranslateY(-65);

        rect.setStyle("-fx-fill: #d0e8ff");
        rect.setStroke(Color.BLACK);
        rect.setArcWidth(20);
        rect.setArcHeight(20);

        Line line = new Line(-85,115,85,115);






        Label label2 = new Label("Abortion and Planned Parenthood");
        label2.setStyle("-fx-font: 17 Ariel;");

        StackPane controls = new StackPane(rect, label, label2, line);
        label.setAlignment(Pos.TOP_CENTER);
        line.setStroke(Color.GRAY);
        line.setTranslateY(-55);


        VBox bot = new VBox(controls);

        setBottom(bot);
        bot.setTranslateY(-110);

        Rectangle rect2 = new Rectangle(300,175);
        rect2.setStyle("-fx-fill: #d0e8ff");
        rect2.setStroke(Color.BLACK);
        rect2.setArcWidth(20);
        rect2.setArcHeight(20);

        Label chats = new Label("Current News");
        chats.setAlignment(Pos.TOP_CENTER);
        chats.setTranslateY(-65);
        chats.setStyle("-fx-font: 17 Ariel;");


        Line line2 = new Line(-65,115,65,115);
        line2.setStroke(Color.GRAY);
        line2.setTranslateY(-55);

        Line line3 = new Line(-75,0,135,0);
        line3.setStroke(Color.GRAY);

        Line line4 = new Line(-75,25,135,25);
        line4.setStroke(Color.GRAY);

        Label label4 = new Label("State of Missouri bans abortions");
        label4.setStyle("-fx-font: 14 Ariel;");

        StackPane controls2 = new StackPane(rect2, chats,line2, img, label4);
        img.setTranslateX(-110);
        img.setTranslateY(-9);
        line3.setTranslateY(10);
        line3.setTranslateX(30);
        line4.setTranslateY(-30);
        line4.setTranslateX(30);
        label4.setTranslateY(-11);
        label4.setTranslateX(25);

        Button match = new Button("Find a Match");
        match.setStyle("-fx-font: 15 Ariel;");
        match.setStyle("-fx-background-color: #fa8072;");
        match.setPrefWidth(300);
        match.setPrefHeight(50);
        match.setTranslateY(215);
        match.setTranslateX(17);
        match.setOnAction(e -> {

            MobileApplication.getInstance().switchView(GluonApplication.CHAT_VIEW);

        });
        VBox center = new VBox( controls2 match);
        setCenter(center);
*/

        //Rectangles
        Rectangle first = new Rectangle(335,136.5);
        first.setStyle("-fx-fill: #00a9ff;");
        Rectangle second = new Rectangle(335,136.5);
        second.setStyle("-fx-fill: #00c6ff;");
        Rectangle third = new Rectangle(335,136.5);
        third.setStyle("-fx-fill: #69e4ff;");
        Rectangle fourth = new Rectangle(335,136.5);
        fourth.setStyle("-fx-fill: #aeeffc;");

        //Labels
        Label user = new Label(SecondaryView.emailL);
        user.getStylesheets().add("/texts.css");
        user.setLayoutX(14);
        user.setLayoutY(17);

        Label week = new Label("Weekly Issue");
        week.getStylesheets().add("/texts.css");
        week.setLayoutX(14);
        week.setLayoutY(153.5);

        Label find = new Label("Find a Match");
        find.getStylesheets().add("/texts.css");;
        find.setLayoutX(14);
        find.setLayoutY(290);

        Label exit = new Label("Sign Out");
        exit.getStylesheets().add("/texts.css");
        exit.setStyle("-fx-text-fill: #ff8585;");
        exit.setLayoutX(14);
        exit.setLayoutY(426.5);

        Label gun = new Label("Gun Control");
        gun.getStylesheets().add("/texts.css");
        gun.setLayoutX(208);
        gun.setLayoutY(153);

        Label news = new Label("NPR     |     New Gun Control Bill in Delaware");
        news.setLayoutX(28);
        news.setLayoutY(98);
        news.setStyle("-fx-text-fill: #ffffff;");
        news.setVisible(false);

        Label news1 = new Label("WSJ     |     Students Protest Over Gun Control");
        news1.setLayoutX(28);
        news1.setLayoutY(128);
        news1.setStyle("-fx-text-fill: #ffffff;");
        news1.setVisible(false);

        Label news2 = new Label("BBC     |     Huge Gun Control Rallies Sweep U.S");
        news2.setLayoutX(28);
        news2.setLayoutY(158);
        news2.setStyle("-fx-text-fill: #ffffff;");
        news2.setVisible(false);


        AnchorPane nodes = new AnchorPane(first, second, third, fourth, user, week, find, exit, gun, news, news1, news2);
        setCenter(nodes);
        first.setLayoutX(0);
        first.setLayoutY(0);
        second.setLayoutX(0);
        second.setLayoutY(136.5);
        second.setRotate(180);
        third.setLayoutX(0);
        third.setLayoutY(273);
        fourth.setLayoutX(0);
        fourth.setLayoutY(409.5);

        Transition trans = new TranslateTransition();
        ((TranslateTransition) trans).setDuration(Duration.seconds(1));
        ((TranslateTransition) trans).setToY(-125.5);
        ((TranslateTransition) trans).setNode(week);

        Transition tran1 = new TranslateTransition();
        ((TranslateTransition) tran1).setDuration(Duration.seconds(1));
        ((TranslateTransition) tran1).setToY(-125.5);
        ((TranslateTransition) tran1).setNode(gun);

        Transition trans1 = new TranslateTransition();
        ((TranslateTransition) trans1).setDuration(Duration.seconds(1));
        ((TranslateTransition) trans1).setToY(-145.5);
        ((TranslateTransition) trans1).setNode(user);
        ScaleTransition st = new ScaleTransition(Duration.seconds(1.3), second);
        st.setToY(4);
        PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
        FadeInTransition fade1 = new FadeInTransition(news1);
        fade1.setRate(1);
        FadeInTransition fade2 = new FadeInTransition(news2);
        fade2.setRate(1);
        FadeInTransition fade = new FadeInTransition(news);
        fade.setRate(1);


        Transition trans3 = new TranslateTransition();
        ((TranslateTransition) trans3).setDuration(Duration.seconds(1));
        ((TranslateTransition) trans3).setToY(0);
        ((TranslateTransition) trans3).setNode(week);
        Transition tran3 = new TranslateTransition();
        ((TranslateTransition) tran3).setDuration(Duration.seconds(1));
        ((TranslateTransition) tran3).setToY(0);
        ((TranslateTransition) tran3).setNode(gun);

        Transition down = new TranslateTransition();
        ((TranslateTransition) down).setDuration(Duration.seconds(1));
        ((TranslateTransition) down).setToY(125.5);
        ((TranslateTransition) down).setNode(news);
        Transition down1 = new TranslateTransition();
        ((TranslateTransition) down1).setDuration(Duration.seconds(1));
        ((TranslateTransition) down1).setToY(125.5);
        ((TranslateTransition) down1).setNode(news1);
        Transition down2 = new TranslateTransition();
        ((TranslateTransition) down2).setDuration(Duration.seconds(1));
        ((TranslateTransition) down2).setToY(125.5);
        ((TranslateTransition) down2).setNode(news2);

        Transition up = new TranslateTransition();
        ((TranslateTransition) up).setDuration(Duration.seconds(1));
        ((TranslateTransition) up).setToY(0);
        ((TranslateTransition) up).setNode(news);
        Transition up1 = new TranslateTransition();
        ((TranslateTransition) up1).setDuration(Duration.seconds(1));
        ((TranslateTransition) up1).setToY(0);
        ((TranslateTransition) up1).setNode(news1);
        Transition up2 = new TranslateTransition();
        ((TranslateTransition) up2).setDuration(Duration.seconds(1));
        ((TranslateTransition) up2).setToY(0);
        ((TranslateTransition) up2).setNode(news2);


        FadeOutTransition fade4 = new FadeOutTransition(news);
        fade4.setRate(1);
        FadeOutTransition fade5 = new FadeOutTransition(news1);
        fade5.setRate(1);
        FadeOutTransition fade6 = new FadeOutTransition(news2);
        fade6.setRate(1);
        Transition trans4 = new TranslateTransition();
        ((TranslateTransition) trans4).setDuration(Duration.seconds(1));
        ((TranslateTransition) trans4).setToY(0);
        ((TranslateTransition) trans4).setNode(user);
        ScaleTransition scaleDown = new ScaleTransition(Duration.seconds(1), second);
        scaleDown.setToY(1);

        fourth.setOnMouseReleased(event -> {
                MobileApplication.getInstance().switchView(GluonApplication.SECONDARY_VIEW);

        });

        third.setOnMouseReleased(event -> {
            MobileApplication.getInstance().switchView(GluonApplication.CHAT_VIEW);

        });



        second.setOnMousePressed(e -> {


                    trans1.play();
                    tran1.play();
                    trans.play();
                    st.play();


                    pause.play();
                    pause.setOnFinished(g -> {

                        news.setVisible(true);
                        news1.setVisible(true);
                        news2.setVisible(true);
                        fade.play();
                        fade2.play();
                        fade1.play();

                    });
                });
            second.setOnMouseReleased(f -> {

                down.play();
                down1.play();
                down2.play();
                fade6.play();
                fade5.play();
                fade4.play();
                fade4.setOnFinished(event -> {
                            news.setVisible(false);
                            news1.setVisible(false);
                            news2.setVisible(false);
                            news.setLayoutY(98);
                         news1.setLayoutY(128);
                             news2.setLayoutY(158);

                });


                trans3.play();
                tran3.play();
                trans4.play();
                scaleDown.play();


            });


    }

    @Override
    protected void updateAppBar(AppBar appBar) {
        appBar.setNavIcon(MaterialDesignIcon.MENU.button(e -> MobileApplication.getInstance().showLayer(GluonApplication.MENU_LAYER)));
        appBar.setTitleText("");

    }

}
