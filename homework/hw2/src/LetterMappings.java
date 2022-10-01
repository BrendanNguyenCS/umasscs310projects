// Homework 2 Question 3

public class LetterMappings {
    public static void main(String[] args) {
        int letter = CharacterToNumber('b');
        System.out.println(letter);
        int result = StringToNumber("aa");
        System.out.println(result);
        String string = NumberToString(result);
        System.out.println(string);
    }

    // Part a
    public static int CharacterToNumber(char character) {
        return (int)(Character.toLowerCase(character) - 'a');
    }

    // Part b
    public static int StringToNumber(String sequence) {
        char[] letters = sequence.toCharArray();
        return 26 * ((int)(letters[0]) - 'a') + (letters[1] - 'a');
    }

    // Part c
    public static String NumberToString(int num) {
        char[] letters = new char[]{(char)(num / 26 + 'a'), (char)(num % 26 + 'a')};
        return new String(letters);
    }
}
