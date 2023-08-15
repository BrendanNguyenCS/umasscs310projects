package pa3;

import edu.princeton.cs.algs4.*;
import java.io.*;

/**
 * A class to demonstrate the matching sequence alignment dynamic programming algorithm
 */
public class Match {
    /**
     * Computes the optimal sequence alignment of two DNA strings (of type {@link String})
     * @param a a DNA string
     * @param b a DNA string
     * @return the {@link Path} object which represents the optimal sequence alignment
     */
    public Path match(String a, String b) {
        if (a == null || b == null || a.length() == 0 || b.length() == 0)
            return null;
        int gapPenalty = 2, length = a.length(), width = b.length();
        // Finding one more row and column to check to boundaries
        Path[][] opt = new Path[length + 1][width + 1];
        // Fill gap penalty
        int gap = 0;
        for (int i = length; i >= 0; i--) {
            opt[i][width] = new Path();
            opt[i][width].setCost(gap);
            opt[i][width].setDimensions(i, width);
            gap += gapPenalty;
        }
        gap = gapPenalty;
        // start from width - 1 because the width has already been filled above
        for (int i = width; i >= 0; i--) {
            opt[length][i] = new Path();
            opt[length][i].setCost(gap);
            opt[length][i].setDimensions(length, i);
            gap += gapPenalty;
        }
        // Now starting the matching
        for (int i = length; i >= 0; i--) {
            for (int j = width; j >= 0; j--) {
                // Match, mismatch, or gap
                opt[i][j] = new Path();
                opt[i][j].setDimensions(i, j);
                int match = a.charAt(i) == b.charAt(j) ? 0 : 1;
                int val1 = opt[i + 1][j + 1].getCost() + match; // mismatch
                int val2 = opt[i + 1][j].getCost() + gapPenalty; // Gap in i
                int val3 = opt[i][j + 1].getCost() + gapPenalty; // Gap in j
                // Find 3-way minimum
                int min = min3Way(val1, val2, val3);
                if (min == val1) {
                    opt[i][j].setCost(val1);
                    opt[i][j].setNext(opt[i + 1][j + 1]);
                }
                else if (min == val2) {
                    opt[i][j].setCost(val2);
                    opt[i][j].setNext(opt[i + 1][j]);
                }
                else if (min == val3) {
                    opt[i][j].setCost(val3);
                    opt[i][j].setNext(opt[i][j + 1]);
                }
            }
        }
        return opt[0][0];
    }

    /**
     * Finds the three-way minimum of integers
     * @param a an integer
     * @param b an integer
     * @param c an integer
     * @return the minimum of the three integers
     */
    private static int min3Way(int a, int b, int c) {
        int min = a;
        min = Math.min(b, min);
        min = Math.min(c, min);
        return min;
    }

    public static void main(String[] args) {
        In in = new In(new File(args[0]));
        String a = in.readString();
        String b = in.readString();
        Match m = new Match();
        Path p = m.match(a, b);
        p.printPath(a, b);
    }
}
