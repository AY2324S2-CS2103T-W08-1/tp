package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;

import java.util.logging.Logger;

public class ConfirmWindow extends UiPart<Stage> {
    private static final String FXML = "ConfirmWindow.fxml";

    private static final String CONFIRM_MESSAGE = "Clearing the database is an irreversible action. Proceed? (Y/N)";
    private static final Logger logger = LogsCenter.getLogger(ConfirmWindow.class);
    private Model model;
    @FXML
    private TextField confirmTextField;

    @FXML
    private Label confirmMessage;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public ConfirmWindow(Stage root, Model model) {
        super(FXML, root);
        confirmMessage.setText(CONFIRM_MESSAGE);
        this.model = model;
    }

    /**
     * Creates a new HelpWindow.
     */
    public ConfirmWindow(Model model) {
        this(new Stage(), model);
    }

    @FXML
    private void handleConfirmEntered() {
        String commandText = confirmTextField.getText();
        if (commandText.equals("Y")) {
            model.setAddressBook(new AddressBook());
        }
        getRoot().hide();
    }


    /**
     * Shows the confirmation window.
     * @throws IllegalStateException
     *     <ul>
     *         <li>
     *             if this method is called on a thread other than the JavaFX Application Thread.
     *         </li>
     *         <li>
     *             if this method is called during animation or layout processing.
     *         </li>
     *         <li>
     *             if this method is called on the primary stage.
     *         </li>
     *         <li>
     *             if {@code dialogStage} is already showing.
     *         </li>
     *     </ul>
     */
    public void show() {
        logger.fine("Showing confirmation page to clear the database.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the confirmation window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    public void focus() {
        getRoot().requestFocus();
    }
}
