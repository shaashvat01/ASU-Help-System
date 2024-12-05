package Backend;

import java.util.*;
import java.util.logging.Logger;

/**
 * <p> AccessList Class </p>
 *
 * <p> Description: This class manages a list of access objects.
 * It provides methods to add, remove, and retrieve access,
 * as well as check for duplicates and iterate over the access list. </p>
 *
 * @version 1.01, 2024-12-02
 * @author Team
 *
 */

public class AccessList implements Iterable<Access> {

    // Logger for debugging and info messages
    private static final Logger logger = Logger.getLogger(AccessList.class.getName());

    // Stores the list of access objects
    private final List<Access> accessList;

    // Default constructor to initialize the access list
    public AccessList() {
        accessList = new ArrayList<>();
    }

    /**
     * Adds an Access object to the list, ensuring no duplicate group entries.
     *
     * @param access The Access object to add.
     */
    public void addAccess(Access access) {
        if (access == null) {
            throw new IllegalArgumentException("Access object cannot be null");
        }

        for (Access existingAccess : accessList) {
            if (existingAccess.getUsername().equals(access.getUsername())) {
                // Find missing groups and add them
                Set<String> missingGroups = new HashSet<>(access.getGroups());
                missingGroups.removeAll(existingAccess.getGroups());

                if (!missingGroups.isEmpty()) {
                    existingAccess.getGroups().addAll(missingGroups);
                    logger.info("Access Added - Subset");
                    return;
                } else {
                    logger.info("Duplicate access found, no changes made.");
                    return;
                }
            }
        }

        // Add as a new entry if no matching username found
        accessList.add(access);
        logger.info("Access Added - New");
    }

    /**
     * Removes an Access object from the list.
     *
     * @param access The Access object to remove.
     */
    public void removeAccess(Access access) {
        if (access == null) {
            throw new IllegalArgumentException("Access object cannot be null");
        }
        if (accessList.remove(access)) {
            logger.info("Access successfully removed.");
        } else {
            logger.warning("Access object not found in the list.");
        }
    }

    /**
     * Retrieves the entire list of Access objects.
     *
     * @return A list of Access objects.
     */
    public List<Access> getAccessList() {
        return Collections.unmodifiableList(accessList);
    }

    /**
     * Checks if the list contains a specific Access object.
     *
     * @param access The Access object to check.
     * @return True if the object exists in the list, false otherwise.
     */
    public boolean contains(Access access) {
        if (access == null) {
            throw new IllegalArgumentException("Access object cannot be null");
        }
        return accessList.contains(access);
    }

    /**
     * Returns an iterator for the access list.
     *
     * @return An iterator for the access list.
     */
    @Override
    public Iterator<Access> iterator() {
        return accessList.iterator();
    }
}