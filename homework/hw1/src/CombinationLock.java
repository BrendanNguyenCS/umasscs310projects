// Homework 1 Question 8

public class CombinationLock {
    private int combination; // The lock's combination
    private boolean isOpen; // The lock's lock status

    // Constructor
    public CombinationLock(int combination) {
        this.combination = combination;
        this.isOpen = false;
    }

    // Opens the lock if the attempted combination matches the lock's combination
    public void open(int attempt) {
        if (attempt == combination) {
            isOpen = true;
        } else {
            System.out.printf("The combination %d is incorrect.\n", attempt);
        }
    }

    // Locks the combination lock
    public void lock() {
        this.isOpen = false;
    }

    // Changes the lock's combination
    public void changeCombo(int newCombination) {
        this.combination = newCombination;
        this.isOpen = false;
    }
}
