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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

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

        MobileApplication.getInstance().switchView(GluonApplication.THIRD_VIEW);


        VBox box = new VBox();

            Button problem1 = new Button("Gun Control");
            Button problem2 = new Button("Abortion");
            Button problem3 = new Button("Minimal Wage");

            box.getChildren().addAll(problem1, problem2, problem3);

            box.setSpacing(20);
            box.setPadding(new Insets(20));

        setCenter(box);

        Image img = null;


        try {
            byte[] barr = Base64.getDecoder().decode(SecondaryView.profilePic);
             img = new Image(new ByteArrayInputStream(barr));
        } catch (Exception e){
            System.out.println("NOOOOO");
        }



        Circle cir2 = new Circle(250,250,120);
        cir2.setStroke(Color.SEAGREEN);
        cir2.setFill(new ImagePattern(img));
        cir2.setEffect(new DropShadow(+25d, 0d, +2d, Color.DARKSEAGREEN));

        setTop(cir2);

        box.setAlignment(Pos.CENTER);


    }


    @Override
    protected void updateAppBar(AppBar appBar) {
        appBar.setNavIcon(MaterialDesignIcon.MENU.button(e -> MobileApplication.getInstance().showLayer(GluonApplication.MENU_LAYER)));
        appBar.setTitleText("Current Issues");

    }

}
