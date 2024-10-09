package com.example._360helpsystem;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

/*******
 * <p> HelloController Class </p>
 *
 * <p> Description: This class serves as the controller for handling user interactions
 * in the HelloApplication. It updates the label text when the button is clicked. </p>
 *
 * @version 1.00, 2024-10-09
 * @author Team - Th15
 *
 */

public class HelloController {
    @FXML
    private Label welcomeText;
    // This method updates the welcome text when the button is clicked.
    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}