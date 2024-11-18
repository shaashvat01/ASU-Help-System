package Backend;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.LinkedList;

public class UserListTest {

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

        assertEquals(1, userList.getUserCount());
    }

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

        assertTrue(userList.removeUser(user));
        assertEquals(0, userList.getUserCount());
    }

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

        assertFalse(userList.removeUser(user));
    }

    @Test
    public void testGetUserCount() {
        UserList userList = new UserList();
        assertEquals(0, userList.getUserCount());

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
        assertEquals(1, userList.getUserCount());
    }

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
        assertNotNull(foundUser);
        assertEquals("username", foundUser.getUserName());
    }

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

        assertNull(userList.findUser("nonexistent"));
    }

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
        assertNotNull(foundUser);
        assertEquals(12345, foundUser.getAccOTP());
    }

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

        assertNull(userList.findUserByOTP(67890));
    }

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
        assertNotNull(users);
        assertEquals(1, users.size());
        assertEquals("username", users.getFirst().getUserName());
    }

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
        assertTrue(output.contains("username"));
        assertTrue(output.contains("password"));
        assertTrue(output.contains("email@example.com"));
    }
}
