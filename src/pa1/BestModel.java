package pa1;

import edu.princeton.cs.algs4.*;
import java.lang.Math;

public class BestModel{
    /**
     * The order of the current best model
     */
    private final int order;
    /**
     * The first training Markov model
     */
    private final MarkovModel model1;
    /**
     * The second training Markov model
     */
    private final MarkovModel model2;

    /**
     * Constructor
     * @param order the other of this model
     * @param s1 the string for the training model
     * @param s2 the string for the other training model
     */
    public BestModel(int order, String s1, String s2) {
        this.order = order;
        model1 = new MarkovModel(order, s1.replaceAll("\\s", " "));
        model2 = new MarkovModel(order, s2.replaceAll("\\s", " "));
    }

    /**
     * Getter for {@link #model1}
     * @return the first training Markov model
     */
    public MarkovModel getModel1() { return model1; }

    /**
     * Getter for {@link #model2}
     * @return the second training Markov model
     */
    public MarkovModel getModel2() { return model2; }

    /**
     * Getter for {@link #order}
     * @return the order of the Markov models
     */
    public int getOrder() { return order; }

    /**
     * Calculates and prints out the total log likelihoods
     * @param q a max priority queue
     * @param text the text to scan
     * @param filename the file being scanned
     */
    public void printAverages(MaxPQ<DiffModel> q, String text, String filename) {
        double total1 = 0.0, total2 = 0.0;
        for(int i = 0; i < text.length() / 2; i++) {
            String sequence = text.substring(i, i + order + 1);
            double p1 = Math.log(getModel1().laplace(sequence));    // Calculate log likelihood in first Markov model
            double p2 = Math.log(getModel2().laplace(sequence));    // Calculate log likelihood in second Markov model
            DiffModel newModel = new DiffModel(sequence, p1, p2);
            q.insert(newModel);
            total1 += p1;
            total2 += p2;
        }

        double avg1 = total1/(text.length()/2.0);                   // Calculate first log likelihood avg
        double avg2 = total2/(text.length()/2.0);                   // Calculate second log likelihood avg
        double avgDifference = avg1 - avg2;                         // Calculate difference in avg log likelihoods
        System.out.printf("%s %.4f %.4f %.4f\n",
                filename, avg1, avg2, avgDifference);
    }

    /**
     * Prints out the top 10 most likely substrings and their stats
     * @param q a max priority queue
     */
    public void printLikelihoods(MaxPQ<DiffModel> q) {
        for (int i = 0; i < 10; i++) {
            DiffModel max = q.delMax();
            System.out.println(max);
        }
    }

    /**
     * A class used to compare substring likelihoods
     */
    private static class DiffModel implements Comparable<DiffModel> {
        /**
         * The current difference model's substring
         */
        private final String substring;
        /**
         * The log likelihood for the first Markov model
         */
        private final double log1;
        /**
         * The log likelihood for the second Markov model
         */
        private final double log2;
        /**
         * The difference in log likelihoods
         */
        private final double logDifference;

        /**
         * Constructor
         * @param substring the diff model's substring
         * @param p1 this substring's log likelihood from the first Markov model
         * @param p2 this substring's log likelihood from the second Markov model
         */
        private DiffModel(String substring, double p1, double p2) {
            this.substring = substring;
            this.log1 = p1;
            this.log2 = p2;
            this.logDifference = log1 - log2;
        }

        /**
         * Getter for {@link #logDifference}
         * @return the difference between the log likelihoods
         */
        public double logDifference() { return logDifference; }

        /**
         * Compares the log differences of this and another {@link DiffModel DiffModel}
         * @param other the object to be compared
         * @return {@code 1} if this > other, {@code -1} if this < other, {@code 0} otherwise
         */
        public int compareTo(DiffModel other) {
            return Double.compare(Math.abs(this.logDifference),
                    Math.abs(other.logDifference));
        }

        /**
         * Checks for equality of the log differences of two {@link DiffModel DiffModel}s
         * @param other the object to be checked against
         * @return {@code true} if the log differences are equal, {@code false} otherwise
         */
        public boolean equals (DiffModel other) {
            if (other == null)
                return false;
            return this.logDifference() == other.logDifference();
        }

        /**
         * @return the string representation of the {@link DiffModel DiffModel}
         */
        public String toString() {
            return String.format("%s %.4f %.4f %.4f",
                    substring, log1, log2, logDifference);
        }
    }

    public static void main(String[] args) {
        int order = Integer.parseInt(args[0]);
        String trainFile1 = args[1], trainFile2 = args[2];          // Reading file names from cmd
        In in1 = new In(trainFile1), in2 = new In(trainFile2);
        String s1 = in1.readAll(), s2 = in2.readAll();              // Read text
        BestModel model = new BestModel(order, s1, s2);             // Create best model, queues, and print statistics
        for (int i = 3; i < args.length; i++) {
            MaxPQ<DiffModel> q = new MaxPQ<>();                     // Create queue
            In file = new In(args[i]);                              // Read in file to compare to Markov model
            String compareText = file.readAll()
                    .replaceAll("\\s", " ");
            String concat = compareText + compareText;
            model.printAverages(q, concat, args[i]);                // Print averages and top 10 likelihoods
            model.printLikelihoods(q);
        }
    }
}
