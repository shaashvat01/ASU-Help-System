package Backend;

import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List; // Import the List class
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test class for the Update_DB class.
 *
 * This class verifies the functionality of the Update_DB class, focusing
 * on file uniqueness checks and key generation.
 */
class Update_DBTest {

    // Create an instance of Update_DB to use in the test cases
    private final Update_DB updateDB = new Update_DB();

    /**
     * Test case: Verifies the behavior of the isFileUnique method.
     *
     * Ensures that the method correctly identifies unique and non-unique file names.
     */
    @Test
    void testIsFileUnique() {
        // Test for a unique file name
        assertTrue(updateDB.isFileUnique("UniqueFile"),
                "File should be marked as unique");

        // Test for a non-unique file name
        assertFalse(updateDB.isFileUnique("Users-DB"),
                "File should be marked as non-unique");
    }

    /**
     * Test case: Verifies the behavior of the generateKey method.
     *
     * Ensures that the method successfully generates a valid AES secret key.
     *
     * @throws Exception if key generation fails unexpectedly.
     */
    @Test
    void testGenerateKey() throws Exception {
        // Test that a key is successfully generated
        SecretKey key = updateDB.generateKey();

        // Assertions to validate the generated key
        assertNotNull(key, "Generated key should not be null");
        assertEquals("AES", key.getAlgorithm(), "Key algorithm should be AES");
    }
}
