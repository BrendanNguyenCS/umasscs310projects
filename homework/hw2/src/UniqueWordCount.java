// Homework 2 Question 5c

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class UniqueWordCount {
    public static void main(String[] args) throws FileNotFoundException {
        // read file from command line
        File file = new File(args[0]);

        // scan file and add to map
        Scanner reader = new Scanner(file);
        HashMap<String, Integer> map = new HashMap<>();
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

        // print out words and their counts
        map.forEach((key, value) -> {
            System.out.println(key + "\t" + value);
        });
    }
}
