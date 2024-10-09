package Backend;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/*******
 * <p> Admin Class </p>
 *
 * <p> Description: This class defines the admin role in the system.
 * It extends the User class and adds functionalities specific to
 * managing other users, such as inviting users, resetting passwords,
 * and modifying user roles. </p>
 *
 * <p> Admins have elevated privileges in managing the help system,
 * including the ability to delete accounts, list users, and adjust roles. </p>
 *
 * @version 1.00, 2024-10-09
 * @author Team - Th15
 *
 */

public class Admin extends User {

	/** Default constructor */
    public Admin() {
        super();  // Calls the default constructor of User
        this.isAdmin = true;  // Set Admin-specific roles
    }

	/** Parameterized constructor
	 *
	 * This constructor initializes the admin account with specific details
	 * such as username, password, email, and name information.
	 */
    public Admin(String username, String password, String email, String firstName, String middleName, String lastName,String preferredName) {
		super(username, password, email, firstName, middleName, lastName,preferredName);  // Calls the parameterized constructor of User
        this.isAdmin = true;  // Set Admin-specific roles
    }

	// This method will handle inviting a user to the system
    public void inviteUser(String Firstname, String middleName, String lastName,String role)
    {

    }

	// This method resets the password of a specific user. The user's password will be nullified.
	// Returns true if successful, false if the user is not found.
    public boolean resetUserPassword(String username,UserList UserL)
    {
		if(UserL.findUser(username) == null)
		{
			return false;
		}
		UserL.findUser(username).setPassword(null);
		return true;
    }

	// Removes a specific user's account from the user list.
    public void deleteUserAccount(User user,UserList userL)
    {
    	userL.removeUser(user);
    }

	// Displays a list of all users registered in the system.
    public void listUserAccounts(UserList UserL)
	{
		UserL.ListUsers();
	}

	// This method assigns a specific role (e.g., Student, Instructor) to a user.
    public void addRoleToUser(User user, String role)
    {
    	if(role.equalsIgnoreCase("Student"))
    	{
    		user.isStudent = true;
    	}
    	if(role.equalsIgnoreCase("Instructor"))
    	{
    		user.isInstructor = true;
    	}
    }

	// This method removes a specific role (e.g., Student, Instructor) from a user.
    public void removeRoleFromUser(User user, String role) {
		if (role.equalsIgnoreCase("Student")) {
			user.isStudent = false; // remove student role
		}
		if (role.equalsIgnoreCase("Instructor")) {
			user.isInstructor = false; // remove instructor role
		}
	}
}