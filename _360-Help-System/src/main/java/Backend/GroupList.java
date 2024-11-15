package Backend;

import java.util.ArrayList;
import java.util.Iterator;

public class GroupList implements Iterable<String>{
    private ArrayList<String> groups;

    public GroupList() {
        this.groups = new ArrayList<>();
    }

    public void addGroup(String group) {
        this.groups.add(group);
    }

    public void removeGroup(String group) {
        this.groups.remove(group);
    }

    // Return the number of articles
    public int getSize() {
        return groups.size();
    }

    public Iterator<String> iterator() {
        return groups.iterator();
    }

    public boolean contains(String group) {
        return groups.contains(group);
    }
}