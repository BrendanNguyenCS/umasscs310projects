package hw2;

import java.io.*;
import java.util.*;

/**
 * A class which prints the lines of a file in reverse order<br>
 * Homework 2 Question 5a
 */
public class ReverseFile {
    public static void main(String[] args) {
        // Read file from command line
        File file = new File(args[0]);
        // Scan through file and add to linked list
        ArrayList<String> lines = new ArrayList<>();
        Scanner reader;
        try {
            reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                lines.add(line);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("An error has occurred while scanning.", e);
        }
        // Print lines in reverse
        for(int i = lines.size() - 1; i >= 0; i--)
            System.out.println(lines.get(i));
    }
}
