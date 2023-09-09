package pa0;

import java.util.HashMap;

/**
 * LineUsage.java: Handle one line's data, using a Map
 */
public class LineUsage {
    /**
     * The map that encapsulates the data for this line
     */
    private final HashMap<String, Integer> lines;

    /**
     * Constructor
     */
    public LineUsage() {
        lines = new HashMap<>();
    }

    /**
     * Adds one sighting of a user on this line
     * @param username the user sighting to be added to this line
     */
    public void addObservation(String username) {
        if (lines.containsKey(username)) {
            lines.put(username, lines.get(username) + 1);
        } else {
            lines.put(username, 1);
        }
    }

    /**
     * Finds the user with the most sightings on this line
     * @return a {@link Usage} object that contains the user and their count
     */
    public Usage findMaxUsage() {
        // temp variables to keep track of the user with max usage
        int maxCount = 0;
        String maxUser = "NONE";
        for(String user : lines.keySet()) {
            if (lines.get(user) > maxCount) {
                maxCount = lines.get(user);
                maxUser = user;
            }
        }
        return new Usage(maxUser, maxCount);
    }
}