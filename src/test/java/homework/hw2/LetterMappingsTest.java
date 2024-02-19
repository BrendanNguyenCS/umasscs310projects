package homework.hw2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LetterMappingsTest {

    @Test
    void characterToNumber() {
        assertEquals(1, LetterMappings.CharacterToNumber('b'));
        assertEquals(0, LetterMappings.CharacterToNumber('a'));
        assertEquals(25, LetterMappings.CharacterToNumber('z'));
        assertEquals(0, LetterMappings.CharacterToNumber('A'));
        assertThrows(IllegalArgumentException.class, () -> LetterMappings.CharacterToNumber('1'));
    }

    @Test
    void stringToNumber() {
        assertEquals(0, LetterMappings.StringToNumber("a"));
        assertEquals(467, LetterMappings.StringToNumber("arz"));
        assertEquals(LetterMappings.StringToNumber("arz"), LetterMappings.StringToNumber("rz"));
        assertThrows(IllegalArgumentException.class, () -> LetterMappings.StringToNumber("1"));
        assertThrows(IllegalArgumentException.class, () -> LetterMappings.StringToNumber("AZ1"));
    }

    @Test
    void numberToString() {
        assertEquals("aa", LetterMappings.NumberToString(0));
        assertEquals("ab", LetterMappings.NumberToString(1));
        assertEquals("ba", LetterMappings.NumberToString(26));
        assertEquals("rz", LetterMappings.NumberToString(467));
        assertThrows(IllegalArgumentException.class, () -> LetterMappings.NumberToString(-1));
    }
}