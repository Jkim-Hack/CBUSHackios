package com.gluonapplication.views;

import com.gluonapplication.GluonApplication;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;


//THIS IS WHERE YOU NEED TO WORK DO NOT CHANGE ANYTHING TO THIS, THIS IS THE BASIC FORMAT OF A VIEW.

public class ThirdView extends View{

    public ThirdView(String name){
        super(name);


    //TODO ABHISHEK Create a register UI that takes in email, username, password.
    //IMPORTANT: Firebase cannot get @'s or .'s from the app so I suggest you use a @gmail.com/@yahoo.com dropdown menu
        // right next to the place where the user types in their username

    }


    @Override
    protected void updateAppBar(AppBar appBar) {
        appBar.setNavIcon(MaterialDesignIcon.MENU.button(e -> MobileApplication.getInstance().showLayer(GluonApplication.MENU_LAYER)));
        appBar.setTitleText("Third");
        appBar.getActionItems().add(MaterialDesignIcon.SEARCH.button(e -> System.out.println("Search")));
    }

}
