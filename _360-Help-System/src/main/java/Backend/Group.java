package Backend;

import java.util.ArrayList;

public class Group {
    private String name;
    private ArrayList<User> users;
    private ArrayList<String> admins;
    private ArrayList<String> regulars;
    private boolean isSpecial;

    public Group(String name, boolean special) {
        this.name = name;
        this.users = new ArrayList<>();
        this.admins = new ArrayList<>();
        this.regulars = new ArrayList<>();
        this.isSpecial = special;
    }

    public Group(String name, boolean special, ArrayList<User> users, ArrayList<String> admins, ArrayList<String> regulars) {
        this.name = name;
        this.users = users;
        this.admins = admins;
        this.regulars = regulars;
        this.isSpecial = special;
    }

    public void addUser(User user) {
        this.users.add(user);
    }
    public void removeUser(User user) {
        this.users.remove(user);
    }
    public String getName() {
        return name;
    }
    public ArrayList<User> getUsers() {
        return users;
    }
    public ArrayList<String> getAdmins() {
        return admins;
    }
    public ArrayList<String> getRegulars() {
        return regulars;
    }
    public boolean isSpecial() {
        return isSpecial;
    }
}
