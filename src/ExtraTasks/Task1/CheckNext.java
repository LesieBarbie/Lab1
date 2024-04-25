package ExtraTasks.Task1;

public class CheckNext {
    private Pair origin, position;

    public CheckNext() {
        this(new Pair(0, 0), new Pair(0, 0));
    }

    public CheckNext(int x, int y) {
        this(new Pair(x, y), new Pair(x, y));
    }

    public CheckNext(Pair origin, int x, int y) {
        this.origin = origin;
        this.position = new Pair(x, y);
    }

    public CheckNext(Pair origin, Pair position) {
        this.origin = origin;
        this.position = position;
    }

    @Override
    public String toString() {
        return "CheckNext:\norigin: " + origin + "\nposition: " + position;
    }
}
