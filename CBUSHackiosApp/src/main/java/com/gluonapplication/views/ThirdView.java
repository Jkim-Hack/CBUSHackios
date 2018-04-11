package com.gluonapplication.views;

import com.gluonapplication.GluonApplication;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;

import com.jfoenix.controls.JFXSlider;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;



public class ThirdView extends View{

    public ThirdView(String name){
        super(name);

        JFXSlider hor_right = new JFXSlider();
        hor_right.setMaxWidth(200);
        hor_right.setIndicatorPosition(JFXSlider.IndicatorPosition.RIGHT);
        hor_right.setSnapToTicks(true);
        hor_right.setMajorTickUnit(100.0);
        hor_right.setBlockIncrement(25);
        hor_right.setShowTickLabels(true);
        hor_right.setShowTickMarks(true);
        hor_right.getStylesheets().add("slider.css");
        setCenter(hor_right);

    }


    @Override
    protected void updateAppBar(AppBar appBar) {
        appBar.setNavIcon(MaterialDesignIcon.MENU.button(e -> MobileApplication.getInstance().showLayer(GluonApplication.MENU_LAYER)));
        appBar.setTitleText("Third");
        appBar.getActionItems().add(MaterialDesignIcon.SEARCH.button(e -> System.out.println("Search")));
    }

}
