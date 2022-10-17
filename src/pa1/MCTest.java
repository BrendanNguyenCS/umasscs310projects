package pa1;

public class MCTest {
    public static void main(String[] args) {
        MarkovModel model = new MarkovModel(2, "aabcabaacaac");
        System.out.println(model);
        System.out.printf("%.4f\n", model.laplace("aac"));
        System.out.printf("%.4f\n", model.laplace("aaa"));
        System.out.printf("%.4f\n", model.laplace("aab"));
    }
}
