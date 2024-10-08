package Backend;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Admin extends User {
    
    
    public Admin() {
        super();  // Calls the default constructor of User
        this.isAdmin = true;  // Set Admin-specific roles
    }
    
    public Admin(String username, String password, String email, String firstName, String middleName, String lastName,String preferredName) {
		super(username, password, email, firstName, middleName, lastName,preferredName);  // Calls the parameterized constructor of User
        this.isAdmin = true;  // Set Admin-specific roles
    }
    
    public void inviteUser(String Firstname, String middleName, String lastName,String role)
    {

    }
    
    public boolean resetUserPassword(String username,UserList UserL)
    {
		if(UserL.findUser(username) == null)
		{
			return false;
		}
		UserL.findUser(username).setPassword(null);


		return true;
    }
    
    public void deleteUserAccount(User user,UserList userL)
    {
    	userL.removeUser(user);
    }
    
    public void listUserAccounts(UserList UserL)
	{
		UserL.ListUsers();
	}
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
    
    public void removeRoleFromUser(User user, String role)
    {
    	if(role.equalsIgnoreCase("Student"))
    	{
    		user.isStudent = false;
    	}
    	if(role.equalsIgnoreCase("Instructor"))
    	{
    		user.isInstructor = false;
    	}

    }
    
}