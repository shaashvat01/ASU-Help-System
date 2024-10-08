package Backend;

import java.util.LinkedList;

public class UserList {

    // Linked list to store User objects
    private LinkedList<User> userL;

    // Constructor
    public UserList() {
        userL = new LinkedList<>(); // Initialize the LinkedList
    }

    // Add a new user to the list
    public void addUser(User user) {
        userL.add(user);
    }

    // Remove a user from the list 
    public boolean removeUser(User user) {
        if (userL.contains(user)) { // Check if user exists in the list
            userL.remove(user); // Remove the user
            return true;
        } 
        else 
        {
            return false;
        }
    }


    // Get the number of users in the list
    public int getUserCount() {
        return userL.size(); // Return the size of userL
    }

    // Print all users in the list
    public void ListUsers() 
    {
        if (userL.isEmpty()) 
        {
            System.out.println("No users in the list.");
        } 
        else 
        {
            for(User user : userL) { // Use userL to iterate over
                //Need to implement front end list
        }
        }
    }

    public User findUser(String username)
    {
        for (User user : this.userL) {
            if(user.username.equals(username))
            {
                return user;
            }
        }
        return null;
    }

    public LinkedList<User> getUserList() {
        return userL; // Return the entire list
    }

    //debug function
    public String toString() {
        StringBuilder sb = new StringBuilder(); // Create a StringBuilder to accumulate the string representation
        if (userL.isEmpty()) {
            sb.append("No users in the list.\n"); // Append a message if the list is empty
        } else {
            for (User user : userL) { // Iterate over each user in the list
                sb.append("Username: ").append(user.getUserName()).append("\n");
                sb.append("Password: ").append(user.getPassword()).append("\n");
                sb.append("Email: ").append(user.getEmail()).append("\n");
                sb.append("First Name: ").append(user.getFirstName()).append("\n");
                sb.append("Middle Name: ").append(user.getMiddleName()).append("\n");
                sb.append("Last Name: ").append(user.getLastName()).append("\n");
                sb.append("Preferred Name: ").append(user.getPreferredName()).append("\n");
                sb.append("Is Student: ").append(user.isStudent()).append("\n");
                sb.append("Is Admin: ").append(user.isAdmin()).append("\n");
                sb.append("Is Instructor: ").append(user.isInstructor()).append("\n");
                sb.append("Account Setup Complete: ").append(user.isAccountSetupDone()).append("\n");
                sb.append("Account Reset OTP: ").append(user.getAccResetOTP()).append("\n");
                sb.append("------------------------\n"); // Separator for each user
            }
        }
        return sb.toString(); // Return the accumulated string
    }
}