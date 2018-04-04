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
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.event.MouseEventHandler;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.event.EventHandler;





//IGNORE THIS VIEW DO NOT USE THIS YET


public class PrimaryView extends View implements MapComponentInitializedListener {

    GoogleMapView mapView;
    GoogleMap map;


    public PrimaryView(String name) {
        super(name);

        mapView = new GoogleMapView();
        mapView.addMapInializedListener(this);


        setCenter(mapView);



    }

    @Override
    protected void updateAppBar(AppBar appBar) {
        appBar.setNavIcon(MaterialDesignIcon.MENU.button(e -> MobileApplication.getInstance().showLayer(GluonApplication.MENU_LAYER)));
        appBar.setTitleText("Primary");
        appBar.getActionItems().add(MaterialDesignIcon.SEARCH.button(e -> System.out.println("Search")));
    }


    @Override
    public void mapInitialized() {
        //Set the initial properties of the map.
        MapOptions mapOptions = new MapOptions();
      
        mapOptions.center(new LatLong(40.1190466, -83.16312040000003))
                .mapType(MapTypeIdEnum.ROADMAP)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .zoom(12);

        map = mapView.createMap(mapOptions);

        //Add a marker to the map
        MarkerOptions markerOptions = new MarkerOptions();

        markerOptions.position( new LatLong(40.1190466, -83.16312040000003) )
                .visible(true)
                .title("Problem Marker");

        Marker marker = new Marker( markerOptions);
     
        marker.setPosition(new LatLong(40.1190466, -83.16312040000003));
        map.addMarker(marker);
        map.setZoom(18);
        
        map.addMouseEventHandler(type, h);

    }



}
