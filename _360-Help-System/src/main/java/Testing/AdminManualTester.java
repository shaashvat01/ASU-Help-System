package Testing;

import Backend.Admin;
import Backend.User;
import Backend.UserList;

public class AdminManualTester {

    public static void main(String[] args) {
        System.out.println("===================================================================");
        System.out.println("Admin Manual Testing");

        // Step 1: Set up test data
        System.out.println("Initializing test data...");
        UserList userList = new UserList();
        User user1 = new User("john_doe", "password123", "john@example.com", "John", "", "Doe", "John");
        User user2 = new User("jane_doe", "password456", "jane@example.com", "Jane", "", "Doe", "Jane");
        userList.addUser(user1);
        userList.addUser(user2);

        Admin admin = new Admin();

        System.out.println("Test data initialized successfully.");
        System.out.println("===================================================================");

        // Test Case 1: Reset User Password
        System.out.println("\nTest Case 1: Reset User Password");
        String usernameToReset = "john_doe";
        System.out.println("Attempting to reset password for user: " + usernameToReset);
        boolean resetResult = admin.resetUserPassword(usernameToReset, userList);

        if (resetResult) {
            System.out.println("Password reset successful for user: " + usernameToReset);
            // Assuming getPassword() exists in the User class
            System.out.println("New Password for " + usernameToReset + ": " + user1.getPassword());
        } else {
            System.out.println("Password reset failed for user: " + usernameToReset);
        }
        System.out.println("===================================================================");

        // Test Case 2: Delete User Account
        System.out.println("\nTest Case 2: Delete User Account");
        System.out.println("Attempting to delete user: jane_doe");
        admin.deleteUserAccount(user2, userList);

        if (userList.findUser("jane_doe") == null) {
            System.out.println("User jane_doe deleted successfully.");
        } else {
            System.out.println("Failed to delete user jane_doe.");
        }
        System.out.println("===================================================================");

        // Test Case 3: Add Role to User
        System.out.println("\nTest Case 3: Add Role to User");
        System.out.println("Adding 'Student' role to user: john_doe");
        admin.addRoleToUser(user1, "Student");

        if (user1.isStudent()) {
            System.out.println("Role 'Student' successfully added to user: john_doe");
        } else {
            System.out.println("Failed to add role 'Student' to user: john_doe");
        }
        System.out.println("===================================================================");
    }
}
