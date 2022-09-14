package pa0;
// One user's record on one line: how many times
// this user has been seen on this line
public class Usage {

    // user: the username of this user
    private String user;
    // count: the number of times this user has been seen on this line
    private int count;

    // Constructor
    public Usage(String x, int count) {
        this.user = x;
        this.count = count;
    }

    // sets the number of times a user has accessed terminal
    public void setCount(int x) {
        count = x;
    }

    // returns username for current usage user
    public String getUser() {
        return this.user;
    }

    // returns the number of usages for this user
    public int getCount() {
        return this.count;
    }
}