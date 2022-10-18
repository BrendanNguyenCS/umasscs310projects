package pa1;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MaxPQ;
import java.lang.Math;

public class BestModel{
    private int order; // Order of best model
    private MarkovModel model1; // Training Markov model
    private MarkovModel model2; // Training Markov model

    public BestModel(int order, String s1, String s2) {
        this.order = order;
        model1 = new MarkovModel(order, s1.replaceAll("\\s", " "));
        model2 = new MarkovModel(order, s2.replaceAll("\\s", " "));
    }

    public MarkovModel getModel1() {
        return this.model1;
    }

    public MarkovModel getModel2() {
        return this.model2;
    }

    public void printAverages(MaxPQ<DiffModel> q, String text, String filename) {
        double total1 = 0.0, total2 = 0.0;
        for(int i = 0; i < text.length() / 2; i++) {
            String sequence = text.substring(i, i + order + 1);
            double p1 = Math.log(getModel1().laplace(sequence));
            double p2 = Math.log(getModel2().laplace(sequence));
            DiffModel newModel = new DiffModel(sequence, p1, p2);
            q.insert(newModel);
            total1 += p1;
            total2 += p2;
        }
        double avg1 = total1/(text.length()/2.0);
        double avg2 = total2/(text.length()/2.0);
        double avgDifference = avg1 - avg2;
        System.out.printf("%s %.4f %.4f %.4f\n", filename, avg1, avg2, avgDifference);
    }

    public void printLikelihoods(MaxPQ<DiffModel> q) {
        for (int i = 0; i < 10; i++) {
            DiffModel max = q.delMax();
            System.out.println(max);
        }
    }

    private static class DiffModel implements Comparable<DiffModel> {
        private String substring;
        private double log1;
        private double log2;
        private double logDifference;

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
            if (Math.abs(this.logDifference) < Math.abs(other.logDifference)) {
                return -1;
            }
            else if (Math.abs(this.logDifference) > Math.abs(other.logDifference)) {
                return 1;
            }
            else {
                return 0;
            }
        }

        public boolean equals (DiffModel other) {
            if (other == null) {
                return false;
            }
            return this.logDifference() == other.logDifference();
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(String.format("%s %.4f %.4f %.4f", substring, log1, log2, logDifference));
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        int order = Integer.parseInt(args[0]);
        // Reading file names from cmd
        String trainFile1 = args[1], trainFile2 = args[2];
        //String compareFile1 = args[3], compareFile2 = args[4];
        // Reading in text files (first 2 are the Markov models, last 2 are used for
        // Comparison on the models);
        In in1 = new In(trainFile1), in2 = new In(trainFile2);
        // In in3 = new In(compareFile1), in4 = new In(compareFile2);

        // Read text and make them circular
        String s1 = in1.readAll(), s2 = in2.readAll();
        // String s3 = in3.readAll().replaceAll("\\s", " "), s4 = in4.readAll().replaceAll("\\s", " ");
        // String compareText1 = s3 + s3;
        // String compareText2 = s4 + s4;

        // Create best model and queues
        BestModel model = new BestModel(order, s1, s2);
        for (int i = 3; i < args.length; i++) {
            MaxPQ<DiffModel> q = new MaxPQ<>();
            In file = new In(args[i]);
            String compareText = file.readAll().replaceAll("\\s", " ");
            String concat = compareText + compareText;
            model.printAverages(q, concat, args[i]);
            model.printLikelihoods(q);
        }
        // Same thing for the second queue and print
        /*for (int i = 0; i < 10; i++) {
            DiffModel max = q2.delMax();
            System.out.println(max);
        }*/
    }
}
