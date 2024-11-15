package Backend;

import java.util.ArrayList;

import static com.example._360helpsystem.CreateAdminAccount.USER_LIST;

public class Group {
    private String name;
    private ArrayList<String> users;
    private ArrayList<String> admins;
    private boolean isSpecial;

    public Group(String name, boolean special) {
        this.name = name;
        this.users = new ArrayList<>();
        this.admins = new ArrayList<>();
        admins.add(USER_LIST.getUserList().getFirst().username);
        this.isSpecial = special;
    }

    public Group(String name, boolean special, ArrayList<String> users, ArrayList<String> admins) {
        this.name = name;
        this.users = users;
        this.admins = admins;
        this.isSpecial = special;
    }

    public void addUser(User user) {
        this.users.add(user.username);
    }
    public void removeUser(User user) {
        this.users.remove(user);
    }
    public String getName() {
        return this.name;
    }
    public ArrayList<String> getUsers() {
        return users;
    }
    public ArrayList<String> getAdmins() {
        return admins;
    }
    public void addAdmin(String username) {
        this.admins.add(username);
    }
    public void removeAdmin(String username) {
        this.admins.remove(username);
    }
    public boolean isAdmin(String username) {
        return this.admins.contains(username);
    }
    public boolean isSpecial() {
        return isSpecial;
    }
}
