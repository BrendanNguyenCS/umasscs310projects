package pa1;

public class BestModel {
    private MarkovModel model1;
    private MarkovModel model2;

    public BestModel(int order, String s1, String s2) {
        model1 = new MarkovModel(order, s1.replaceAll("\\s", " "));
        model2 = new MarkovModel(order, s2.replaceAll("\\s", " "));
    }

    public MarkovModel getModel1() {
        return this.model1;
    }

    public MarkovModel getModel2() {
        return this.model2;
    }

    private class DiffModel {

    }
}
