package com.gluonapplication;

import com.gluonapplication.views.SecondaryView;
import com.gluonhq.charm.down.Platform;
import com.gluonhq.charm.down.Services;
import com.gluonhq.charm.down.plugins.LifecycleService;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.application.ViewStackPolicy;
import com.gluonhq.charm.glisten.control.Avatar;
import com.gluonhq.charm.glisten.control.NavigationDrawer;
import com.gluonhq.charm.glisten.control.NavigationDrawer.Item;
import com.gluonhq.charm.glisten.control.NavigationDrawer.ViewItem;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.image.Image;

import java.io.ByteArrayInputStream;
import java.util.Base64;

import static com.gluonapplication.GluonApplication.*;

public class DrawerManager {

    private final NavigationDrawer drawer;

    public static  Image profpic;

    public DrawerManager() {
        this.drawer = new NavigationDrawer();

        Image img = new Image("/profile.png");


        try {
            byte[] barr = Base64.getDecoder().decode(SecondaryView.profilePic);
            img = new Image(new ByteArrayInputStream(barr));
            profpic = img;
        } catch (Exception e){
            System.out.println("NOOOOOy");
        }
        
        NavigationDrawer.Header header = new NavigationDrawer.Header(SecondaryView.emailL,
                "1 Active Conversation",
                new Avatar(21, profpic));
        drawer.setHeader(header);
        header.setOnAction((ActionEvent e) -> {
            MobileApplication.getInstance().switchView(GluonApplication.PROBLEM1_VIEW);
        });
        
        //final Item primaryItem = new ViewItem("Home", MaterialDesignIcon.HOME.graphic(), PRIMARY_VIEW, ViewStackPolicy.SKIP);
        final Item thirdItem = new ViewItem("Home", MaterialDesignIcon.HOME.graphic(), THIRD_VIEW);
        final Item views = new ViewItem("Views", MaterialDesignIcon.DASHBOARD.graphic(), PROBLEM1_VIEW);
       // final Item userItem = new ViewItem("UserV", MaterialDesignIcon.DASHBOARD.graphic(), USER_VIEW);
        final Item quit = new ViewItem("Sign Out", MaterialDesignIcon.EXIT_TO_APP.graphic(), SECONDARY_VIEW);
        final Item Chat = new ViewItem("ChatRoom", MaterialDesignIcon.MESSAGE.graphic(), CHAT_VIEW);
        drawer.getItems().addAll(thirdItem, views, Chat, quit);


        
        drawer.addEventHandler(NavigationDrawer.ITEM_SELECTED, 
                e -> MobileApplication.getInstance().hideLayer(MENU_LAYER));
        
        MobileApplication.getInstance().viewProperty().addListener((obs, oldView, newView) -> updateItem(newView.getName()));
        updateItem(PRIMARY_VIEW);
    }
    
    private void updateItem(String nameView) {
        for (Node item : drawer.getItems()) {
            if (item instanceof ViewItem && ((ViewItem) item).getViewName().equals(nameView)) {
                drawer.setSelectedItem(item);
                break;
            }
        }
    }
    
    public NavigationDrawer getDrawer() {
        return drawer;
    }
}
