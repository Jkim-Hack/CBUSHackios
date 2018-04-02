package com.gluonapplication.views;

import com.gluonapplication.GluonApplication;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class UserView extends View{



        public UserView(String name){
            super(name);

            Button map = new Button();
            map.setOnAction((ActionEvent e) ->{
                System.out.println(SecondaryView.isuserIDVal);
            });
           setCenter(map);
        }


        @Override
        protected void updateAppBar(AppBar appBar) {
            appBar.setNavIcon(MaterialDesignIcon.MENU.button(e -> MobileApplication.getInstance().showLayer(GluonApplication.MENU_LAYER)));
            appBar.setTitleText("User");
            appBar.getActionItems().add(MaterialDesignIcon.SEARCH.button(e -> System.out.println("Search")));
        }






}
