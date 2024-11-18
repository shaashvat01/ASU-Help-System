package Backend;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordEvaluatorTest {

    private PasswordField passwordField;
    private Label feedbackLabel;
    private PasswordEvaluator evaluator;

    // This is required because there are JavaFX components like PasswordField and Label which
    // are required to perform these tests.
    @BeforeAll
    static void initToolkit() {
        // Initialize JavaFX Toolkit
        Platform.startup(() -> {});
    }

    // This is us to provide new PasswordField and Labels for each test cases making them more effective
    @BeforeEach
    void setUp() {
        // Create real instances of PasswordField and Label for testing
        passwordField = new PasswordField();
        feedbackLabel = new Label();

        // Initialize PasswordEvaluator with these components
        evaluator = new PasswordEvaluator(passwordField, feedbackLabel);
    }

    // Verifies that the checkPassword method correctly identifies a valid password
    @Test
    void testValidPassword() {
        String password = "Strong@123";
        assertTrue(evaluator.checkPassword(password), "Valid password should return true");
    }

    // Verifies the method returns false when the password lacks an uppercase letter.
    @Test
    void testPasswordMissingUppercase() {
        String password = "weak@123";
        assertFalse(evaluator.checkPassword(password), "Password missing an uppercase letter should return false");
    }

    // Verifies the method returns false when the password lacks a lowercase letter.
    @Test
    void testPasswordMissingLowercase() {
        String password = "WEAK@123";
        assertFalse(evaluator.checkPassword(password), "Password missing a lowercase letter should return false");
    }

    // Verifies the method returns false when the password lacks a digit.
    @Test
    void testPasswordMissingDigit() {
        String password = "Weak@Pass";
        assertFalse(evaluator.checkPassword(password), "Password missing a numeric digit should return false");
    }

    // Verifies the method returns false when the password lacks a special character.
    @Test
    void testPasswordMissingSpecialChar() {
        String password = "Weak1234";
        assertFalse(evaluator.checkPassword(password), "Password missing a special character should return false");
    }

    // Verifies the method returns false when the password length is too short
    @Test
    void testPasswordTooShort() {
        String password = "W@1a";
        assertFalse(evaluator.checkPassword(password), "Password with less than 8 characters should return false");
    }

    // Verifies the method returns false when the password is not given.
    @Test
    void testEmptyPassword() {
        String password = "";
        assertFalse(evaluator.checkPassword(password), "Empty password should return false");
    }

    //Verifies the method returns false when the password fails all criteria.
    @Test
    void testAllInvalidCriteria() {
        String password = "weak";
        assertFalse(evaluator.checkPassword(password), "Password violating all criteria should return false");
    }
}
