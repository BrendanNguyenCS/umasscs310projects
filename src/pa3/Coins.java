package pa3;

import edu.princeton.cs.algs4.In;

import java.util.ArrayList;

public class Coins {

    /**
     * The amount needed to pay
     */
    private int amount;
    /**
     * An ArrayList of the coin denominations (the different coins that can be used to pay)
     */
    private ArrayList<Integer> denominations;
    /**
     * Keeps track of the coins used when tracking minimum coins
     */
    private int[] M;
    /**
     * Keeps track of the last coin used when tracking minimum coins
     */
    private int[] R;

    // Constructor
    public Coins(String filename) {
        In in = new In(filename);
        denominations = new ArrayList<>();
        // the first int in the file is the amount
        amount = in.readInt();
        // all other ints are the denominations
        int[] denos = in.readAllInts();
        for (int d : denos) {
            denominations.add(d);
        }
        M = new int[amount + 1];
        M[0] = 0;
        R = new int[amount + 1];
        R[0] = 1;
    }

    /**
     * Getter for {@link #amount}
     * @return the amount needed to pay
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Returns the minimum number of coins needed to pay the {@link #amount} by calling a private helper recursion method
     * @return the number of coins
     */
    public int makeChange() {
        return findMinCoins(amount);
    }

    /**
     * Calculate the total minimum coins for the current total
     * @param total the total amount to pay
     * @return an integer signifying the minimum number of coins
     * An adapted version of the bottom up dynamic programming strategy
     */
    private int findMinCoins(int total) {
        for (int i = 1; i < total + 1; i++) {
            int min = i;
            int newCoin = 1;
            for (int coin : denominations) {
                if (coin > i) {
                    continue;
                }
                if (1 + M[i - coin] < min) {
                    min = Math.min(1 + M[i - coin], min);
                    newCoin = coin;
                }
            }
            M[i] = min;
            R[i] = newCoin;
        }
        return M[total];
    }

    /**
     * The number of coins needed to get the amount
     * @param coin the coin denomination
     * @return the number of coins for a denomination to get the amount
     */
    public int howMany(int coin) {
        int count = 0;
        for (int i : R) {
            if (i == coin) count++;
        }
        return count > M[amount] ? 0 : count;
    }

    /**
     * Helper debugging method to view the contents of the array fields
     */
    private void printArrays() {
        // Print M
        for (int i : M) {
            System.out.print(i + " ");
        }
        System.out.println();
        // Print R
        for (int i : R) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Coins c = new Coins(args[0]);
        // Print out min coins
        System.out.println(c.makeChange());
        // Print out array contents
        c.printArrays();
        // Test output for coins.txt
        //System.out.println("70 = " + c.howMany(70));
        //System.out.println("34 = " + c.howMany(34));
        // Test output for coins2.txt
        System.out.println("1 = " + c.howMany(1));
        System.out.println("25 = " + c.howMany(25));
    }
}
