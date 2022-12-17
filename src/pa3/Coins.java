package pa3;

import edu.princeton.cs.algs4.In;
import java.util.*;

public class Coins {

    /**
     * The amount needed to pay
     */
    private final int amount;
    /**
     * An ArrayList of the coin denominations (the different coins that can be used to pay)
     */
    private final ArrayList<Integer> denominations;
    /**
     * A tree to track the coin denominations and their minimums
     */
    private final HashMap<Integer, Integer> coinTree;

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
        coinTree = new HashMap<>();
    }

    /**
     * Getter for {@link #amount}
     * @return the amount needed to pay
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Getter for {@link #denominations}
     * @return the coin denominations
     */
    public ArrayList<Integer> getDenominations() {
        return denominations;
    }

    /**
     * Returns the minimum number of coins needed to pay the {@link #amount} by calling a private helper recursion method
     * @return the number of coins
     */
    public int makeChange() {
        int[] coinsUsed = new int[amount + 1];
        int[] lastCoin = new int[amount + 1];
        coinsUsed[0] = 0; lastCoin[0] = 1;

        for (int cents = 1; cents <= amount; cents++) {
            int minCoins = cents;
            int newCoin = 1;

            int differentCoins = denominations.size();
            for (int j = 0; j < differentCoins; j++) {
                if (denominations.get(j) > cents) {
                    continue;
                }
                if (coinsUsed[cents - denominations.get(j)] + 1 < minCoins) {
                    minCoins = coinsUsed[cents - denominations.get(j)] + 1;
                    newCoin = denominations.get(j);
                }
            }

            coinsUsed[cents] = minCoins;
            lastCoin[cents] = newCoin;
        }

        backtrack(amount, lastCoin);
        return coinsUsed[amount];
    }

    /**
     * A method which keeps track of all coins for this amount
     * @param amount the amount to pay
     * @param lastCoin an array which keeps track of the last coins used
     */
    private void backtrack(int amount, int[] lastCoin) {
        // Keeps track of how many coins each
        for (int i = 0; i < denominations.size(); i++)
            coinTree.put(denominations.get(i), 0);
        // Backtrack
        int s = amount;
        while (s > 0) {
            int coin = lastCoin[s];
            int freq = coinTree.get(coin);
            coinTree.put(coin, freq + 1);
            s -= coin;
        }
    }

    /**
     * The number of coins needed to get the amount
     * @param coin the coin denomination
     * @return the number of coins for a denomination to get the amount
     */
    public int howMany(int coin) {
        return coinTree.get(coin) != null ? coinTree.get(coin) : 0;
    }

    /**
     * Helper debugging method to view the contents of the tree
     */
    private void printSolution() {
        System.out.println(amount + " " + denominations);
        System.out.println("For " + amount + " We use " + makeChange() + " coins as follows:");
        System.out.println(coinTree);
    }

    public static void main(String[] args) {
        Coins c = new Coins(args[0]);
        // Print out min coins
        System.out.println(c.makeChange());
        // Print out array contents
        c.printSolution();
        // Test output for coins.txt
        //System.out.println("70 = " + c.howMany(70));
        //System.out.println("34 = " + c.howMany(34));
        // Test output for coins2.txt
        System.out.println("1 = " + c.howMany(1));
        System.out.println("25 = " + c.howMany(25));
    }
}
