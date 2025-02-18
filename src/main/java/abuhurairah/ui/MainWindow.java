package abuhurairah.ui;

import abuhurairah.AbuHurairah;
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

    /**
     * Creates a scrolling pane window
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Abuhurairah instance */
    public void setAbuHurairah(AbuHurairah d) {
        abuHurairah = d;
    }

    /**
     * Starts the main window by retrieving and displaying the task list and welcome message.
     * It checks if there are tasks in the list and shows appropriate welcome messages.
     */
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

    /**
     * Handles the user input, processing the text entered in the userInput field.
     * It processes the user's input, generates the response, and displays the dialog.
     */
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

