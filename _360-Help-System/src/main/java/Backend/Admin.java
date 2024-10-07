package Backend;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Admin extends User {
    
    
    public Admin() {
        super();  // Calls the default constructor of User
        this.isAdmin = true;  // Set Admin-specific roles
    }
    
    public Admin(String username, String password, String email, String firstName, String middleName, String lastName) {
        super(username, password, email, firstName, middleName, lastName);  // Calls the parameterized constructor of User
        this.isAdmin = true;  // Set Admin-specific roles
    }
    
    public void inviteUser(String Firstname, String middleName, String lastName,String role)
    {
    	String userData = firstName + "-" + middleName + "-" + lastName + "-" + role;
    	
    	String file = "invites.txt";
    	
    	try(BufferedWriter bw = new BufferedWriter(new FileWriter(file,true)))
		{
    		bw.write(userData);
    		bw.newLine();
    	}
    	
    	catch (IOException e)
    	{
    		System.err.println("An error occurred while writing to the file: " + e.getMessage());
    	}
    	
    }
    
    public void resetUserPassword(User user)
    {
    	user.setPassword(""); //Need to expand from front end
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