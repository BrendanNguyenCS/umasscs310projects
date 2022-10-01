// Homework 2 Question 5b

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class UniqueWords {
    public static void main(String[] args) throws FileNotFoundException {
        // read file from command line
        File file = new File(args[0]);

        // scan file and add to map
        Scanner reader = new Scanner(file);
        HashMap<String, String> map = new HashMap<>();
        while(reader.hasNext()) {
            String word = reader.next();
            map.putIfAbsent(word, "");
        }

        // print all keys in map
        map.forEach((key, value) -> {
            System.out.println(key);
        });
    }
}
