package Backend;

import java.util.ArrayList;
import java.util.Iterator;

/*******
 * <p> AccessList Class </p>
 *
 * <p> Description: This class manages a list of access objects.
 * It provides methods to add, remove, and retrieve access,
 * as well as check for duplicates and iterate over the access list. </p>
 *
 * @version 1.00, 2024-11-19
 * @author Team - Th15
 *
 */

public class AccessList implements Iterable<Access> {

    // Stores the list of access objects
    private ArrayList<Access> accessList;

    // Default constructor to initialize the access list
    public AccessList() {
        accessList = new ArrayList<>();
    }

    // Adds an Access object to the list, ensuring no duplicate group entries
    public void addAccess(Access access) {
        boolean grpsMatch = true;
        ArrayList<String> missingGrps = new ArrayList<>();
        for (Access a : accessList) {
            if (a.getUsername().equals(access.getUsername())) {
                for (String grps : access.getGroups()) {
                    if (!a.getGroups().contains(grps)) {
                        missingGrps.add(grps);
                        grpsMatch = false;
                    }
                }
                if (!grpsMatch) {
                    for (String grps : missingGrps) {
                        a.getGroups().add(grps);
                    }
                    System.out.println("Access Added - Subset");
                    return;
                } else {
                    System.out.println("Duplicate");
                    return;
                }
            }
        }

        accessList.add(access);
        System.out.println("Access Added - New");
    }

    // Removes an Access object from the list
    public void removeAccess(Access access) {
        accessList.remove(access);
    }

    // Retrieves the entire list of Access objects
    public ArrayList<Access> getAccessList() {
        return accessList;
    }

    // Checks if the list contains a specific Access object
    public boolean contains(Access access) {
        return accessList.contains(access);
    }

    // Returns an iterator for the access list
    @Override
    public Iterator<Access> iterator() {
        return accessList.iterator();
    }
}
