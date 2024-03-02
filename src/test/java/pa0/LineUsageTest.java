package pa0;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LineUsageTest {

    @Test
    void addObservation() {
        LineUsage l = new LineUsage();
        l.addObservation("user1");
        assertEquals(1, l.getLines().get("user1"));
        l.addObservation("user1");
        l.addObservation("user2");
        assertEquals(2, l.getLines().get("user1"));
        assertEquals(1, l.getLines().get("user2"));
    }

    @Test
    void findMaxUsage() {
        LineUsage l = new LineUsage();
        String[] users = {"user2", "user1", "user5", "user3", "user4"};
        for (int i = 0; i < users.length; i++) {
            for (int j = 0; j < i + 1; j++) {
                l.addObservation(users[i]);
            }
        }
        Usage u = l.findMaxUsage();
        assertEquals("user4", u.getUser());
        assertEquals(5, u.getCount());
    }
}