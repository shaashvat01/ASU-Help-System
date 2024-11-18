package Backend;

import java.util.ArrayList;

import static com.example._360helpsystem.CreateAdminAccount.USER_LIST;
import static com.example._360helpsystem.SignIn.CURRENT_USER;

public class Group {
    private String name;
    private ArrayList<String> users;
    private ArrayList<String> admins;
    private boolean isSpecial;

    public Group(String name, boolean special) {
        this.name = name;
        this.users = new ArrayList<>();
        this.admins = new ArrayList<>();

        this.isSpecial = special;
        if(isSpecial && CURRENT_USER.isInstructor()) {
            admins.add(CURRENT_USER.username);
            users.add(CURRENT_USER.username);
        }

        if(!isSpecial) {
            for(User user : USER_LIST.getUserList())
            {
                if(!user.isAdmin())
                {
                    users.add(user.username);
                    if(user.isInstructor())
                    {
                        admins.add(user.username);
                    }
                }

            }
        }
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
        this.users.remove(user.username);
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
