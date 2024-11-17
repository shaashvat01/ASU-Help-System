/*******
 * <p> GroupList Class </p>
 *
 * <p> Description: This class manages a list of groups within the help system.
 * It includes methods for adding, removing, and retrieving groups to support
 * organizing content and managing access in a structured manner. </p>
 *
 * @version 1.00, 2024-10-30
 * author Team - Th15
 *
 *******/

package Backend;

import java.util.ArrayList;
import java.util.Iterator;

import static com.example._360helpsystem.CreateAdminAccount.GROUP_LIST;

public class GroupList implements Iterable<Group> {
    // List of groups managed by this class.
    private ArrayList<Group> groups;

    // Default constructor initializing the groups list.
    public GroupList() {
        this.groups = new ArrayList<>();
    }

    // Adds a group to the list.
    public void addGroup(Group group) {
        this.groups.add(group);
    }

    public Group getGroup(String name) {
        for (Group group : groups) {
            if (group.getName().equals(name)) {
                return group;
            }
        }
        return null;
    }

    // Removes a group from the list.
    public void removeGroup(String group) {
        Iterator<Group> iterator = GROUP_LIST.iterator();
        while (iterator.hasNext()) {
            Group g = iterator.next();
            if (g.getName().equals(group)) {
                iterator.remove(); // Safely removes the element from GROUP_LIST
                this.groups.remove(g); // Removes from the internal groups list
            }
        }
    }

    // Returns the number of groups in the list.
    public int getSize() {
        return groups.size();
    }

    // Provides an iterator for the groups list.
    @Override
    public Iterator<Group> iterator() {
        return groups.iterator();
    }

    // Checks if a specific group exists in the list.
    public boolean contains(String group) {
        for(Group g : GROUP_LIST) {
            if(g.getName().equals(group)) {
                return true;
            }
        }
        return false;
    }

}
