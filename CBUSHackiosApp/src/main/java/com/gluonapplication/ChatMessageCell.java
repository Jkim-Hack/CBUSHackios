package com.gluonapplication;

import com.gluonhq.charm.glisten.control.Avatar;
import com.gluonhq.charm.glisten.control.CharmListCell;
import com.gluonhq.charm.glisten.control.ListTile;
import javafx.scene.control.ListCell;

public class ChatMessageCell extends CharmListCell<ChatMessage> {

    private final ListTile tile;
    private final Avatar avatar;



    public ChatMessageCell(){
        avatar = new Avatar();
        tile = new ListTile();
        tile.setPrimaryGraphic(avatar);

    }


    @Override public void updateItem(ChatMessage item, boolean empty) {
        super.updateItem(item, empty);
        if (!empty && item != null) {
            avatar.setImage(item.getUserImage());
            tile.textProperty().setAll(item.getMessageUser());
            tile.textProperty().addAll(item.getMessageText());
            setGraphic(tile);
        } else {
            setGraphic(null);
        }
    }


}


