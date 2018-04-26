package com.gluonapplication;

import com.gluonapplication.views.*;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.layout.layer.SidePopupView;
import com.gluonhq.charm.glisten.visual.Swatch;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class GluonApplication extends MobileApplication {


   //
    public static final String SPLASH_VIEW = HOME_VIEW;
    public static final String PRIMARY_VIEW = "Primary View";
    public static final String SECONDARY_VIEW = "Secondary View";
    public static final String THIRD_VIEW = "Third ";
    public static final String USER_VIEW = "User View";
    public static final String CHAT_VIEW = "Chat Room";
    public static final String PROBLEM1_VIEW = "Problem1 View";
    public static final String PROBLEM2_VIEW = "Problem2 View";
    public static final String PROBLEM3_VIEW = "Problem3 View";
    public static final String MENU_LAYER = "Side Menu";

    //LOOK ONLY AT SECONDARYVIEW AND THIRDVIEW

    @Override
    public void init() {
        addViewFactory(SPLASH_VIEW, () -> new Splash(SPLASH_VIEW));
        addViewFactory(SECONDARY_VIEW, () -> new SecondaryView(SECONDARY_VIEW));
        addViewFactory(PRIMARY_VIEW, () -> new PrimaryView(PRIMARY_VIEW));
        addViewFactory(THIRD_VIEW, () -> new ThirdView(THIRD_VIEW));
        addViewFactory(PROBLEM1_VIEW, () -> new Problem1(PROBLEM1_VIEW));
        addViewFactory(PROBLEM2_VIEW, () -> new Problem2(PROBLEM2_VIEW));
        addViewFactory(PROBLEM3_VIEW, () -> new Problem3(PROBLEM3_VIEW));
        addViewFactory(USER_VIEW, () -> new UserView(USER_VIEW));
        addViewFactory(CHAT_VIEW, () -> new ChatRoom(CHAT_VIEW));
        addLayerFactory(MENU_LAYER, () -> new SidePopupView(new DrawerManager().getDrawer()));
    }

    @Override
    public void postInit(Scene scene) {
        Swatch.BLUE.assignTo(scene);

        scene.getStylesheets().add(GluonApplication.class.getResource("style.css").toExternalForm());
        ((Stage) scene.getWindow()).getIcons().add(new Image(GluonApplication.class.getResourceAsStream("/icon.png")));
    }
}
