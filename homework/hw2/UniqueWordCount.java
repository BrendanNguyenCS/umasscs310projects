package hw2;

import java.io.*;
import java.util.*;

/**
 * A class which prints all the different words in a file and their counts<br>
 * Homework 2 Question 5c
 */
public class UniqueWordCount {
    public static void main(String[] args) {
        // read file from command line
        File file = new File(args[0]);

        // scan file and add to map
        Scanner reader;
        HashMap<String, Integer> map = new HashMap<>();
        try {
            reader = new Scanner(file);
            while(reader.hasNext()) {
                int wordCount = 0;
                String word = reader.next();
                // if word already exists, get its current count
                if (map.containsKey(word)) {
                    wordCount = map.get(word);
                }
                // add word to map while incrementing word count
                map.put(word, ++wordCount);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("An error has occurred while scanning.", e);
        }

        // print out words and their counts
        map.forEach((key, value) -> System.out.println(key + "\t" + value));
    }
}
