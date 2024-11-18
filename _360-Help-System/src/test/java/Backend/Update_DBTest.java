package Backend;

import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List; // Import the List class
import static org.junit.jupiter.api.Assertions.*;

class Update_DBTest {

    private final Update_DB updateDB = new Update_DB();

    @Test
    void testIsFileUnique() {
        // Test for a unique file name
        assertTrue(updateDB.isFileUnique("UniqueFile"), "File should be marked as unique");

        // Test for a non-unique file name
        assertFalse(updateDB.isFileUnique("Users-DB"), "File should be marked as non-unique");
    }

    @Test
    void testGenerateKey() throws Exception {
        // Test that a key is successfully generated
        SecretKey key = updateDB.generateKey();
        assertNotNull(key, "Generated key should not be null");
        assertEquals("AES", key.getAlgorithm(), "Key algorithm should be AES");
    }
}
