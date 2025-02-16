package ally.gui;

import java.io.IOException;

import ally.ui.Ally;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Ally using FXML.
 */
public class Main extends Application {

    private final Ally ally = new Ally();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            stage.setTitle("Ally");
            Image appIcon = new Image("/images/ally.png");
            stage.getIcons().add(appIcon);
            fxmlLoader.<MainWindow>getController().setAlly(ally);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
