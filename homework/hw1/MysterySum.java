package hw1;

/**
 * A class that does a mystery summation<br>
 * Homework 1 Question 5
 */
public class MysterySum {
    public static void main(String[] args) {
        int n = mysterySum(5);
        System.out.println(n);
    }

    /**
     * Mystery summation formula
     * @param n a number
     * @return the result
     */
    public static int mysterySum(int n) {
        int i, j, s = 0;
        for (i = 0; i < n; i++) {
            for (j = 0; j < i; j++) {
                s += i * i;
                System.out.println(s + "\t" + i * i);
            }
            System.out.println("------------------------");
        }
        return s;
    }
}
