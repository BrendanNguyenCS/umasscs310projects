package pa2;

import java.util.*;

/**
 * Class to demonstrate greedy algorithms. Skeleton.
 */
public class Grid {
    private boolean[][] grid = null;
    private ArrayList<Set<Spot>> allGroups; // All groups
    private final int height; // The height of the grid
    private final int width; // The width of the grid

    /**
     * Very simple constructor
     *
     * @param ingrid
     * a two-dimensional array of boolean to be used as the grid to
     * search
     */
    public Grid(boolean[][] ingrid) {
        grid = ingrid;
        allGroups = new ArrayList<Set<Spot> >();
        height = grid.length;
        width = grid[0].length;
    }

    public void printAllGroups()
    {
        for(Set<Spot> g:allGroups) {
            for(Spot s:g)
                System.out.println(s);
            System.out.println("");
        }
    }
    // The best way IMO to calculate the number of groups is to set up a matrix of integers and
    // for each non-0 entry calculate the group it's in.
    public ArrayList<Set<Spot>> calcAllGroups() {
        HashSet<Spot> spotsTraversed = new HashSet<>(); // a set to keep track of spots already traversed

        // Traverse grid
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                // if the current spot is not in a group or is already in a group
                if (!grid[i][j] || spotsTraversed.contains(new Spot(i, j))) {
                    // continue
                }
                else {
                    // calculate group size
                    int groupSize = groupSize(i, j);
                    Set<Spot> currentGroup = new HashSet<Spot>();
                    // Fill current group with all Spots in the group
                    getGroup(i, j, groupSize, currentGroup, spotsTraversed);
                    allGroups.add(currentGroup);
                }
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
     *
     * @param i
     * the first index into grid (i.e. the row number)
     * @param j
     * the second index into grid (i.e. the col number)
     * @return the size of the group
     */
    public int groupSize(int i, int j) {
        HashSet<Spot> group = new HashSet<Spot>();
        findGroup(i, j, group);
        return group.size();
    }

    /**
     * The recursive method that fills the set of the Spots in the group. This will be called by groupSize() to
     * get the current group's size
     *
     * @param i
     * the current row in the grid
     * @param j
     * the current column in the grid
     * @param group
     * the set that contains the current group
     */
    private void findGroup(int i, int j, Set<Spot> group) {
        // Checking if i,j coordinates are out of bounds
        if (!checkBounds(i, j)) {
            return;
        }

        // Stop if current square is not in a group or is already in the current group
        if (!grid[i][j] || group.contains(new Spot(i, j))) {
            return;
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
     * This private recursive method utilized by calcGroups() to populate the current group and the set that
     * defines all the spots that have been visited. It also adds the known group size of the current group to all
     * Spots
     *
     * @param i
     * the current row position in the grid
     * @param j
     * the current column position in the grid
     * @param count
     * the current group's group size
     * @param group
     * the Set that defines the current group
     * @param allSpots
     * the Set that defines all Spots that are already in a group
     */
    private void getGroup(int i, int j, int count, Set<Spot> group, Set<Spot> allSpots) {
        // Checking if i,j coordinates are out of bounds
        if (!checkBounds(i, j)) {
            return;
        }

        // Stop if current square is not in a group or is already in the current group
        if (!grid[i][j] || group.contains(new Spot(i, j)) || allSpots.contains(new Spot(i, j))) {
            return;
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
     *
     * @param i
     * the row coordinate
     * @param j
     * the column coordinate
     * @return true if the coordinates are in bounds, false otherwise
     */
    private boolean checkBounds(int i, int j) {
        return !(i < 0 || j < 0 || i >= height || j >= width);
    }

    /**
     * Nested class to represent a filled spot in the grid
     */
    public static class Spot {
        int i;
        int j;
        int group;
        /**
         * Constructor for a Spot
         *
         * @param i
         * the i-coordinate of this Spot
         * @param j
         * the j-coordinate of this Spot
         */
        public Spot(int i, int j) {
            this.i = i;
            this.j = j;
            this.group = 0; // Default. Will be set later.
        }

        /**
         * Tests whether this Spot is equal (i.e. has the same coordinates) to
         * another
         *
         * @param o
         * an Object
         * @return true if o is a Spot with the same coordinates
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
         * Returns an int based on Spot's contents
         * another way: (new Integer(i)).hashCode()^(new Integer(j)).hashCode();
         */
        public int hashCode() {
            return i << 16 + j; // combine i and j two halves of int
        }

        public void setGroup(int g) {group = g;}

        public int getI() {return i;}
        public int getJ() {return j;}
        public int getGroup() {return group;}
        /**
         * Returns a String representing this Spot, just the coordinates. You can add group if you want.
         */
        public String toString() {
            return "(" + i + " , " + j + ")";
        }
    }
}
