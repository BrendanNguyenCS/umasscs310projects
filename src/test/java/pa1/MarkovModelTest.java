package pa1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MarkovModelTest {

    @Test
    void laplace() {
        MarkovModel model1 = new MarkovModel(2, "aabcabaacaac");
        assertEquals(0.5, model1.laplace("aac"));
        assertEquals(0.16, model1.laplace("aaa"), 0.1);
        assertEquals(0.33, model1.laplace("aab"), 0.1);

        MarkovModel model2 = new MarkovModel(1, "aabcabaacaac");
        assertEquals(0.4, model2.laplace("aa"));
        assertEquals(0.3, model2.laplace("ab"));
        assertEquals(0.4, model2.laplace("bc"));
    }
}