package com.example._360helpsystem;

import javafx.scene.control.Button;

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

    // You can add more styling methods for different button styles if needed
}
