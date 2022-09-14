package pa0;
// Import packages as needed

import java.util.HashMap;

// LineUsageData.java: Handle one line's data, using a Map
public class LineUsage {

    private HashMap<String, Integer> lines;
    private int count;

    // Constructor
    public LineUsage() {
        this.lines = new HashMap<String, Integer>();
        count = 1;
    }

    // add one sighting of a user on this line
    public void addObservation(String username) {
        // if username key exists, add 1 to existing count
        if (lines.containsKey(username)) {
            lines.put(username, lines.get(username) + 1);
        } else {
            // adds new user of terminal to map
            lines.put(username, 1);
        }
    }

    // find the user with the most sightings on this line
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