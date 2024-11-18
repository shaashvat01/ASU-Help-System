package Backend;

import java.util.ArrayList;

public class Access {
    private String username;
    private ArrayList<String> groups;

    public Access(String username, ArrayList<String> groups) {
        this.username = username;
        this.groups = groups;
    }
    public String getUsername() {
        return username;
    }
    public boolean hasGroup(String name)
    {
        return groups.contains(name);
    }
    public ArrayList<String> getGroups()
    {
        return groups;
    }

}
