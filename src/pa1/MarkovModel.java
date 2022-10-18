package pa1;

import java.util.HashSet;
import java.util.TreeMap;

public class MarkovModel {
    private int k; // Order of model
    private int S; // Size of alphabet
    private TreeMap<String, Integer> substring1; // Map of substrings of size k
    private TreeMap<String, Integer> substring2; // Map of substrings of size k+1

    public MarkovModel(int k, String s) {
        // Initialize known fields
        this.k = k;
        substring1 = new TreeMap<String, Integer>();
        substring2 = new TreeMap<String, Integer>();
        // create circular string to take into account combinations on the end
        // of the strings
        String circularString = s + s;
        // A set to keep track of unique characters in the string
        HashSet<Character> uniqueLtrs = new HashSet<>();
        // get substrings and add to maps
        for(int i = 0; i < s.length(); i++) {
            // Add current character at index i to set
            uniqueLtrs.add(s.charAt(i));
            // size k substring
            String shortString = circularString.substring(i, i + k);
            // size k+1 substring
            String longString = circularString.substring(i, i + k + 1);

            // update if substring exists in map
            if (substring1.containsKey(shortString)) {
                substring1.replace(shortString, substring1.get(shortString) + 1);
            } else {
                // add to map otherwise
                substring1.put(shortString, 1);
            }

            // update if substring exists in map
            if (substring2.containsKey(longString)) {
                substring2.replace(longString, substring2.get(longString) + 1);
            } else {
                // add to map otherwise
                substring2.put(longString, 1);
            }
        }
        S = uniqueLtrs.size();
    }
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

    // Returns the order of the Markov model
    public int getK() {
        return this.k;
    }

    // Returns the size of the alphabet
    public int getS() {
        return this.S;
    }

    // Returns the string representation of the MarkovModel
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("S = " + this.S);
        // Add size k keys and values
        substring1.forEach((key, value) -> {
            sb.append("\n" + key + "\t" + value);
        });
        // Add size k+1 keys and values
        substring2.forEach((key, value) -> {
            sb.append("\n" + key + "\t" + value);
        });
        return sb.toString();
    }
}
