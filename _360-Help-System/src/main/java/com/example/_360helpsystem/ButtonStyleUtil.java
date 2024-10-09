package com.example._360helpsystem;

import javafx.scene.control.Button;

/*******
 * <p> ButtonStyleUtil Class </p>
 *
 * <p> Description: This utility class provides styling methods for JavaFX buttons.
 * It includes methods to create buttons with specific styles such as circular buttons. </p>
 *
 * @version 1.00, 2024-10-09
 * @author Team - Th15
 *
 */

public class ButtonStyleUtil {
    // Method to create a styled circular back button
    public static Button createCircularBackButton() {
        Button backButton = new Button("←");
        backButton.setStyle(
                "-fx-background-color: #8B0000;" +  // Background color dark red
                        "-fx-text-fill: white;" +            // White text fill
                        "-fx-background-radius: 50%;" +      // Make it circular by setting background radius to 50%
                        "-fx-min-width: 30px;" +             // Minimum width
                        "-fx-min-height: 30px;" +            // Minimum height
                        "-fx-max-width: 30px;" +             // Maximum width (to keep it consistent)
                        "-fx-max-height: 30px;"              // Maximum height
        );
        return backButton;
    }
}
