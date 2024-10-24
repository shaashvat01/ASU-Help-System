package Backend;

import java.util.concurrent.ThreadLocalRandom;

public class UID_Generator {
    private long generateUID() {
        // Generate a random UID in the range of 0 to Long.MAX_VALUE
        return ThreadLocalRandom.current().nextLong(1, Long.MAX_VALUE); // Excluding 0 for uniqueness
    }

    public long getUID()
    {
        return generateUID();
    }
}