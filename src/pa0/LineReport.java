package pa0;

import java.io.*;
import java.util.*;

public class LineReport {
    /**
     * Array for the terminals in the log file
     */
    private final LineUsage[] lines;

    // Constructor
    public LineReport() {
        lines = new LineUsage[501];
        for (int i = 1; i < lines.length; i++) {
            lines[i] = new LineUsage();
        }
    }

    /**
     * Reads the input data from the file, puts facts into {@link #lines} array
     * @param fname the file name
     */
    void loadData(String fname) {
        // initialize file and scanner
        File file = new File(fname);
        Scanner reader;
        try {
            reader = new Scanner(file);
            // constantly read lines of log file
            while(reader.hasNextLine()) {
                String line = reader.nextLine();

                // get the line number and username from read line
                String[] parts = line.split(" ");
                int lineNumber = Integer.parseInt(parts[0]);
                String user = parts[1];

                lines[lineNumber].addObservation(user);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("An error has occurred while scanning.", e);
        }
    }

    /**
     * Given the loaded {@link #lines} array, generates the report on {@link #lines}
     */
    void generateReport() {
        // print header line
        System.out.println("Terminal\tMost Common User\tCount");
        // find max user for each terminal and print user info
        for(int i = 1; i < lines.length; i++) {
            Usage user = lines[i].findMaxUsage();
            System.out.println(i + "\t" + user.getUser() + "\t" + user.getCount());
        }
    }

    public static void main(String[] args) {
        // read filename from command line
        String filename = args[0];

        // generate report
        LineReport report = new LineReport();
        report.loadData(filename);
        report.generateReport();
    }
}