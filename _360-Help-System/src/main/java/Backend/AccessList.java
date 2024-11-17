package Backend;


import java.util.ArrayList;
import java.util.Iterator;

public class AccessList implements Iterable<Access> {
    private ArrayList<Access> accessList;

    public AccessList() {
        accessList = new ArrayList<>();
    }
    public void addAccess(Access access) {
        accessList.add(access);
    }
    public void removeAccess(Access access) {
        accessList.remove(access);
    }
    public ArrayList<Access> getAccessList() {
        return accessList;
    }

    @Override
    public Iterator<Access> iterator() {
        return accessList.iterator();
    }
}
