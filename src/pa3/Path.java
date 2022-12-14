package pa3;

public class Path {
    private int row;
    private int column;
    private int cost;
    private Path next;

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
    
    public int getCost() {
        return cost;
    }

    public Path getNext() {
        return next;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setNext(Path next) {
        this.next = next;
    }
}
