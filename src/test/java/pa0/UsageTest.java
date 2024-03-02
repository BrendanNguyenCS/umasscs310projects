package pa0;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsageTest {

    @Test
    void getCount() {
        Usage u = new Usage("user1", 1);
        assertEquals(1, u.getCount());
    }

    @Test
    void setCount() {
        Usage u = new Usage("user1", 1);
        u.setCount(2);
        assertEquals(2, u.getCount());
    }

    @Test
    void getUser() {
        Usage u = new Usage("user1", 1);
        assertEquals("user1", u.getUser());
    }
}