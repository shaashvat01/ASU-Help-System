package Backend;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AdminTest {
    @Test
    void testResetUserPassword_UserExists() {
        Admin admin = new Admin();
        UserList userList = new UserList();
        // Use the available parameterized constructor
        User user = new User("testUser", "password123", "test@example.com", "First", "Middle", "Last", "Preferred");
        userList.addUser(user);

        boolean result = admin.resetUserPassword("testUser", userList);

        assertTrue(result, "Password should be reset successfully for existing user");
        assertNull(user.getPassword(), "Password should be null after reset");
    }

    @Test
    void testResetUserPassword_UserDoesNotExist() {
        Admin admin = new Admin();
        UserList userList = new UserList();

        boolean result = admin.resetUserPassword("nonExistentUser", userList);

        assertFalse(result, "Password reset should fail for non-existent user");
    }
}
