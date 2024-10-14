package dad.misamigos.controllers;

import dad.misamigos.model.Friend;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FriendController implements Initializable {

    private static final Image NO_PHOTO = new Image("/images/friend.png");

    // model

    private final ObjectProperty<Friend> friend = new SimpleObjectProperty<>();

    // view

    @FXML
    private DatePicker birthDatePicker;

    @FXML
    private TextField emailText;

    @FXML
    private TextField nameText;

    @FXML
    private TextField phoneText;

    @FXML
    private ImageView photoView;

    @FXML
    private GridPane root;

    @FXML
    private TextField surnameText;

    public FriendController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/FriendView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        friend.addListener(this::onFriendChanged);



    }

    private void onFriendChanged(ObservableValue<? extends Friend> o, Friend oldValue, Friend newValue) {

        if (oldValue != null) {

            nameText.textProperty().unbindBidirectional(oldValue.nameProperty());
            surnameText.textProperty().unbindBidirectional(oldValue.surnameProperty());
            birthDatePicker.valueProperty().unbindBidirectional(oldValue.birthDateProperty());
            phoneText.textProperty().unbindBidirectional(oldValue.phoneNumberProperty());
            emailText.textProperty().unbindBidirectional(oldValue.emailProperty());
            photoView.imageProperty().unbind();

        }

        if (newValue != null) {

            nameText.textProperty().bindBidirectional(newValue.nameProperty());
            surnameText.textProperty().bindBidirectional(newValue.surnameProperty());
            birthDatePicker.valueProperty().bindBidirectional(newValue.birthDateProperty());
            phoneText.textProperty().bindBidirectional(newValue.phoneNumberProperty());
            emailText.textProperty().bindBidirectional(newValue.emailProperty());
            photoView.imageProperty().bind(
                    Bindings
                            .when(newValue.photoProperty().isNull())
                            .then(NO_PHOTO)
                            .otherwise(newValue.photoProperty())
            );

        }

    }

    public GridPane getRoot() {
        return root;
    }

    @FXML
    void onPhotoAction(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar foto");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Imagen", "*.png", "*.jpg", "*.jpeg"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Todos los archivos", "*.*"));
        File imageFile = fileChooser.showOpenDialog(getRoot().getScene().getWindow());
        if (imageFile != null) {
            Image photo = new Image(imageFile.toURI().toString());
            friend.get().setPhoto(photo);
        }

    }

    public Friend getFriend() {
        return friend.get();
    }

    public ObjectProperty<Friend> friendProperty() {
        return friend;
    }

    public void setFriend(Friend friend) {
        this.friend.set(friend);
    }

}
