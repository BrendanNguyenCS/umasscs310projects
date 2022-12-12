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

    // Constructor
    public LineUsage() {
        this.lines = new HashMap<String, Integer>();
    }

    /**
     * Adds one sighting of a user on this line
     * @param username the user sighting to be added to this line
     */
    public void addObservation(String username) {
        // if username key exists, add 1 to existing count
        if (lines.containsKey(username)) {
            lines.put(username, lines.get(username) + 1);
        } else {
            // adds new user of terminal to map
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

        // check each user in lines
        for(String user : lines.keySet()) {
            // if the user's count is greater than the current max,
            // update temp variables with new user
            if (lines.get(user) > maxCount) {
                maxCount = lines.get(user);
                maxUser = user;
            }
        }

        // return max user with temp variables
        return new Usage(maxUser, maxCount);
    }
}