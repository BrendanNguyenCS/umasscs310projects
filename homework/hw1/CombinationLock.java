// Homework 1 Question 8
package hw1;

public class CombinationLock {
    private int a, b, c; // The lock's combination

    // Constructor
    public CombinationLock(int a1, int a2, int a3) {
        this.a = a1;
        this.b = a2;
        this.c = a3;
    }

    // Opens the lock if the attempted combination matches the lock's combination
    public boolean open(int x, int y, int z) {
        return x == a && y == b && z == c;
    }

    // Changes the lock's combination (old combination followed by new combination)
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
