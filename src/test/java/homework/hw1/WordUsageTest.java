package homework.hw1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WordUsageTest {

    @Test
    void getWord() {
        WordUsage wordCount = new WordUsage("word");
        assertEquals("word", wordCount.getWord());
        WordUsage wordCount2 = new WordUsage("word2", 2);
        assertEquals("word2", wordCount2.getWord());
    }

    @Test
    void setCount() {
        WordUsage wordCount = new WordUsage("word");
        wordCount.setCount(2);
        assertEquals(2, wordCount.getCount());
    }

    @Test
    void increment() {
        WordUsage wordCount = new WordUsage("word");
        wordCount.increment();
        assertEquals(2, wordCount.getCount());
        WordUsage wordCount2 = new WordUsage("word2", 2);
        wordCount2.increment();
        assertEquals(3, wordCount2.getCount());
    }
}