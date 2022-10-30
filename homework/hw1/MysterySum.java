// Homework 1 Question 5
package hw1;

public class MysterySum {
    public static void main(String[] args) {
        int n = mysterySum(5);
        System.out.println(n);
    }

    public static int mysterySum(int n) {
        int i, j, s=0;
        for(i=0; i < n; i++) {
            for(j=0; j < i; j++) {
                s += i*i;
                System.out.println(s + "\t" + i*i);
            }
            System.out.println("------------------------");
        }
        return s;
    }
}
