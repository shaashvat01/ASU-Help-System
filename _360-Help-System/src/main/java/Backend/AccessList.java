package Backend;


import java.util.ArrayList;
import java.util.Iterator;

public class AccessList implements Iterable<Access> {
    private ArrayList<Access> accessList;

    public AccessList() {
        accessList = new ArrayList<>();
    }
    public void addAccess(Access access) {
        boolean grpsMatch = true;
        ArrayList<String> missingGrps = new ArrayList<>();
        for(Access a : accessList) {
            if(a.getUsername().equals(access.getUsername())) {
                for(String grps : access.getGroups()) {
                    if(!a.getGroups().contains(grps)) {
                        missingGrps.add(grps);
                        grpsMatch = false;
                    }
                }
                if(!grpsMatch) {
                    for(String grps : missingGrps) {
                        a.getGroups().add(grps);
                    }
                    System.out.println("Access Added - Subset");
                    return;
                }
                else{
                    System.out.println("Duplicate");
                    return;
                }
            }
        }

        accessList.add(access);
        System.out.println("Access Added - New");

    }
    public void removeAccess(Access access) {
        accessList.remove(access);
    }
    public ArrayList<Access> getAccessList() {
        return accessList;
    }
    public boolean contains(Access access) {
        return accessList.contains(access);
    }


    @Override
    public Iterator<Access> iterator() {
        return accessList.iterator();
    }
}
