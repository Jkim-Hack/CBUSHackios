package com.gluonapplication;

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

import static com.gluonapplication.GluonApplication.*;

public class DrawerManager {

    private final NavigationDrawer drawer;

    public DrawerManager() {
        this.drawer = new NavigationDrawer();
        
        NavigationDrawer.Header header = new NavigationDrawer.Header("Shred Squad",
                "4 Active Conversations",
                new Avatar(21, new Image(DrawerManager.class.getResourceAsStream("/profile.png"))));
        drawer.setHeader(header);
        header.setOnAction((ActionEvent e) -> {
            MobileApplication.getInstance().switchView(GluonApplication.PROBLEM1_VIEW);
        });
        
        final Item primaryItem = new ViewItem("Home", MaterialDesignIcon.HOME.graphic(), PRIMARY_VIEW, ViewStackPolicy.SKIP);
        //final Item secondaryItem = new ViewItem("Login/Register", MaterialDesignIcon.DASHBOARD.graphic(), SECONDARY_VIEW);
        final Item thirdItem = new ViewItem("Third", MaterialDesignIcon.DASHBOARD.graphic(), THIRD_VIEW);
        final Item views = new ViewItem("Views", MaterialDesignIcon.DASHBOARD.graphic(), PROBLEM1_VIEW);
        final Item userItem = new ViewItem("UserV", MaterialDesignIcon.DASHBOARD.graphic(), USER_VIEW);
        drawer.getItems().addAll(primaryItem, thirdItem, views, userItem);
        
        if (Platform.isDesktop()) {
            final Item quitItem = new Item("Quit", MaterialDesignIcon.EXIT_TO_APP.graphic());
            quitItem.selectedProperty().addListener((obs, ov, nv) -> {
                if (nv) {
                    Services.get(LifecycleService.class).ifPresent(LifecycleService::shutdown);
                }
            });
            drawer.getItems().add(quitItem);
        }
        
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
