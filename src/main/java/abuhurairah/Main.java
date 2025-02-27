package abuhurairah;

import java.io.IOException;

import abuhurairah.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private AbuHurairah abuHurairarh = new AbuHurairah();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setAbuHurairah(abuHurairarh); // inject the Duke instance
            stage.show();
            fxmlLoader.<MainWindow>getController().start(); // initiate bot
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
