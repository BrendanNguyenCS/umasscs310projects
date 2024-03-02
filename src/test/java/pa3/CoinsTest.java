package pa3;

import org.junit.jupiter.api.*;
import java.lang.reflect.Method;
import static org.junit.jupiter.api.Assertions.*;

class CoinsTest {
    private final String filename = "classes/pa3/coins.txt";
    private final String filename2 = "classes/pa3/coins2.txt";

    /**
     * Uses reflection to get the private method {@code printSolution}
     * @return the publicly accessible method {@code printSolution}
     * @throws NoSuchMethodException if the method does not exist
     */
    public Method getPrintSolutionMethod() throws NoSuchMethodException {
        Method m = Coins.class.getDeclaredMethod("printSolution");
        m.setAccessible(true);
        return m;
    }

    @Nested
    class getAmount {
        @Test
        void getAmount1() {
            Coins c = new Coins(filename);
            assertEquals(140, c.getAmount());
        }

        @Test
        void getAmount2() {
            Coins c = new Coins(filename2);
            assertEquals(63, c.getAmount());
        }
    }

    @Nested
    class getDenominations {
        @Test
        void getDenominations1() {
            Coins c = new Coins(filename);
            assertEquals(6, c.getDenominations().size());
        }

        @Test
        void getDenominations2() {
            Coins c = new Coins(filename2);
            assertEquals(4, c.getDenominations().size());
        }
    }

    @Test
    void makeChange() {
        Coins c = new Coins(filename);
        assertEquals(2, c.makeChange());
        c = new Coins(filename2);
        assertEquals(6, c.makeChange());
    }

    @Nested
    class howMany {
        @Test
        void howMany1() {
            Coins c = new Coins(filename);
            c.makeChange();
            assertEquals(2, c.howMany(70));
            assertEquals(0, c.howMany(34));
        }

        @Test
        void howMany2() {
            Coins c = new Coins(filename2);
            c.makeChange();
            assertEquals(3, c.howMany(1));
            assertEquals(2, c.howMany(25));
            assertEquals(1, c.howMany(10));
            assertEquals(0, c.howMany(5));
        }
    }

    @Test
    void printSolution() {
        Coins c = new Coins(filename);
        try {
            Method m = getPrintSolutionMethod();
            assertDoesNotThrow(() -> m.invoke(c));
        } catch (Exception e) {
            fail(e);
        }
    }
}