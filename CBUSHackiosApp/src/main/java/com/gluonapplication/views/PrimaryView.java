package com.gluonapplication.views;

import com.gluonhq.charm.glisten.animation.FadeInTransition;
import com.gluonhq.charm.glisten.animation.FadeInUpBigTransition;
import com.gluonhq.charm.glisten.animation.FadeInUpTransition;
import com.gluonhq.charm.glisten.animation.FadeOutDownTransition;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.control.Icon;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import com.gluonapplication.GluonApplication;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class PrimaryView extends View {

    public PrimaryView(String name) {
        super(name);
        
        getStylesheets().add(PrimaryView.class.getResource("primary.css").toExternalForm());

        Label label = new Label("Welcome User!");

        Button button = new Button("Change the World!");
        FadeInUpTransition fade = new FadeInUpTransition(label);
        button.setGraphic(new Icon(MaterialDesignIcon.LANGUAGE));
        button.setOnAction(e -> label.setText("Hello JavaFX Universe!"));

        setTop(label);

        fade.play();

    }

    @Override
    protected void updateAppBar(AppBar appBar) {
        appBar.setNavIcon(MaterialDesignIcon.MENU.button(e -> MobileApplication.getInstance().showLayer(GluonApplication.MENU_LAYER)));
        appBar.setTitleText("Primary");
        appBar.getActionItems().add(MaterialDesignIcon.SEARCH.button(e -> System.out.println("Search")));
    }
    
}
