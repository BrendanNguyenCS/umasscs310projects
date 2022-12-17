package hw1;

/**
 * A class to track the number of times a word is used in a certain text<br>
 * Homework 1 Question 9
 */
public class WordUsage {

    /**
     * The current word being tracked
     */
    private final String word;
    /**
     * The number of times this word has been used
     */
    private int count;

    // Constructors
    public WordUsage(String word, int count) {
        this.word = word;
        this.count = count;
    }

    public WordUsage(String word) {
        this.word = word;
        this.count = 1;
    }

    /**
     * Getter for {@link #word}
     * @return the current word being tracked
     */
    public String getWord() {
        return this.word;
    }

    /**
     * Getter for {@link #count}
     * @return this word's count
     */
    public int getCount() {
        return this.count;
    }

    /**
     * Setter for {@link #count}
     * @param count the new count
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * Increments the count for this word
     */
    public void increment() {
        this.count++;
    }
}
