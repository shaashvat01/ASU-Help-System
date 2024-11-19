package Backend;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test class for the PasswordEvaluator class.
 *
 * This class validates the password evaluation logic by testing various
 * password strength criteria such as length, uppercase, lowercase,
 * numeric digits, and special characters.
 */
class PasswordEvaluatorTest {

    private PasswordField passwordField; // Input field for password
    private Label feedbackLabel;        // Label for feedback messages
    private PasswordEvaluator evaluator; // Instance of PasswordEvaluator

    /**
     * Initializes the JavaFX Toolkit required for testing JavaFX components
     * like PasswordField and Label.
     */
    @BeforeAll
    static void initToolkit() {
        Platform.startup(() -> {});
    }

    /**
     * Sets up the test environment before each test by creating fresh
     * instances of PasswordField, Label, and PasswordEvaluator.
     */
    @BeforeEach
    void setUp() {
        passwordField = new PasswordField();
        feedbackLabel = new Label();
        evaluator = new PasswordEvaluator(passwordField, feedbackLabel);
    }

    /**
     * Test case: Verifies that a valid password passes the evaluation.
     */
    @Test
    void testValidPassword() {
        String password = "Strong@123";
        assertTrue(evaluator.checkPassword(password), "Valid password should return true");
    }

    /**
     * Test case: Verifies that a password missing an uppercase letter fails the evaluation.
     */
    @Test
    void testPasswordMissingUppercase() {
        String password = "weak@123";
        assertFalse(evaluator.checkPassword(password), "Password missing an uppercase letter should return false");
    }

    /**
     * Test case: Verifies that a password missing a lowercase letter fails the evaluation.
     */
    @Test
    void testPasswordMissingLowercase() {
        String password = "WEAK@123";
        assertFalse(evaluator.checkPassword(password), "Password missing a lowercase letter should return false");
    }

    /**
     * Test case: Verifies that a password missing a numeric digit fails the evaluation.
     */
    @Test
    void testPasswordMissingDigit() {
        String password = "Weak@Pass";
        assertFalse(evaluator.checkPassword(password), "Password missing a numeric digit should return false");
    }

    /**
     * Test case: Verifies that a password missing a special character fails the evaluation.
     */
    @Test
    void testPasswordMissingSpecialChar() {
        String password = "Weak1234";
        assertFalse(evaluator.checkPassword(password), "Password missing a special character should return false");
    }

    /**
     * Test case: Verifies that a password shorter than the required length fails the evaluation.
     */
    @Test
    void testPasswordTooShort() {
        String password = "W@1a";
        assertFalse(evaluator.checkPassword(password), "Password with less than 8 characters should return false");
    }

    /**
     * Test case: Verifies that an empty password fails the evaluation.
     */
    @Test
    void testEmptyPassword() {
        String password = "";
        assertFalse(evaluator.checkPassword(password), "Empty password should return false");
    }

    /**
     * Test case: Verifies that a password violating all criteria fails the evaluation.
     */
    @Test
    void testAllInvalidCriteria() {
        String password = "weak";
        assertFalse(evaluator.checkPassword(password), "Password violating all criteria should return false");
    }
}
