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

public class GroupList implements Iterable<String> {
    // List of groups managed by this class.
    private ArrayList<String> groups;

    // Default constructor initializing the groups list.
    public GroupList() {
        this.groups = new ArrayList<>();
    }

    // Adds a group to the list.
    public void addGroup(String group) {
        this.groups.add(group);
    }

    // Removes a group from the list.
    public void removeGroup(String group) {
        this.groups.remove(group);
    }

    // Returns the number of groups in the list.
    public int getSize() {
        return groups.size();
    }

    // Provides an iterator for the groups list.
    @Override
    public Iterator<String> iterator() {
        return groups.iterator();
    }

    // Checks if a specific group exists in the list.
    public boolean contains(String group) {
        return groups.contains(group);
    }
}
