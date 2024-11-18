package Backend;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UID_GeneratorTest {

    @Test
    void testGenerateUID_Range() {
        UID_Generator uidGenerator = new UID_Generator();

        // Generate a UID
        long uid = uidGenerator.getUID();

        // Assertions to validate the UID range
        assertTrue(uid >= 1 && uid <= Long.MAX_VALUE, "UID should be in the range of 1 to Long.MAX_VALUE");
    }

    @Test
    void testGenerateUID_Uniqueness() {
        UID_Generator uidGenerator = new UID_Generator();

        // Generate multiple UIDs
        long uid1 = uidGenerator.getUID();
        long uid2 = uidGenerator.getUID();

        // Ensure UIDs are likely unique (not guaranteed due to randomness but highly probable)
        assertNotEquals(uid1, uid2, "Two consecutive UIDs should not usually be the same");
    }
}
