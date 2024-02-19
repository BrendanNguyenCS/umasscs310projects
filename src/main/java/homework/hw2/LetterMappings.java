package homework.hw2;

/**
 * A class for the ASCII letter mappings<br>
 * Homework 2 Question 3
 */
public class LetterMappings {
    /**
     * Part A
     * <p>
     * Returns the number associated with the given character
     * @param character the character to translate to an integer
     * @return the integer associated with the ASCII letter ('a' = 0, 'A' = 0, 'b' = 1, 'B' = 1, etc.)
     */
    public static int CharacterToNumber(char character) {
        if (!Character.isLetter(character)) {
            throw new IllegalArgumentException("Character must be a letter");
        }
        return Character.toLowerCase(character) - 'a';
    }

    /**
     * Part B
     * <p>
     * Returns a number associated with a sequence of characters
     * @param sequence the character sequence to translate
     * @return the integer of the translated sequence
     */
    public static int StringToNumber(String sequence) {
        char[] letters = sequence.toCharArray();
        int result = 0;
        for (int i = 0; i < letters.length; i++) {
            if (!Character.isLetter(letters[i])) {
                throw new IllegalArgumentException("Sequence must be a letter");
            }
            result += (int) (Math.pow(26, letters.length - 1 - i) * (letters[i] - 'a'));
        }
        return result;
    }

    /**
     * Part C
     * <p>
     * Returns the ASCII character sequence for the given number
     * @param num the integer to translate to string
     * @return the character sequence
     */
    public static String NumberToString(int num) {
        if (num < 0) {
            throw new IllegalArgumentException("Number must be non-negative");
        }
        char[] letters = new char[]{(char)(num / 26 + 'a'), (char)(num % 26 + 'a')};
        return new String(letters);
    }
}
