package pa0;
// Import packages as needed

import java.util.HashMap;

// LineUsageData.java: Handle one line's data, using a Map
public class LineUsage {
    // Variables here
    private HashMap<String, Integer> lines;

    // Constructor
    public LineUsage() {
        this.lines = new HashMap<String, Integer>();
    }

    // add one sighting of a user on this line
    public void addObservation(String username) {
    }

    // find the user with the most sightings on this line
    public Usage findMaxUsage() {
        return new Usage("NOTHING",-1);
    }
}