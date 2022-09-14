package pa0;
// One user's record on one line: how many times
// this user has been seen on this line
public class Usage {
    // Put variables here.
    private String user;
    private int count;

    public Usage(String x, int count) {
        this.user = x;
        this.count = count;
    }

    public void setCount(int x) {
        count = x;
    }

    public String getUser() {
        return this.user;
    }

    public int getCount() {
        return this.count;
    }
}