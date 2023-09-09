package pa1;

import java.util.*;

public class MarkovModel {
    /**
     * The order of the Markov model
     */
    private final int k;
    /**
     * The size of the alphabet to test
     */
    private final int S;
    /**
     * The map of substrings of size k
     */
    private final TreeMap<String, Integer> substring1;
    /**
     * The map of substrings of size k+1
     */
    private final TreeMap<String, Integer> substring2;

    /**
     * Constructor
     * @param k the order of this model
     * @param s the text to create the model
     */
    public MarkovModel(int k, String s) {
        this.k = k;
        substring1 = new TreeMap<>();
        substring2 = new TreeMap<>();
        String circularString = s + s;                                      // create circular string
        HashSet<Character> uniqueLtrs = new HashSet<>();                    // Keep track of unique characters in string
        for(int i = 0; i < s.length(); i++) {                               // get substrings and add to maps
            uniqueLtrs.add(s.charAt(i));
            String shortString = circularString.substring(i, i + k);        // size k substring
            String longString = circularString.substring(i, i + k + 1);     // size k+1 substring
            if (substring1.containsKey(shortString)) {
                substring1.replace(shortString, substring1.get(shortString) + 1);
            } else {
                substring1.put(shortString, 1);
            }

            if (substring2.containsKey(longString)) {
                substring2.replace(longString, substring2.get(longString) + 1);
            } else {
                substring2.put(longString, 1);
            }
        }
        S = uniqueLtrs.size();
    }

    /**
     * Calculates the probability of a string
     * @param s the string
     * @return the decimal representation of the probability
     */
    public double laplace(String s) {
        // Get shortened substring to check map of size k
        String shtString = s.substring(0, k);
        int shtCount = substring1.get(shtString) != null
                ? substring1.get(shtString)
                : 0;
        int lngCount = substring2.get(s) != null
                ? substring2.get(s)
                : 0;
        return (lngCount + 1.0)/(shtCount + S);
    }

    /**
     * Getter for {@link #k}
     * @return the order of the Markov model
     */
    public int getK() {
        return k;
    }

    /**
     * Getter for {@link #S}
     * @return the size of the alphabet used to test substrings
     */
    public int getS() {
        return S;
    }

    /**
     * @return the string representation of the Markov model
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("S = " + S);
        substring1.forEach((key, value) -> sb.append("\n" + key + "\t" + value));
        substring2.forEach((key, value) -> sb.append("\n" + key + "\t" + value));
        return sb.toString();
    }
}
