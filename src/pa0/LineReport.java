package pa0;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class LineReport {
    // lines: array for the terminals in the log file
    private LineUsage[] lines;

    // Constructor
    public LineReport() {
        lines = new LineUsage[501];
        for (int i = 1; i < lines.length; i++) {
            lines[i] = new LineUsage();
        }
    }

    // read input data, put facts into lines array
    void loadData(String fname) throws FileNotFoundException {
        // initialize file and scanner
        File file = new File(fname);
        Scanner reader = new Scanner(file);
        // constantly read lines of log file
        while(reader.hasNextLine()) {
            String line = reader.nextLine();

            // get the line number and username from read line
            String[] parts = line.split(" ");
            int lineNumber = Integer.parseInt(parts[0]);
            String user = parts[1];

            lines[lineNumber].addObservation(user);
        }
    }

    // given loaded lines array, generate report on lines
    void generateReport() {
        // print header line
        System.out.println("Terminal\tMost Common User\tCount");
        // find max user for each terminal and print user info
        for(int i = 1; i < lines.length; i++) {
            Usage user = lines[i].findMaxUsage();
            System.out.println(i + "\t" + user.getUser() + "\t" + user.getCount());
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        // read filename from command line
        String filename = args[0];

        // generate report
        LineReport report = new LineReport();
        report.loadData(filename);
        report.generateReport();
    }
}