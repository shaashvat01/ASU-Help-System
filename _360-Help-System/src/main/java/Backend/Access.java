package Backend;

import java.util.ArrayList;

/*******
 * <p> Access Class </p>
 *
 * <p> Description: This class manages user access to specific groups in the system.
 * It contains the username and a list of groups that the user has access to,
 * and provides methods to retrieve this information and check access rights. </p>
 *
 * @version 1.00, 2024-11-19
 * @author Team - Th15
 *
 */

public class Access {

    // Stores the username of the user
    private String username;

    // Stores the list of groups the user has access to
    private ArrayList<String> groups;

    private String articleTitle;

    // Initializes an Access object with a username and list of groups
    public Access(String username,String title ,ArrayList<String> groups) {
        this.username = username;
        this.articleTitle = title;
        this.groups = groups;
    }
    // Retrieves the username of the user
    public String getUsername() {
        return username;
    }
    // Checks if the user belongs to a specific group
    public boolean hasGroup(String name) {
        return groups.contains(name);
    }
    // Retrieves the list of groups the user has access to
    public ArrayList<String> getGroups() {
        return groups;
    }
    public String getArticleTitle() {
        return articleTitle;
    }
}
