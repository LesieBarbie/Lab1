package Task5;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface Evaluatable {
    double eval(double x);
}

class Function1 implements Evaluatable {
    @Override
    public double eval(double x) {
        return Math.exp(-Math.abs(2.5 * x)) * Math.sin(x);
    }
}

class Function2 implements Evaluatable {
    @Override
    public double eval(double x) {
        return x * x;
    }
}

class ProfilingHandler implements InvocationHandler {
    private final Object target;

    public ProfilingHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long startTime = System.nanoTime();
        Object result = method.invoke(target, args);
        long endTime = System.nanoTime();
        System.out.println("[Profiling] " + method.getName() + " took " + (endTime - startTime) + " ns");
        return result;
    }
}

class TracingHandler implements InvocationHandler {
    private final Object target;

    public TracingHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String methodName = method.getName();
        System.out.println("[Tracing] " + methodName + ":");
        if (args != null) {
            StringBuilder argStr = new StringBuilder();
            for (int i = 0; i < args.length; i++) {
                argStr.append(args[i]);
                if (i < args.length - 1) argStr.append(", ");
            }
            System.out.println("\tArguments: " + argStr);
        }
        long startTime = System.nanoTime();
        Object result = method.invoke(target, args);
        long endTime = System.nanoTime();
        System.out.println("\t" + methodName + "(" + args[0] + ") = " + result);
        System.out.println("\t[Tracing] " + methodName + " took " + (endTime - startTime) + " ns");
        return result;
    }
}

public class ProxyDemo {
    public static void main(String[] args) {
        Evaluatable f1 = (Evaluatable) Proxy.newProxyInstance(
                Evaluatable.class.getClassLoader(),
                new Class<?>[]{Evaluatable.class},
                new ProfilingHandler(new Function1())
        );

        Evaluatable f2 = (Evaluatable) Proxy.newProxyInstance(
                Evaluatable.class.getClassLoader(),
                new Class<?>[]{Evaluatable.class},
                new TracingHandler(new Function2())
        );

        double x = 1.0;
        System.out.println("F1: " + f1.eval(x));
        System.out.println("F2: " + f2.eval(x));
    }
}
