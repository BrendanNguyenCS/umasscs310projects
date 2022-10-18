package pa1;

public class MCTest {
    public static void main(String[] args) {
        // Memo question 3
        MarkovModel model1 = new MarkovModel(2, "aabcabaacaac");
        System.out.println(model1);
        System.out.printf("%.4f\n", model1.laplace("aac"));
        System.out.printf("%.4f\n", model1.laplace("aaa"));
        System.out.printf("%.4f\n", model1.laplace("aab"));

        System.out.println();

        // Memo question 4
        MarkovModel model2 = new MarkovModel(1, "aabcabaacaac");
        System.out.println(model2);
        System.out.printf("%.4f\n", model2.laplace("aa"));
        System.out.printf("%.4f\n", model2.laplace("ab"));
        System.out.printf("%.4f\n", model2.laplace("bc"));
    }
}
