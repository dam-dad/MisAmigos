package dad.misamigos;

import com.google.gson.Gson;
import dad.misamigos.adapters.ImageAdapter;
import dad.misamigos.adapters.LocalDateAdapter;
import dad.misamigos.controllers.RootController;
import dad.misamigos.model.Friend;
import dad.misamigos.model.FriendsList;
import javafx.application.Application;
import javafx.beans.property.ListProperty;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.hildan.fxgson.FxGson;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.LocalDate;

public class MisAmigosApp extends Application {

    private static final File DATA_DIR = new File(System.getProperty("user.home"), ".MisAmigos");
    private static final File FRIENDS_FILE = new File(DATA_DIR, "friends.json");

    private final Gson gson = FxGson.fullBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .registerTypeAdapter(Image.class, new ImageAdapter())
            .create();

    private final RootController rootController = new RootController();

    @Override
    public void init() throws Exception {
        // cargar los amigos desde el fichero friends.json
        if (FRIENDS_FILE.exists()) {
            String json = Files.readString(FRIENDS_FILE.toPath(), StandardCharsets.UTF_8);
            ListProperty<Friend> friends = gson.fromJson(json, FriendsList.class);
            rootController.getFriends().setAll(friends);
            System.out.println(friends.size() + " contactos leídos desde el fichero " + FRIENDS_FILE);
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Mis Amigos");
        primaryStage.setScene(new Scene(rootController.getRoot()));
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        // guardar los amigos en el fichero friends.json
        if (!DATA_DIR.exists()) {
            DATA_DIR.mkdir();
        }
        String json = gson.toJson(rootController.getFriends());
        Files.writeString(FRIENDS_FILE.toPath(), json, StandardCharsets.UTF_8);
        System.out.println("Cambios guardados en el fichero " + FRIENDS_FILE);
    }

}
