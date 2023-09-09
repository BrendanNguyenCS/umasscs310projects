package pa0;

/**
 * One user's record on one line: how many times this user has been seen on this line
 */
public class Usage {
    /**
     * The username of this user
     */
    private final String user;
    /**
     * The number of times this user has been seen on this line
     */
    private int count;

    /**
     * Constructor
     * @param x the username for the current user
     * @param count the number of times this user has been seen on this line
     */
    public Usage(String x, int count) {
        user = x;
        this.count = count;
    }

    /**
     * Getter for {@link #count}
     * @return the number of times this user has been seen on this line
     */
    public int getCount() {
        return this.count;
    }

    /**
     * Setter for {@link #count}
     * @param x the new count
     */
    public void setCount(int x) {
        count = x;
    }

    /**
     * Getter for {@link #user}
     * @return the username for this user
     */
    public String getUser() {
        return this.user;
    }
}