// Homework 2 Question 5a
package hw2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReverseFile {
    public static void main(String args[]) throws FileNotFoundException {
        // Read file from command line
        File file = new File(args[0]);

        // Scan through file and add to linked list
        ArrayList<String> lines = new ArrayList<String>();
        Scanner reader = new Scanner(file);
        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            lines.add(line);
        }

        // Print lines in reverse
        for(int i = lines.size() - 1; i >= 0; i--) {
            System.out.println(lines.get(i));
        }
    }
}
