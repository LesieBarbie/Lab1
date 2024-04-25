package Task2;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Scanner;

class Check {
    private double x;
    private double y;

    public Check(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double Dist() {
        return Math.sqrt(x * x + y * y);
    }

    public void setRandomData() {
        this.x = Math.random() * 10;
        this.y = Math.random() * 10;
    }

    public void setData(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Task2.Check{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}