package com.gluonapplication.views;

import com.gluonapplication.GluonApplication;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXNodesList;
import com.jfoenix.controls.JFXSlider;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;

public class Problem2 extends View{


    private static final String ANIMATED_OPTION_BUTTON = "animated-option-button";
    private static final String ANIMATED_OPTION_SUB_BUTTON = "animated-option-sub-button";
    private static final String ANIMATED_OPTION_SUB_BUTTONTWO = "animated-option-sub-buttontwo";



    public Problem2(String name) {
        super(name);

        DatabaseReference ref = FirebaseDatabase.getInstance()
                .getReference("Users");

        DatabaseReference userRef = ref;

        HBox hbox = new HBox();

        VBox vbox = new VBox();

        Label problem = new Label("                Do you think Abortion should be \n                                       legal?");

        JFXSlider hor_right = new JFXSlider();
        hor_right.setMaxWidth(200);
        hor_right.setIndicatorPosition(JFXSlider.IndicatorPosition.RIGHT);
        hor_right.setSnapToTicks(true);
        hor_right.setMajorTickUnit(100.0);
        hor_right.setBlockIncrement(25);
        hor_right.setShowTickLabels(true);
        hor_right.setShowTickMarks(true);
        hor_right.getStylesheets().add("slider.css");

        StringConverter convert = new StringConverter<Double>() {
            @Override
            public String toString(Double object) {
                if (object == 0) {
                    return "Strongly Disagree";
                } else if (object == 25) {
                    return "Disagree";
                } else if (object == 50) {
                    return "Neutral";
                } else if (object == 75) {
                    return "Agree";
                } else if (object == 100) {
                    return "Strongly Agree";
                }
                return null;

            }

            @Override
            public Double fromString(String string) {

                if (string.equals("Strongly Disagree")) {
                    return 0d;
                } else if (string.equals("Disagree")) {
                    return 25d;
                } else if (string.equals("Neutral")) {
                    return 50d;
                } else if (string.equals("Agree")) {
                    return 75d;
                } else if (string.equals("Strongly Agree")) {
                    return 100d;
                }

                return null;
            }
        };

        hor_right.setLabelFormatter(convert);




        //dun work yetti
        hor_right.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if(hor_right.getValue() == 0){
                    hor_right.setStyle(
                            "-fx-control-inner-background: red;"
                    );
                } else if(hor_right.getValue() == 25){
                    hor_right.setStyle(
                            "-fx-control-inner-background: #bf2424;"
                    );
                } else if(hor_right.getValue() == 50){
                    hor_right.setStyle(
                            "-fx-control-inner-background: gray;"
                    );
                } else if(hor_right.getValue() == 75){
                    hor_right.setStyle(
                            "-fx-control-inner-background: #3232a3;"
                    );
                }
                else if(hor_right.getValue() == 100){
                    hor_right.setStyle(
                            "-fx-control-inner-background: #0000ff;"
                    );
                }
            }
        });

        setCenter(hor_right);



        JFXButton ssbutton1 = new JFXButton("Views");
        ssbutton1.setButtonType(JFXButton.ButtonType.RAISED);
        ssbutton1.getStyleClass().addAll(ANIMATED_OPTION_BUTTON, ANIMATED_OPTION_SUB_BUTTON);

        JFXButton ssbutton2 = new JFXButton("   Gun \nControl");
        ssbutton2.setButtonType(JFXButton.ButtonType.RAISED);
        ssbutton2.getStyleClass().addAll(ANIMATED_OPTION_BUTTON, ANIMATED_OPTION_SUB_BUTTONTWO);

        ssbutton2.setOnAction((ActionEvent e) -> {

            MobileApplication.getInstance().switchView(GluonApplication.PROBLEM1_VIEW);

        });

        JFXButton sbutton2 = new JFXButton("Abortion");
        sbutton2.setButtonType(JFXButton.ButtonType.RAISED);
        sbutton2.getStyleClass().addAll(ANIMATED_OPTION_BUTTON, ANIMATED_OPTION_SUB_BUTTONTWO);



        JFXButton sbutton3 = new JFXButton("Minimum \nWage");
        sbutton3.setButtonType(JFXButton.ButtonType.RAISED);
        sbutton3.getStyleClass().addAll(ANIMATED_OPTION_BUTTON, ANIMATED_OPTION_SUB_BUTTONTWO);


        sbutton3.setOnAction((ActionEvent e) -> {

            MobileApplication.getInstance().switchView(GluonApplication.PROBLEM3_VIEW);

        });


        JFXNodesList nodesList = new JFXNodesList();
        nodesList.setSpacing(20);
        nodesList.addAnimatedNode(ssbutton1);
        nodesList.addAnimatedNode(ssbutton2);
        nodesList.addAnimatedNode(sbutton2);
        nodesList.addAnimatedNode(sbutton3);
        nodesList.setRotate(-90);


        nodesList.setPadding(new Insets(10, -10, -10, -10));


        JFXButton submit = new JFXButton("Submit");
        submit.getStylesheets().addAll("MapButton.css");
        submit.setOnMouseEntered(event -> {
            submit.setStyle("-fx-text-fill: #ffffff;");
        });
        submit.setOnMouseExited(event -> {
            submit.setStyle("-fx-text-fill: #49bcff;");
        });

        TextArea thoughts = new TextArea();
        thoughts.setPromptText("Any other thoughts?");

        vbox.getChildren().addAll(nodesList, problem, hor_right, thoughts, submit);
        vbox.setSpacing(30);
        vbox.setPadding(new Insets(20));

        hor_right.setTranslateX(50);

        submit.setTranslateX(220);

        submit.setOnAction((ActionEvent e) -> {

            int count = (int)(hor_right.getValue());
            SecondaryView.repdemCounter += count;
            ref.child(SecondaryView.emailL).child("Problem2Score").setValueAsync(SecondaryView.repdemCounter);
            MobileApplication.getInstance().switchView(GluonApplication.PROBLEM3_VIEW);
        });


        setCenter(vbox);

    }


    @Override
    protected void updateAppBar(AppBar appBar) {
        appBar.setNavIcon(MaterialDesignIcon.MENU.button(e -> MobileApplication.getInstance().showLayer(GluonApplication.MENU_LAYER)));
        appBar.setTitleText("\t\t    Abortion");

    }




}
