package abuhurairah.ui;

import abuhurairah.*;
import abuhurairah.storage.Storage;
import abuhurairah.task.TaskList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private AbuHurairah abuHurairah;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image abuHurairahImage = new Image(this.getClass().getResourceAsStream("/images/DaAbuhurairah.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        // handle welcome
    }

    /** Injects the Abuhurairah instance */
    public void setAbuHurairah(AbuHurairah d) {
        abuHurairah = d;
    }

    public void start() {
        Storage storage = abuHurairah.getStorage();
        TaskList taskList = abuHurairah.getTaskList();
        String name = abuHurairah.getName();
        storage.getStore(taskList);

        // Display stored tasks and welcome message
        if (!taskList.getTasks().isEmpty()) {
            String taskHistory = taskList.printStoredList();
            dialogContainer.getChildren().add(
                    DialogBox.getAbuHurairahDialog(taskHistory + Ui.showWelcomeBack(name), abuHurairahImage)
            );
        } else {
            dialogContainer.getChildren().add(
                    DialogBox.getAbuHurairahDialog(Ui.showWelcome(name), abuHurairahImage)
            );
        }
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();

        // handle bye
        TaskList taskList = abuHurairah.getTaskList();
        Storage storage = abuHurairah.getStorage();
        if (taskList.isBye(input)) {
            if (!taskList.isEmpty()) {
                storage.store(taskList);
            }
            dialogContainer.getChildren().addAll(
                    DialogBox.getAbuHurairahDialog(Ui.bye(), abuHurairahImage)
            );
            userInput.clear();
            javafx.application.Platform.exit();
        }

        String response = abuHurairah.getResponse(input, taskList);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getAbuHurairahDialog(response, abuHurairahImage)
        );
        userInput.clear();
    }
}

