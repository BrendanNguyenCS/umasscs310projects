// Homework 1 Question 9

public class WordUsage {
    private final String word; // the current word being tracked
    private int count; // the number of times this word has been used

    // Constructors
    public WordUsage(String word, int count) {
        this.word = word;
        this.count = count;
    }

    public WordUsage(String word) {
        this.word = word;
        this.count = 1;
    }

    // Returns this tracked word
    public String getWord() {
        return this.word;
    }

    // Returns this word's count
    public int getCount() {
        return this.count;
    }

    // Sets this word's count
    public void setCount(int count) {
        this.count = count;
    }

    // Increments the count for this word
    public void increment() {
        this.count++;
    }
}
