package dad.misamigos.controllers;

import dad.misamigos.model.Friend;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RootController implements Initializable {

    // controllers

    private final FriendController friendController = new FriendController();

    // model

    private final ListProperty<Friend> friends = new SimpleListProperty<>(
            FXCollections.observableArrayList(
                friend -> new Observable[] { friend.nameProperty(), friend.surnameProperty() } // indicamos que properties de cada bean son observables dentro de la lista
            )
    );

    private final ObjectProperty<Friend> selectedFriend = new SimpleObjectProperty<>();

    // view

    @FXML
    private Button enemyButton;

    @FXML
    private Button friendButton;

    @FXML
    private ListView<Friend> friendsList;

    @FXML
    private BorderPane root;

    @FXML
    private VBox emptyBox;

    @FXML
    private BorderPane placeholderPane;

    @FXML
    private Label friendsCountLabel;

    public RootController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RootView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // bindings

        friendsList.itemsProperty().bind(friends);
        selectedFriend.bind(friendsList.getSelectionModel().selectedItemProperty());
        enemyButton.disableProperty().bind(selectedFriend.isNull());
        placeholderPane.centerProperty().bind(
                Bindings.when(selectedFriend.isNotNull())
                        .then((Pane)friendController.getRoot())
                        .otherwise(emptyBox)
        );
        friendsCountLabel.textProperty().bind(friends.sizeProperty().asString());

        friendController.friendProperty().bind(selectedFriend);

    }

    public BorderPane getRoot() {
        return root;
    }

    @FXML
    void onEnemyAction(ActionEvent event) {
        friends.remove(selectedFriend.get());
    }

    @FXML
    void onFriendAction(ActionEvent event) {
        Friend friend = new Friend();
        friend.setName("Nombre");
        friend.setSurname("Apellidos");
        friends.add(friend);
        friendsList.getSelectionModel().select(friend);
    }

    public ObservableList<Friend> getFriends() {
        return friends.get();
    }

    public ListProperty<Friend> friendsProperty() {
        return friends;
    }

}