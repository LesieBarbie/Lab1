package Task3;

public class TestClass {
    public double method(double a) {
        return Math.exp(-Math.abs(a) * a) * Math.sin(a);
    }

    public double method2(double a, int b) {
        return Math.exp(-Math.abs(a) * b) * Math.sin(b);
    }

    public double anotherMethod(int x, double y) {
        return x * y;
    }

    public String stringMethod(String str) {
        return "I Love  " + str;
    }
}
