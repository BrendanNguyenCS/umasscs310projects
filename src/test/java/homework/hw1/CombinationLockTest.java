package homework.hw1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CombinationLockTest {

    @Test
    void open() {
        CombinationLock lock = new CombinationLock(1, 2, 3);
        assertTrue(lock.open(1, 2, 3));
        assertFalse(lock.open(3, 2, 1));
        assertFalse(lock.open(1, 2, 4));
    }

    @Test
    void changeCombo() {
        CombinationLock lock = new CombinationLock(1, 2, 3);
        assertTrue(lock.changeCombo(1, 2, 3, 4, 5, 6));
        assertFalse(lock.changeCombo(1, 2, 3, 4, 5, 6));
    }
}