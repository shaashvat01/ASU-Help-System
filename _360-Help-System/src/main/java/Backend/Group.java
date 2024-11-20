package Backend;

import java.util.ArrayList;

import static com.example._360helpsystem.CreateAdminAccount.USER_LIST;
import static com.example._360helpsystem.SignIn.CURRENT_USER;

/*******
 * <p> Group Class </p>
 *
 * <p> Description: This class represents a group in the system. It manages group members,
 * administrators, and supports functionality for special groups with predefined rules. </p>
 *
 * @version 1.00, 2024-11-19
 * @author Team - Th15
 *
 */

public class Group {

    // Name of the group
    private String name;

    // List of usernames belonging to the group
    private ArrayList<String> users;

    // List of admin usernames for the group
    private ArrayList<String> admins;

    // Indicates whether the group is special
    private boolean isSpecial;

    // Constructor to initialize a group with a name and type (special or not)
    public Group(String name, boolean special) {
        this.name = name;
        this.users = new ArrayList<>();
        this.admins = new ArrayList<>();
        this.isSpecial = special;

        if (isSpecial && CURRENT_USER.isInstructor()) {
            admins.add(CURRENT_USER.username);
            users.add(CURRENT_USER.username);
        }

        if (!isSpecial) {
            for (User user : USER_LIST.getUserList()) {
                if (!user.isAdmin()) {
                    users.add(user.username);
                    if (user.isInstructor()) {
                        admins.add(user.username);
                    }
                }
            }
        }
    }

    // Constructor to initialize a group with a name, type, and existing users/admins
    public Group(String name, boolean special, ArrayList<String> users, ArrayList<String> admins) {
        this.name = name;
        this.users = users;
        this.admins = admins;
        this.isSpecial = special;
    }

    // Adds a user to the group
    public void addUser(User user) {
        this.users.add(user.username);
    }

    // Removes a user from the group
    public void removeUser(User user) {
        this.users.remove(user.username);
    }

    // Retrieves the name of the group
    public String getName() {
        return this.name;
    }

    // Retrieves the list of users in the group
    public ArrayList<String> getUsers() {
        return users;
    }

    // Retrieves the list of admins in the group
    public ArrayList<String> getAdmins() {
        return admins;
    }

    // Adds an admin to the group
    public void addAdmin(String username) {
        this.admins.add(username);
    }

    // Removes an admin from the group
    public void removeAdmin(String username) {
        this.admins.remove(username);
    }

    // Checks if a username belongs to the group's admins
    public boolean isAdmin(String username) {
        return this.admins.contains(username);
    }

    // Checks if the group is special
    public boolean isSpecial() {
        return isSpecial;
    }
}
