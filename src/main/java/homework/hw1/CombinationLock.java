package homework.hw1;

/**
 * A class that simulates a combination lock<br>
 * Homework 1 Question 8
 */
public class CombinationLock {
    /**
     * The lock's combination
     */
    private int a, b, c;

    /**
     * Constructor
     * @param a1 first number of lock
     * @param a2 second number of lock
     * @param a3 third number of lock
     */
    public CombinationLock(int a1, int a2, int a3) {
        a = a1;
        b = a2;
        c = a3;
    }

    /**
     * Opens the lock if the attempted combination matches the lock's combination
     * @param x the first attempted number
     * @param y the second attempted number
     * @param z the third attempted number
     * @return {@code true} if the attempted combination matches the lock's combination, {@code false} otherwise
     */
    public boolean open(int x, int y, int z) {
        return x == a && y == b && z == c;
    }

    /**
     * Changes the lock's combination (old combination followed by new combination)
     * @param x the first number of current combination
     * @param y the second number of current combination
     * @param z the third number of current combination
     * @param p the first number of new combination
     * @param q the second number of new combination
     * @param r the third number of new combination
     * @return {@code true} if the combination was successfully changed, {@code false} otherwise
     */
    public boolean changeCombo(int x, int y, int z, int p, int q, int r) {
        if (this.open(x, y, z)) {
            a = p;
            b = q;
            c = r;
            return true;
        }
        return false;
    }
}
