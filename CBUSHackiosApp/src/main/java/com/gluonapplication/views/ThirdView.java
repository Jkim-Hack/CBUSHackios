package com.gluonapplication.views;

import com.gluonapplication.GluonApplication;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXNodesList;
import com.jfoenix.controls.JFXSlider;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.io.ByteArrayInputStream;
import java.util.Base64;

import static com.gluonapplication.views.SecondaryView.isuserIDVal;


public class ThirdView extends View{


    private static final String FX_TEXT_FILL_WHITE = "-fx-text-fill:WHITE";
    private static final String ANIMATED_OPTION_BUTTON = "animated-option-button";
    private static final String ANIMATED_OPTION_SUB_BUTTON = "animated-option-sub-button";
    private static final String ANIMATED_OPTION_SUB_BUTTONTWO = "animated-option-sub-buttontwo";


    public ThirdView(String name){
        super(name);

/*
        JFXSlider hor_right = new JFXSlider();
        hor_right.setMaxWidth(200);
        hor_right.setIndicatorPosition(JFXSlider.IndicatorPosition.RIGHT);
        hor_right.setSnapToTicks(true);
        hor_right.setMajorTickUnit(100.0);
        hor_right.setBlockIncrement(25);
        hor_right.setShowTickLabels(true);
        hor_right.setShowTickMarks(true);
        hor_right.getStylesheets().add("slider.css");

        JFXNodesList profileList = new JFXNodesList();


        Button b = new Button("LOL");

        profileList.addAnimatedNode(b);


        JFXButton ssbutton1 = new JFXButton("Profile");
        ssbutton1.setButtonType(JFXButton.ButtonType.RAISED);
        ssbutton1.getStyleClass().addAll(ANIMATED_OPTION_BUTTON, ANIMATED_OPTION_SUB_BUTTON);

        JFXButton ssbutton2 = new JFXButton("Problems");
        ssbutton2.setButtonType(JFXButton.ButtonType.RAISED);
        ssbutton2.getStyleClass().addAll(ANIMATED_OPTION_BUTTON, ANIMATED_OPTION_SUB_BUTTONTWO);

        JFXButton ssbutton3 = new JFXButton("News");
        ssbutton3.setButtonType(JFXButton.ButtonType.RAISED);
        ssbutton3.getStyleClass().addAll(ANIMATED_OPTION_BUTTON, ANIMATED_OPTION_SUB_BUTTON);

        JFXButton sbutton1 = new JFXButton("Gun Control");
        sbutton1.setButtonType(JFXButton.ButtonType.RAISED);
        sbutton1.getStyleClass().addAll(ANIMATED_OPTION_BUTTON, ANIMATED_OPTION_SUB_BUTTONTWO);

        JFXButton sbutton2 = new JFXButton("Abortion");
        sbutton2.setButtonType(JFXButton.ButtonType.RAISED);
        sbutton2.getStyleClass().addAll(ANIMATED_OPTION_BUTTON, ANIMATED_OPTION_SUB_BUTTONTWO);

        JFXButton sbutton3 = new JFXButton("Minimal Wage");
        sbutton3.setButtonType(JFXButton.ButtonType.RAISED);
        sbutton3.getStyleClass().addAll(ANIMATED_OPTION_BUTTON, ANIMATED_OPTION_SUB_BUTTONTWO);



        JFXNodesList nodesList1 = new JFXNodesList();
        nodesList1.setRotate(270);
        nodesList1.setSpacing(10);
        nodesList1.addAnimatedNode(ssbutton2);
        nodesList1.addAnimatedNode(sbutton1);
        nodesList1.addAnimatedNode(sbutton2);
        nodesList1.addAnimatedNode(sbutton3);


        JFXNodesList nodesList = new JFXNodesList();
        nodesList.setSpacing(10);
        nodesList.addAnimatedNode(ssbutton1);
        nodesList.addAnimatedNode(nodesList1);
        nodesList.addAnimatedNode(ssbutton3);


        nodesList.setPadding(new Insets(10, -10, -10, -10));

*/































        Label welcome = new Label("Welcome, " + SecondaryView.emailL);

        welcome.setStyle("-fx-font: 21 Ariel;");

        VBox header = new VBox(welcome);
            header.setPadding(new Insets(20));
            setTop(header);
            header.setAlignment(Pos.CENTER);

        Rectangle rect = new Rectangle(300,200);
        Label label = new Label("This Week's Issue:");
        label.setStyle("-fx-font: 17 Ariel;");
        label.setTranslateY(-72);

        rect.setStyle("-fx-fill: #d0e8ff");
        rect.setStroke(Color.BLACK);
        rect.setArcWidth(20);
        rect.setArcHeight(20);

        Button match = new Button("Find a Match");


        Label label2 = new Label("Abortion and Planned Parenthood");
        label2.setStyle("-fx-font: 17 Ariel;");

        StackPane controls = new StackPane(rect, label, label2, match);
        label.setAlignment(Pos.TOP_CENTER);
        match.setTranslateY(70);

        VBox bot = new VBox(controls);

        setBottom(bot);
        bot.setTranslateY(-20);

        Rectangle rect2 = new Rectangle(300,200);
        rect2.setStyle("-fx-fill: #d0e8ff");
        rect2.setStroke(Color.BLACK);
        rect2.setArcWidth(20);
        rect2.setArcHeight(20);

        StackPane controls2 = new StackPane(rect2);

        VBox center = new VBox(controls2);
        center.setTranslateY(30);
        setCenter(center);




    }

    @Override
    protected void updateAppBar(AppBar appBar) {
        appBar.setNavIcon(MaterialDesignIcon.MENU.button(e -> MobileApplication.getInstance().showLayer(GluonApplication.MENU_LAYER)));
        appBar.setTitleText("                       Home");

    }

}
