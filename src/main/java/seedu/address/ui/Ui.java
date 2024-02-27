package seedu.address.ui;

import javafx.stage.Stage;

/**
 * API of UI component
 */
public interface Ui {

    /** Starts the UI (and the App).  */
    void start(Stage primaryStage);

    void enableCommandBox();
    void disableCommandBox();

    void setResultDisplayText(String s);
}
