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
            for (User user : userL) { // Use userL to iterate over
                //Need to implement front end list
        }
        }
    }
}