package ally.gui;

import ally.ui.Ally;
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

    private Ally ally;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private final Image allyImage = new Image(this.getClass().getResourceAsStream("/images/ally.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Ally instance */
    public void setAlly(Ally d) {
        ally = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Ally's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        try {
            String input = userInput.getText();
            System.out.println("Input received: " + input);

            String response = ally.getResponse(input);
            System.out.println("Response: " + response);

            String commandType = ally.getCommandType();
            System.out.println("Command type: " + commandType);

            if (response == null || commandType == null) {
                System.out.println("Warning: Null response or command type");
                return;
            }

            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getAllyDialog(response, allyImage, commandType)
            );
            userInput.clear();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error in handleUserInput: " + e.getMessage());
        }
    }
}
