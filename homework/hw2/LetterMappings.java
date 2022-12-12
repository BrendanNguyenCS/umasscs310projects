package hw2;

/**
 * A class for the ASCII letter mappings
 * Homework 2 Question 3
 */
public class LetterMappings {
    public static void main(String[] args) {
        int letter = CharacterToNumber('b');
        System.out.println(letter);
        int result = StringToNumber("aa");
        System.out.println(result);
        String string = NumberToString(result);
        System.out.println(string);
    }

    /**
     * Part A
     * Returns the number associated with the given character
     * @param character the character to translate to an integer
     * @return the integer associated with the ASCII letter ('a' = 0, 'A' = 0, 'b' = 1, 'B' = 1, etc.)
     */
    public static int CharacterToNumber(char character) {
        return (int)(Character.toLowerCase(character) - 'a');
    }

    /**
     * Part B
     * Returns a number associated with a sequence of characters
     * @param sequence the character sequence to translate
     * @return the integer of the translated sequence
     */
    public static int StringToNumber(String sequence) {
        char[] letters = sequence.toCharArray();
        return 26 * ((int)(letters[0]) - 'a') + (letters[1] - 'a');
    }

    // Part c

    /**
     * Part C
     * Returns the ASCII character sequence for the given number
     * @param num the integer to translate to string
     * @return the character sequence
     */
    public static String NumberToString(int num) {
        char[] letters = new char[]{(char)(num / 26 + 'a'), (char)(num % 26 + 'a')};
        return new String(letters);
    }
}
