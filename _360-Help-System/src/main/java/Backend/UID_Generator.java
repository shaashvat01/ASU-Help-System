/*******
 * <p> UID_Generator Class </p>
 *
 * <p> Description: This class generates unique identifiers (UIDs) for objects in the help system.
 * It ensures that each generated UID is distinct, supporting system-wide unique identification
 * for managing and accessing various resources. </p>
 *
 * @version 1.00, 2024-10-30
 * author Team - Th15
 *
 *******/

package Backend;

import java.util.concurrent.ThreadLocalRandom;

public class UID_Generator {
    // Generates a random UID in the range of 1 to Long.MAX_VALUE.
    private long generateUID() {
        return ThreadLocalRandom.current().nextLong(1, Long.MAX_VALUE); // Excluding 0 for uniqueness
    }

    // Provides a new unique identifier by calling generateUID.
    public long getUID() {
        return generateUID();
    }
}
