package ExtraTasks.Task1;

import java.lang.reflect.*;
import java.util.Scanner;

public class ReflectionInteractive {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.print("Enter class name to instantiate or 'exit' to quit: ");
            String className = scanner.nextLine();
            if ("exit".equalsIgnoreCase(className)) {
                break;
            }
            try {
                Class<?> cls = Class.forName(className);
                Object instance = createObject(cls);
                printObjectState(instance);

                while (true) {
                    System.out.print("Would you like to invoke a method? (yes/no): ");
                    String decision = scanner.nextLine();
                    if ("no".equalsIgnoreCase(decision)) {
                        break;
                    }
                    invokeMethod(instance);
                    printObjectState(instance);
                }
            } catch (ClassNotFoundException e) {
                System.out.println("Class not found: " + className);
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
    }

    private static Object createObject(Class<?> cls) throws Exception {
        Constructor<?>[] constructors = cls.getDeclaredConstructors();
        System.out.println("Available constructors for " + cls.getSimpleName() + ":");
        for (int i = 0; i < constructors.length; i++) {
            System.out.println((i + 1) + ": " + constructors[i]);
        }
        System.out.print("Choose a constructor by number: ");
        int choice = Integer.parseInt(scanner.nextLine()) - 1;
        Constructor<?> constructor = constructors[choice];

        Object[] parameters = new Object[constructor.getParameterTypes().length];
        for (int i = 0; i < parameters.length; i++) {
            System.out.print("Input value for parameter of type " + constructor.getParameterTypes()[i].getSimpleName() + ": ");
            parameters[i] = parseValue(constructor.getParameterTypes()[i], scanner.nextLine());
        }
        constructor.setAccessible(true);
        return constructor.newInstance(parameters);
    }

    private static void invokeMethod(Object obj) throws Exception {
        Method[] methods = obj.getClass().getDeclaredMethods();
        System.out.println("Available methods for " + obj.getClass().getSimpleName() + ":");
        for (int i = 0; i < methods.length; i++) {
            System.out.println((i + 1) + ": " + methods[i]);
        }
        System.out.print("Select a method by number: ");
        int methodIndex = Integer.parseInt(scanner.nextLine()) - 1;
        Method method = methods[methodIndex];

        Object[] args = new Object[method.getParameterTypes().length];
        for (int i = 0; i < args.length; i++) {
            System.out.print("Enter value for parameter of type " + method.getParameterTypes()[i].getSimpleName() + ": ");
            args[i] = parseValue(method.getParameterTypes()[i], scanner.nextLine());
        }
        method.setAccessible(true);
        Object result = method.invoke(obj, args);
        System.out.println("Method result: " + result);
    }

    private static Object parseValue(Class<?> type, String value) {
        if (type == int.class || type == Integer.class) {
            return Integer.parseInt(value);
        } else if (type == double.class || type == Double.class) {
            return Double.parseDouble(value);
        } else if (type == boolean.class || type == Boolean.class) {
            return Boolean.parseBoolean(value);
        } else if (type == String.class) {
            return value;
        }
        return null; // Default case, should be handled for more complex types
    }

    private static void printObjectState(Object obj) {
        System.out.println("Current state of the object:");
        for (Field field : obj.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                System.out.println(field.getName() + ": " + field.get(obj));
            } catch (IllegalAccessException e) {
                System.out.println("Failed to access field " + field.getName());
            }
        }
    }
}


