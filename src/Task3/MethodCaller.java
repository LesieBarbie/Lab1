package Task3;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class MethodCaller {
    public static void callMethod(Object object, String methodName, Object... params) throws FunctionNotFoundException {
        // Отримуємо клас об'єкта
        Class<?> clazz = object.getClass();

        try {
            // Отримуємо всі методи класу, включаючи приватні
            Method[] methods = clazz.getDeclaredMethods();

            // Пошук методу з вказаною назвою
            Method method = null;
            for (Method m : methods) {
                if (m.getName().equals(methodName)) {
                    method = m;
                    break;
                }
            }

            // Якщо метод не знайдено, викидаємо виключення
            if (method == null) {
                throw new FunctionNotFoundException("Method '" + methodName + "' not found in class '" + clazz.getName() + "'");
            }

            // Виведення типів та значень переданих параметрів
            Class<?>[] parameterTypes = method.getParameterTypes();
            System.out.print("Типи: ");
            for (int i = 0; i < parameterTypes.length; i++) {
                System.out.print(parameterTypes[i].getSimpleName());
                if (i < parameterTypes.length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.print(", значення: ");
            System.out.println(Arrays.toString(params));

            // Викликаємо метод з заданими параметрами
            Object result = method.invoke(object, params);

            // Виводимо результат
            System.out.println("Результат виклику: " + result);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        // Створюємо об'єкт класу Task3.TestClass
        TestClass obj = new TestClass();

        // Спробуємо викликати метод "method" з різними параметрами
        try {
            System.out.println("Task3.TestClass [a=1.0, exp(-abs(a)*x)*sin(x)]");

            // Виклик методу з параметром типу double
            callMethod(obj, "method", 1.0);

            // Виклик методу з параметрами типу double та int
            callMethod(obj, "method2", 1.0, 1);

            // Виклик іншого методу з параметрами типу int та double
            callMethod(obj, "anotherMethod", 3, 3.0);

            // Виклик методу з параметром типу String
            callMethod(obj, "stringMethod", "CS21");
        } catch (FunctionNotFoundException e) {
            e.printStackTrace();
        }
    }
}
