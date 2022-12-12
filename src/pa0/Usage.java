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

    // Constructor
    public Usage(String x, int count) {
        this.user = x;
        this.count = count;
    }

    /**
     * @param x the new value for {@link #count}
     */
    public void setCount(int x) {
        count = x;
    }

    /**
     * @return the field {@link #user}
     */
    public String getUser() {
        return this.user;
    }

    /**
     * @return the field {@link #count}
     */
    public int getCount() {
        return this.count;
    }
}