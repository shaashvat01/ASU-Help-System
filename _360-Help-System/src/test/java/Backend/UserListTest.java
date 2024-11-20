package Backend;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.LinkedList;

/**
 * Unit test class for the UserList class.
 *
 * This class verifies the functionality of the UserList class,
 * including adding, removing, and retrieving users.
 */
public class UserListTest {

    /**
     * Test case: Verifies that a user can be successfully added to the list.
     */
    @Test
    public void testAddUser() {
        UserList userList = new UserList();
        User user = new User(
                "username",
                "password",
                "email@example.com",
                "John",
                "A.",
                "Doe",
                "Johnny"
        );
        userList.addUser(user);

        // Ensure the user count increases to 1
        assertEquals(1, userList.getUserCount(), "User count should be 1 after adding a user");
    }

    /**
     * Test case: Verifies that a user can be successfully removed from the list.
     */
    @Test
    public void testRemoveUser() {
        UserList userList = new UserList();
        User user = new User(
                "username",
                "password",
                "email@example.com",
                "John",
                "A.",
                "Doe",
                "Johnny"
        );
        userList.addUser(user);

        // Ensure the user can be removed and the count decreases to 0
        assertTrue(userList.removeUser(user), "User should be successfully removed");
        assertEquals(0, userList.getUserCount(), "User count should be 0 after removing the user");
    }

    /**
     * Test case: Verifies behavior when attempting to remove a user that does not exist.
     */
    @Test
    public void testRemoveNonExistentUser() {
        UserList userList = new UserList();
        User user = new User(
                "username",
                "password",
                "email@example.com",
                "John",
                "A.",
                "Doe",
                "Johnny"
        );

        // Ensure the removal fails
        assertFalse(userList.removeUser(user), "Removing a non-existent user should return false");
    }

    /**
     * Test case: Verifies the user count functionality of the list.
     */
    @Test
    public void testGetUserCount() {
        UserList userList = new UserList();

        // Initially, the count should be 0
        assertEquals(0, userList.getUserCount(), "User count should be 0 initially");

        User user = new User(
                "username",
                "password",
                "email@example.com",
                "John",
                "A.",
                "Doe",
                "Johnny"
        );
        userList.addUser(user);

        // After adding a user, the count should increase to 1
        assertEquals(1, userList.getUserCount(), "User count should be 1 after adding a user");
    }

    /**
     * Test case: Verifies that a user can be found by username.
     */
    @Test
    public void testFindUser() {
        UserList userList = new UserList();
        User user = new User(
                "username",
                "password",
                "email@example.com",
                "John",
                "A.",
                "Doe",
                "Johnny"
        );
        userList.addUser(user);

        User foundUser = userList.findUser("username");

        // Ensure the correct user is found
        assertNotNull(foundUser, "Found user should not be null");
        assertEquals("username", foundUser.getUserName(), "Found user should have the correct username");
    }

    /**
     * Test case: Verifies behavior when a user is not found by username.
     */
    @Test
    public void testFindUserNotFound() {
        UserList userList = new UserList();
        User user = new User(
                "username",
                "password",
                "email@example.com",
                "John",
                "A.",
                "Doe",
                "Johnny"
        );
        userList.addUser(user);

        // Ensure no user is found with a nonexistent username
        assertNull(userList.findUser("nonexistent"), "Finding a non-existent user should return null");
    }

    /**
     * Test case: Verifies that a user can be found by OTP.
     */
    @Test
    public void testFindUserByOTP() {
        UserList userList = new UserList();
        User user = new User(
                "username",
                "password",
                "email@example.com",
                "John",
                "A.",
                "Doe",
                "Johnny"
        );
        user.setAccOTP(12345);
        userList.addUser(user);

        User foundUser = userList.findUserByOTP(12345);

        // Ensure the correct user is found by OTP
        assertNotNull(foundUser, "Found user should not be null");
        assertEquals(12345, foundUser.getAccOTP(), "Found user should have the correct OTP");
    }

    /**
     * Test case: Verifies behavior when a user is not found by OTP.
     */
    @Test
    public void testFindUserByOTPNotFound() {
        UserList userList = new UserList();
        User user = new User(
                "username",
                "password",
                "email@example.com",
                "John",
                "A.",
                "Doe",
                "Johnny"
        );
        user.setAccOTP(12345);
        userList.addUser(user);

        // Ensure no user is found with a nonexistent OTP
        assertNull(userList.findUserByOTP(67890), "Finding a user with a non-existent OTP should return null");
    }

    /**
     * Test case: Verifies the retrieval of the entire user list.
     */
    @Test
    public void testGetUserList() {
        UserList userList = new UserList();
        User user = new User(
                "username",
                "password",
                "email@example.com",
                "John",
                "A.",
                "Doe",
                "Johnny"
        );
        userList.addUser(user);

        LinkedList<User> users = userList.getUserList();

        // Ensure the user list is retrieved correctly
        assertNotNull(users, "User list should not be null");
        assertEquals(1, users.size(), "User list should contain one user");
        assertEquals("username", users.getFirst().getUserName(), "First user's username should match");
    }

    /**
     * Test case: Verifies the toString method outputs user details as expected.
     */
    @Test
    public void testToString() {
        UserList userList = new UserList();
        User user = new User(
                "username",
                "password",
                "email@example.com",
                "John",
                "A.",
                "Doe",
                "Johnny"
        );
        userList.addUser(user);

        String output = userList.toString();

        // Ensure the toString output contains key user information
        assertTrue(output.contains("username"), "Output should contain the username");
        assertTrue(output.contains("password"), "Output should contain the password");
        assertTrue(output.contains("email@example.com"), "Output should contain the email");
    }
}
