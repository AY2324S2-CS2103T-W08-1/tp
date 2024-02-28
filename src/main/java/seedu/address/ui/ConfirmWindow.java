package seedu.address.ui;


import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;

/**
 * Confirmation window that warns the user that the database will be cleared.
 */
public class ConfirmWindow extends UiPart<Stage> {
    private static final String FXML = "ConfirmWindow.fxml";

    private static final String CONFIRM_MESSAGE = "Clearing the database is an irreversible action. Proceed? (Y/N)";
    private static final String SUCCESS_MESSAGE = "Database has been cleared!";
    private static final Logger logger = LogsCenter.getLogger(ConfirmWindow.class);
    private Model model;
    private Ui ui;
    @FXML
    private TextField confirmTextField;

    @FXML
    private Label confirmMessage;

    /**
     * Creates a new ConfirmWindow.
     *
     * @param root Stage to use as the root of the ConfirmWindow.
     */
    public ConfirmWindow(Stage root, Model model, Ui ui) {
        super(FXML, root);
        confirmMessage.setText(CONFIRM_MESSAGE);
        root.setOnCloseRequest(event -> ui.enableCommandBox());
        this.model = model;
        this.ui = ui;
    }

    /**
     * Creates a new HelpWindow.
     */
    public ConfirmWindow(Model model, Ui ui) {
        this(new Stage(), model, ui);
    }

    @FXML
    private void handleConfirmEntered() {
        String commandText = confirmTextField.getText();
        if (commandText.equals("Y")) {
            model.setAddressBook(new AddressBook());
            ui.setResultDisplayText(SUCCESS_MESSAGE);
        }
        getRoot().hide();
        ui.enableCommandBox();
        confirmTextField.setText("");
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
