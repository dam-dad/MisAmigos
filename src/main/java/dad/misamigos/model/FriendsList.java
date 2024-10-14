package dad.misamigos.model;

import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;

public class FriendsList extends SimpleListProperty<Friend> {

    public FriendsList() {
        super(FXCollections.observableArrayList());
    }

}
