package Backend;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.paint.Color;

/*******
 * <p> PasswordEvaluator Class </p>
 *
 * <p> Description: This class evaluates passwords based on various criteria such as
 * the presence of uppercase letters, lowercase letters, digits, special characters,
 * and the password length. Feedback is provided in real time as the user types. </p>
 *
 * @version 1.00, 2024-10-09
 * @author Team - Th15
 *
 */

public class PasswordEvaluator {

    private PasswordField passwordField;
    private Label feedbackLabel;

    // Initializes the PasswordEvaluator with the given password field and feedback label. Adds a listener to trigger real-time password evaluation.
    public PasswordEvaluator(PasswordField passwordField, Label feedbackLabel) {
        this.passwordField = passwordField;
        this.feedbackLabel = feedbackLabel;

        // Add listener to the password field to trigger real-time evaluation
        this.passwordField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                evaluatePassword(newValue);
            }
        });
    }

    // Evaluates the password based on the criteria and updates the feedback label with the results. Returns true if all criteria are met, otherwise false.
    private boolean evaluatePassword(String password) {
        StringBuilder feedback = new StringBuilder();
        boolean isValid = true;

        // Check for uppercase letter
        if (!password.matches(".*[A-Z].*")) {
            feedback.append("At least one uppercase letter - Not satisfied\n");
            isValid = false;
        }
        else {
            feedback.append("At least one uppercase letter - Satisfied\n");
        }

        // Check for lowercase letter
        if (!password.matches(".*[a-z].*")) {
            feedback.append("At least one lowercase letter - Not satisfied\n");
            isValid = false;
        }
        else {
            feedback.append("At least one lowercase letter - Satisfied\n");
        }

        // Check for digit
        if (!password.matches(".*[0-9].*")) {
            feedback.append("At least one numeric digit - Not satisfied\n");
            isValid = false;
        }
        else {
            feedback.append("At least one numeric digit - Satisfied\n");
        }

        // Check for special character
        if (!password.matches(".*[!@#$%^&*()_+\\-\\[\\]{};':\"\\\\|,.<>/?].*")) {
            feedback.append("At least one special character - Not satisfied\n");
            isValid = false;
        }
        else {
            feedback.append("At least one special character - Satisfied\n");
        }

        // Check for length of at least 8 characters
        if (password.length() < 8) {
            feedback.append("At least eight characters - Not satisfied\n");
            isValid = false;
        }
        else {
            feedback.append("At least eight characters - Satisfied\n");
        }

        // Update feedback label text and color
        feedbackLabel.setText(feedback.toString());

        if (isValid) {
            feedbackLabel.setTextFill(Color.GREEN);  // Set text color to green when all criteria are met
            return true;
        } else {
            feedbackLabel.setTextFill(Color.RED);    // Set text color to red when any criteria are not met
            return false;
        }
    }

    // Checks if the password meets all criteria (uppercase, lowercase, digit, special character, and length). Returns true if all conditions are satisfied.
    public boolean checkPassword(String password) {
        boolean hasUppercase = password.matches(".*[A-Z].*");
        boolean hasLowercase = password.matches(".*[a-z].*");
        boolean hasDigit = password.matches(".*[0-9].*");
        boolean hasSpecialChar = password.matches(".*[!@#$%^&*()_+\\-\\[\\]{};':\"\\\\|,.<>/?].*");
        boolean isAtLeastEight = password.length() >= 8;

        // Output feedback for debugging
        if (hasUppercase) {
            System.out.println("Has capital letter");
        } else {
            System.out.println("No capital letter");
        }

        if (hasLowercase) {
            System.out.println("Has lowercase letter");
        } else {
            System.out.println("No lowercase letter");
        }

        if (hasDigit) {
            System.out.println("Has number");
        } else {
            System.out.println("No number");
        }

        if (hasSpecialChar) {
            System.out.println("Has special character");
        } else {
            System.out.println("No special character");
        }

        if (isAtLeastEight) {
            System.out.println("Has at least 8 characters");
        } else {
            System.out.println("Less than 8 characters");
        }

        // Return true only if all conditions are satisfied
        return hasUppercase && hasLowercase && hasDigit && hasSpecialChar && isAtLeastEight;
    }
}
