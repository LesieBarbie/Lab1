package ExtraTasks.Task1;

public class Pair {
    private int x, y;

    public Pair() {
        this(0, 0);
    }

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
