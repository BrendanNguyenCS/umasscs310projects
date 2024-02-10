package pa3;

import edu.princeton.cs.algs4.*;
import java.util.LinkedList;

/**
 * A class that encapsulates ASCII shifting
 */
public class MoveToFront {
    /**
     * Applies move-to-front encoding
     * @param f the file name to read from
     */
    public static void encode(String f) {
        BinaryIn in = new BinaryIn(f);
        BinaryOut out = new BinaryOut();
        LinkedList<Character> encode = initList();
        while(!in.isEmpty()) {
            char c = in.readChar();
            int index = encode.indexOf(c);
            out.write(index, 8);
            encode.remove(index);
            encode.addFirst(c);
        }
        out.close();
    }

    /**
     * Applies move-to-front decoding
     * @param f the file name to read from
     */
    public static void decode(String f) {
        BinaryIn in = new BinaryIn(f);
        BinaryOut out = new BinaryOut();
        LinkedList<Character> decode = initList();
        while (!in.isEmpty()) {
            int index = in.readChar();
            char c = decode.remove(index);
            out.write(c);
            decode.addFirst(c);
        }
        out.close();
    }

    /**
     * Initialize a new LinkedList of ASCII Extended characters
     * @return the filled {@link LinkedList} object
     */
    private static LinkedList<Character> initList() {
        LinkedList<Character> list = new LinkedList<>();
        for (char c = 0; c < 256; c++) {
            list.add(c);
        }
        return list;
    }

    public static void main(String[] args) {
        if (args[0].equals("-")) {
            encode(args[1]);
        } else if (args[0].equals("+")) {
            decode(args[1]);
        } else {
            throw new IllegalArgumentException("That is not a valid operation.");
        }
    }
}
