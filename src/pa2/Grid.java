package pa2;

import java.util.*;

/**
 * Class to demonstrate greedy algorithms. Skeleton.
 */
public class Grid {
    /**
     * The grid which specifies where spots are
     * all {@link Spot Spot}s are defined as {@code true} in the grid, {@code false} values are not a Spot
     */
    private final boolean[][] grid;
    /**
     * This structure keeps track of all groups in the grid, along with all the members of all the groups
     */
    private final ArrayList<Set<Spot>> allGroups;
    /**
     * The height of the grid
     */
    private final int height;
    /**
     * The width of the grid
     */
    private final int width;

    /**
     * Constructor
     * @param ingrid a 2D array of {@link Boolean}s to be used as the grid to search
     */
    public Grid(boolean[][] ingrid) {
        grid = ingrid;
        allGroups = new ArrayList<>();
        height = grid.length;
        width = grid[0].length;
    }

    /**
     * Print out all {@link Spot}s in all groups in this grid
     */
    public void printAllGroups()
    {
        for(Set<Spot> g:allGroups) {
            for(Spot s:g)
                System.out.println(s);
            System.out.println();
        }
    }

    /**
     * Calculate all groups in the grid
     * @return the {@link ArrayList} of {@link Set}s of {@link Spot Spot}s signifying all groups that exist in this grid
     */
    public ArrayList<Set<Spot>> calcAllGroups() {
        HashSet<Spot> spotsTraversed = new HashSet<>(); // a set to keep track of spots already traversed

        // Traverse grid
        for (int i = 0; i < grid.length; i++) {
            // if the current spot is not in a group or is already in a group
            for (int j = 0; j < grid[i].length; j++)
                if (!grid[i][j] || spotsTraversed.contains(new Spot(i, j))) {
                    // continue
                } else {
                    // calculate group size
                    int groupSize = groupSize(i, j);
                    Set<Spot> currentGroup = new HashSet<>();
                    // Fill current group with all Spots in the group
                    getGroup(i, j, groupSize, currentGroup, spotsTraversed);
                    allGroups.add(currentGroup);
                }
        }
        return allGroups;
    }

    /**
     * Prints out a usage message
     */
    private static void printUsage() {
        System.out.println("Usage: java Grid <i> <j>");
        System.out.println("Find the size of the cluster of spots including position i,j");
        System.out.println("Usage: java Grid -all");
        System.out.println("Print all spots.");
    }

    /**
     * This calls the recursive method to find the group size
     * @param i the first index into grid (i.e. the row number)
     * @param j the second index into grid (i.e. the col number)
     * @return the size of the group
     */
    public int groupSize(int i, int j) {
        HashSet<Spot> group = new HashSet<>();
        findGroup(i, j, group);
        return group.size();
    }

    /**
     * The recursive method that fills the set of the {@link Spot Spot}s in the group. This will be called by
     * {@link #groupSize(int, int) groupSize()} to get the current group's size
     * @param i the current row in the grid
     * @param j the current column in the grid
     * @param group the {@link Set} that contains the current group
     */
    private void findGroup(int i, int j, Set<Spot> group) {
        // Checking if i,j coordinates are out of bounds
        if (!checkBounds(i, j))
            return;
        // Stop if current square is not in a group or is already in the current group
        if (!grid[i][j] || group.contains(new Spot(i, j))) {
            // continue
        }
        else {
            // Add current square to group
            Spot s = new Spot(i, j);
            group.add(s);
            // Recursively search in all four directions
            findGroup(i - 1, j, group); // Below
            findGroup(i + 1, j, group); // Above
            findGroup(i, j - 1, group); // Left
            findGroup(i, j + 1, group); // Right
        }
    }

    /**
     * This private recursive method utilized by {@link #calcAllGroups()} populates the current group and the set that
     * defines all the spots that have been visited. It also adds the known group size of the current group to all
     * {@link Spot Spot}s
     * @param i the current row position in the grid
     * @param j the current column position in the grid
     * @param count the current group's group size
     * @param group the {@link Set} that defines the current group
     * @param allSpots the {@link Set} that defines all {@link Spot Spot}s that are already in a group
     */
    private void getGroup(int i, int j, int count, Set<Spot> group, Set<Spot> allSpots) {
        // Checking if i,j coordinates are out of bounds
        if (!checkBounds(i, j))
            return;
        // Stop if current square is not in a group or is already in the current group
        if (!grid[i][j] || group.contains(new Spot(i, j)) || allSpots.contains(new Spot(i, j))) {
            // do nothing
        }
        else {
            // Add current square to group and allSpots
            Spot s = new Spot(i, j);
            s.setGroup(count);
            group.add(s);
            allSpots.add(s);
            // Recursively add in all four directions
            getGroup(i - 1, j, count, group, allSpots); // Below
            getGroup(i + 1, j, count, group, allSpots); // Above
            getGroup(i, j - 1, count, group, allSpots); // Left
            getGroup(i, j + 1, count, group, allSpots); // Right
        }
    }

    /**
     * This method checks if the desired coordinates are within the bounds of the grid
     * @param i the row coordinate
     * @param j the column coordinate
     * @return {@code true} if the coordinates are in bounds, {@code false} otherwise
     */
    private boolean checkBounds(int i, int j) { return i >=0 && j >= 0 && i < height && j < width; }

    /**
     * Nested class to represent a filled spot in the grid
     */
    public static class Spot {
        /**
         * The row coordinate
         */
        int i;
        /**
         * The column coordinate
         */
        int j;
        /**
         * The group size
         */
        int group;

        /**
         * Constructor for a Spot
         * @param i the i-coordinate of this Spot
         * @param j the j-coordinate of this Spot
         */
        public Spot(int i, int j) {
            this.i = i;
            this.j = j;
            this.group = 0;
        }

        /**
         * Tests whether this Spot is equal (i.e. has the same coordinates) to another
         * @param o a {@link Spot Spot} object
         * @return {@code true} if {@code o} is a {@link Spot Spot} with the same coordinates
         */
        public boolean equals(Object o) {
            if (o == null)
                return false;
            if (o.getClass() != getClass())
                return false;
            Spot other = (Spot) o;
            return (other.i == i) && (other.j == j);
        }

        /**
         * Returns an int based on Spot's contents. Combines {@link Spot#i} and {@link Spot#j} two halves of int
         */
        public int hashCode() { return i << 16 + j; }

        /**
         * Getter for {@link #group}
         * @return the group size
         */
        public int getGroup() { return group; }

        /**
         * Setter for {@link #group}
         * @param g the new group size
         */
        public void setGroup(int g) { group = g; }

        /**
         * Getter for {@link #i}
         * @return the row
         */
        public int getI() { return i; }

        /**
         * Getter for {@link #j}
         * @return the column
         */
        public int getJ() { return j; }

        /**
         * Returns a String representing this Spot, just the coordinates.<br>
         * Note: The group size can be added if needed.
         */
        public String toString() { return "(" + i + " , " + j + ")"; }
    }
}
