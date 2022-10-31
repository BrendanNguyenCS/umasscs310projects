package pa1;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MaxPQ;
import java.lang.Math;

public class BestModel{
    private final int order; // Order of best model
    private final MarkovModel model1; // Training Markov model
    private final MarkovModel model2; // Training Markov model

    public BestModel(int order, String s1, String s2) {
        this.order = order;
        model1 = new MarkovModel(order, s1.replaceAll("\\s", " "));
        model2 = new MarkovModel(order, s2.replaceAll("\\s", " "));
    }

    // Returns the first Markov test model
    public MarkovModel getModel1() {
        return this.model1;
    }

    // Returns the second Markov test model
    public MarkovModel getModel2() {
        return this.model2;
    }

    // Calculates and prints out total log likelihoods
    public void printAverages(MaxPQ<DiffModel> q, String text, String filename) {
        double total1 = 0.0, total2 = 0.0;
        for(int i = 0; i < text.length() / 2; i++) {
            String sequence = text.substring(i, i + order + 1);
            // Calculate log likelihood in first Markov model
            double p1 = Math.log(getModel1().laplace(sequence));
            // Calculate log likelihood in second Markov model
            double p2 = Math.log(getModel2().laplace(sequence));

            DiffModel newModel = new DiffModel(sequence, p1, p2);
            q.insert(newModel);

            // Increment totals
            total1 += p1;
            total2 += p2;
        }
        // Calculate log likelihood average in first Markov model
        double avg1 = total1/(text.length()/2.0);
        // Calculate log likelihood average in second Markov model
        double avg2 = total2/(text.length()/2.0);
        // Calculate the difference in average log likelihoods
        double avgDifference = avg1 - avg2;
        System.out.printf("%s %.4f %.4f %.4f\n", filename, avg1, avg2, avgDifference);
    }

    // Prints out top 10 most likely substrings and their stats
    public void printLikelihoods(MaxPQ<DiffModel> q) {
        for (int i = 0; i < 10; i++) {
            DiffModel max = q.delMax();
            System.out.println(max);
        }
    }

    // Used to compare substring likelihoods
    private static class DiffModel implements Comparable<DiffModel> {
        private final String substring; // The current difference model's substring
        private final double log1; // The log likelihood for first Markov model
        private final double log2; // The log likelihood for second Markov model
        private final double logDifference; // Difference in log likelihoods

        private DiffModel(String substring, double p1, double p2) {
            this.substring = substring;
            this.log1 = p1;
            this.log2 = p2;
            this.logDifference = log1 - log2;
        }

        public double logDifference() {
            return logDifference;
        }

        public int compareTo(DiffModel other) {
            return Double.compare(Math.abs(this.logDifference), Math.abs(other.logDifference));
        }

        public boolean equals (DiffModel other) {
            if (other == null) {
                return false;
            }
            return this.logDifference() == other.logDifference();
        }

        public String toString() {
            return String.format("%s %.4f %.4f %.4f", substring, log1, log2, logDifference);
        }
    }

    public static void main(String[] args) {
        int order = Integer.parseInt(args[0]);
        // Reading file names from cmd
        String trainFile1 = args[1], trainFile2 = args[2];

        // Reading in text files (first 2 are the Markov models, last 2 are used for
        // comparison on the models)
        In in1 = new In(trainFile1), in2 = new In(trainFile2);
        // In in3 = new In(compareFile1), in4 = new In(compareFile2);

        // Read text
        String s1 = in1.readAll(), s2 = in2.readAll();

        // Create best model, queues, and print statistics
        BestModel model = new BestModel(order, s1, s2);
        for (int i = 3; i < args.length; i++) {
            // Create queue
            MaxPQ<DiffModel> q = new MaxPQ<>();
            // Read in file to compare to Markov model
            In file = new In(args[i]);
            // Replace whitespaces and make text circular
            String compareText = file.readAll().replaceAll("\\s", " ");
            String concat = compareText + compareText;

            // Print averages and top 10 likelihoods
            model.printAverages(q, concat, args[i]);
            model.printLikelihoods(q);
        }
    }
}
