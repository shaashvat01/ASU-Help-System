package Backend;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test class for the Admin class.
 *
 * This class contains tests for the resetUserPassword method
 * in the Admin class, ensuring that password reset functionality
 * works as expected for both existing and non-existing users.
 */
class AdminTest {

    /**
     * Test case: Verify that the resetUserPassword method successfully resets
     * the password for an existing user in the UserList.
     */
    @Test
    void testResetUserPassword_UserExists() {
        Admin admin = new Admin(); // Create an Admin object
        UserList userList = new UserList(); // Create a UserList object

        // Add a test user to the UserList
        User user = new User("testUser", "password123", "test@example.com",
                "First", "Middle", "Last", "Preferred");
        userList.addUser(user); // Add the user to the list

        // Attempt to reset the password for the test user
        boolean result = admin.resetUserPassword("testUser", userList);

        // Verify that the password reset was successful
        assertTrue(result, "Password should be reset successfully for existing user");
        assertNull(user.getPassword(), "Password should be null after reset");
    }

    /**
     * Test case: Verify that the resetUserPassword method fails
     * to reset the password for a non-existent user in the UserList.
     */
    @Test
    void testResetUserPassword_UserDoesNotExist() {
        Admin admin = new Admin(); // Create an Admin object
        UserList userList = new UserList(); // Create a UserList object

        // Attempt to reset the password for a non-existent user
        boolean result = admin.resetUserPassword("nonExistentUser", userList);

        // Verify that the password reset was unsuccessful
        assertFalse(result, "Password reset should fail for non-existent user");
    }

    @Test
    void testDeleteUserAccount_UserExists() {
        Admin admin = new Admin(); // Create an Admin object
        UserList userList = new UserList(); // Create a UserList object

        // Add a test user to the UserList
        User user = new User("testUser", "password123", "test@example.com",
                "First", "Middle", "Last", "Preferred");
        userList.addUser(user); // Add the user to the list

        // Delete the user account
        admin.deleteUserAccount(user, userList);

        // Verify that the user has been removed
        assertEquals(0, userList.getUserCount(), "User should be removed from UserList");
        assertNull(userList.findUser("testUser"), "User should no longer exist in UserList");
    }

    /**
     * Test case: Verify that addRoleToUser assigns the correct role
     * to the specified user.
     */
    @Test
    void testAddRoleToUser_AssignRoles() {
        Admin admin = new Admin(); // Create an Admin object
        User user = new User("testUser", "password123", "test@example.com",
                "First", "Middle", "Last", "Preferred");

        // Assign roles to the user
        admin.addRoleToUser(user, "Student");
        admin.addRoleToUser(user, "Instructor");

        // Verify that the roles were assigned correctly
        assertTrue(user.isStudent(), "User should have the Student role");
        assertTrue(user.isInstructor(), "User should have the Instructor role");
    }

    /**
     * Test case: Verify that removeRoleFromUser removes the correct role
     * from the specified user.
     */
    @Test
    void testRemoveRoleFromUser_RemoveRoles() {
        Admin admin = new Admin(); // Create an Admin object
        User user = new User("testUser", "password123", "test@example.com",
                "First", "Middle", "Last", "Preferred");

        // Assign roles to the user and then remove them
        admin.addRoleToUser(user, "Student");
        admin.addRoleToUser(user, "Instructor");
        admin.removeRoleFromUser(user, "Student");
        admin.removeRoleFromUser(user, "Instructor");

        // Verify that the roles were removed correctly
        assertFalse(user.isStudent(), "User should no longer have the Student role");
        assertFalse(user.isInstructor(), "User should no longer have the Instructor role");
    }
}
