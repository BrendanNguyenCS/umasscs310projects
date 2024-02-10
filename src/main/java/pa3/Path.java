package pa3;

/**
 * Class for a Path in the Dynamic Programming sequence alignment algorithm
 */
public class Path {
    /**
     * The row for this node
     */
    private int row;
    /**
     * The column for this node
     */
    private int column;
    /**
     * The matching cost from this point on
     */
    private int cost;
    /**
     * The next node in the optimal path
     */
    private Path next;

    /**
     * Constructor
     */
    public Path() {
        next = null;
    }

    /**
     * Getter for {@link #row}
     * @return this node's row value
     */
    public int getRow() {
        return row;
    }

    /**
     * Setter for {@link #row}
     * @param row the new row value
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     * Getter for {@link #column}
     * @return this node's column value
     */
    public int getColumn() {
        return column;
    }

    /**
     * Setter for {@link #column}
     * @param column the new column value
     */
    public void setColumn(int column) {
        this.column = column;
    }

    /**
     * Getter for {@link #cost}
     * @return this node's cost
     */
    public int getCost() {
        return cost;
    }

    /**
     * Setter for {@link #cost}
     * @param cost the new cost for this node
     */
    public void setCost(int cost) {
        this.cost = cost;
    }

    /**
     * Getter for {@link #next}
     * @return the next node in the optimal path
     */
    public Path getNext() {
        return next;
    }

    /**
     * Setter for {@link #next}
     * @param next the new next node in the optimal path
     */
    public void setNext(Path next) {
        this.next = next;
    }

    /**
     * A method which sets the grid dimensions for this {@link Path} node
     * @param row the new row value
     * @param column the new column value
     */
    public void setDimensions(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * @return the string representation of this {@link Path} node
     */
    public String toString() {
        return "(" + row + " , " + column + " , " + cost + ")";
    }

    /**
     * Prints the path
     * @param a a string sequence
     * @param b another string sequence
     */
    public void printPath(String a, String b) {
        System.out.println("Edit Distance " + cost);
        Path p = this;
        while (p.next != null) {
            int c = p.cost - p.next.cost;
            if (p.next.row == p.row + 1 && p.next.column == p.column + 1) {
                System.out.println(a.charAt(p.row) + " " + b.charAt(p.column) + " " + c);
            }
            if (p.next.column == p.column) {
                System.out.println(a.charAt(p.row) + " - " + c);
            }
            if (p.next.row == p.row) {
                System.out.println("- " + b.charAt(p.column) + " " + c);
            }
            p = p.next;
        }
    }
}
