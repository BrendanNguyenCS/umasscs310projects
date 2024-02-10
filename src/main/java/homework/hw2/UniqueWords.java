package homework.hw2;

import java.io.*;
import java.util.*;

/**
 * A class which prints all the different words in a file<br>
 * Homework 2 Question 5b
 */
public class UniqueWords {
    public static void main(String[] args) {
        File file = new File(args[0]);
        Scanner reader;
        HashMap<String, String> map = new HashMap<>();
        try {
            reader = new Scanner(file);
            while (reader.hasNext()) {
                String word = reader.next();
                map.putIfAbsent(word, "");
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("An error has occurred while scanning.", e);
        }
        // print all keys in map
        map.forEach((key, value) -> System.out.println(key));
    }
}
