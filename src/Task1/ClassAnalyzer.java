package Task1;

import java.lang.reflect.*;
import java.util.Scanner;

public class ClassAnalyzer {

    public static String analyzeClass(String classNameOrFullName) {
        try {
            Class<?> clazz = Class.forName(classNameOrFullName);
            return analyzeClass(clazz);
        } catch (ClassNotFoundException e) {
            return "Class not found: " + classNameOrFullName;
        }
    }

    public static String analyzeClass(Class<?> clazz) {
        StringBuilder result = new StringBuilder();

        // Получаем информацию о пакете
        Package pkg = clazz.getPackage();
        if (pkg != null) {
            result.append("Package: ").append(pkg.getName()).append("\n");
        }

        // Получаем модификаторы класса
        int modifiers = clazz.getModifiers();
        result.append(Modifier.toString(modifiers)).append(" ");

        // Получаем имя класса
        result.append("class ").append(clazz.getSimpleName()).append(" ");

        // Получаем базовый класс
        Class<?> superClass = clazz.getSuperclass();
        if (superClass != null && superClass != Object.class) {
            result.append("extends ").append(superClass.getSimpleName()).append(" ");
        }

        // Получаем интерфейсы, которые реализует класс
        Class<?>[] interfaces = clazz.getInterfaces();
        if (interfaces.length > 0) {
            result.append("implements ");
            for (int i = 0; i < interfaces.length; i++) {
                result.append(interfaces[i].getSimpleName());
                if (i < interfaces.length - 1) {
                    result.append(", ");
                }
            }
            result.append(" ");
        }

        result.append("{\n");

        // Получаем поля класса
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            result.append("\t").append(Modifier.toString(field.getModifiers())).append(" ");
            result.append(field.getType().getSimpleName()).append(" ");
            result.append(field.getName()).append(";\n");
        }

        // Получаем конструкторы класса
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        for (Constructor<?> constructor : constructors) {
            result.append("\t").append(Modifier.toString(constructor.getModifiers())).append(" ");
            result.append(constructor.getName()).append("(");
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            for (int i = 0; i < parameterTypes.length; i++) {
                result.append(parameterTypes[i].getSimpleName());
                if (i < parameterTypes.length - 1) {
                    result.append(", ");
                }
            }
            result.append(");\n");
        }

        // Получаем методы класса
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            result.append("\t").append(Modifier.toString(method.getModifiers())).append(" ");
            result.append(method.getReturnType().getSimpleName()).append(" ");
            result.append(method.getName()).append("(");
            Class<?>[] parameterTypes = method.getParameterTypes();
            for (int i = 0; i < parameterTypes.length; i++) {
                result.append(parameterTypes[i].getSimpleName());
                if (i < parameterTypes.length - 1) {
                    result.append(", ");
                }
            }
            result.append(");\n");
        }

        result.append("}");

        return result.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter the name of the class (or type 'exit' to quit): ");
            String className = scanner.nextLine();

            if (className.equalsIgnoreCase("exit")) {
                break; // выход из цикла при вводе "exit"
            }

            String classAnalysis = analyzeClass(className);
            System.out.println(classAnalysis);
        }

        scanner.close();
    }
}

