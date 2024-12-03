package Testing;

import Backend.User;
import Backend.UserList;

public class UserListTester {

    static int numPassed = 0;
    static int numFailed = 0;

    public static void main(String[] args) {
        System.out.println("____________________________________________________________________________");
        System.out.println("\nTesting UserList Functionality with Role Assignment");

        // Create UserList instance for testing
        UserList userList = new UserList();

        // Perform test cases
        performTestCaseAddUserWithRole(1, "student_user", "Password123", "student@example.com", "Student", userList);
        performTestCaseAddUserWithRole(2, "instructor_user", "Instructor@123", "instructor@example.com", "Instructor", userList);
        performTestCaseAddUserWithRole(3, "admin_user", "Admin@123", "admin@example.com", "Admin", userList);
        performTestCaseRemoveUser(4, "student_user", userList);

        System.out.println("____________________________________________________________________________");
        System.out.println();
        System.out.println("Number of tests passed: " + numPassed);
        System.out.println("Number of tests failed: " + numFailed);
    }

    /**
     * Test case for adding a user to the UserList with a single role assignment.
     */
    private static void performTestCaseAddUserWithRole(int testCase, String username, String password, String email, String role, UserList userList) {
        System.out.println("____________________________________________________________________________\n\nTest case (Add User with Role): " + testCase);
        System.out.println("Input Details:");
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
        System.out.println("Email: " + email);
        System.out.println("Role: " + role);
        System.out.println("______________");

        // Create a new user with the role-specific constructor
        User user = new User(username, password, email, "John", "A.", "Doe", "Johnny");

        // Assign the specific role
        if ("Student".equalsIgnoreCase(role)) {
            user.setStudent(true);
        } else if ("Instructor".equalsIgnoreCase(role)) {
            user.setInstructor(true);
        }  else {
            System.out.println("***Failure*** Invalid role specified: " + role);
            numFailed++;
            return;
        }

        // Add the user to the list
        userList.addUser(user);

        // Validate the user is added
        boolean isUserAdded = userList.findUser(username) != null;
        int userCount = userList.getUserCount();

        if (isUserAdded && userCount > 0) {
            System.out.println("***Success*** The user <" + username + "> with role <" + role + "> was added successfully.");
            numPassed++;
        } else {
            System.out.println("***Failure*** The user <" + username + "> was not added successfully.");
            numFailed++;
        }

        System.out.println("Current User List: " + userList.toString());
        System.out.println("Number of users in the list: " + userCount);
    }

    /**
     * Test case for removing a user from the UserList.
     */
    private static void performTestCaseRemoveUser(int testCase, String username, UserList userList) {
        System.out.println("____________________________________________________________________________\n\nTest case (Remove User): " + testCase);
        System.out.println("Username to Remove: " + username);
        System.out.println("______________");

        // Find the user to be removed
        User user = userList.findUser(username);

        if (user != null) {
            // Remove the user
            boolean isRemoved = userList.removeUser(user);
            boolean isStillPresent = userList.findUser(username) != null;

            if (isRemoved && !isStillPresent) {
                System.out.println("***Success*** The user <" + username + "> was removed successfully.");
                numPassed++;
            } else {
                System.out.println("***Failure*** The user <" + username + "> removal failed.");
                numFailed++;
            }
        } else {
            System.out.println("***Failure*** The user <" + username + "> does not exist in the list, so it cannot be removed.");
            numFailed++;
        }

        System.out.println("Current User List: " + userList.toString());
        System.out.println("Number of users in the list: " + userList.getUserCount());
    }
}