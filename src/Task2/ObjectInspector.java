package Task2;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Scanner;

public class ObjectInspector {
    public static void inspect(Object obj) {
        // Отримати тип об'єкта та вивести його
        Class<?> objClass = obj.getClass();
        System.out.println("Тип об'єкту: " + objClass.getName());

        // Вивести стан об'єкту (поля з їх значеннями)
        System.out.println("Стан об'єкту:");
        Field[] fields = objClass.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                System.out.println(field.getName() + " = " + field.get(obj));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        // Вивести список всіх відкритих методів класу
        System.out.println("Список всіх відкритих методів:");
        Method[] methods = objClass.getDeclaredMethods();
        int methodIndex = 1;
        for (Method method : methods) {
            System.out.println(methodIndex + "). " + method.toString());
            methodIndex++;
        }

        // Вибрати метод для виклику (лише той, який не має параметрів)
        System.out.println("Введіть порядковий номер методу [1, " + (methodIndex - 1) + "] або ('exit' щоб вийти):");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        if (input.equalsIgnoreCase("exit")) {
            return; // Завершити виконання методу inspect
        }

        try {
            int selectedMethodIndex = Integer.parseInt(input);
            if (selectedMethodIndex >= 1 && selectedMethodIndex <= (methodIndex - 1)) {
                Method selectedMethod = methods[selectedMethodIndex - 1];
                if (selectedMethod.getParameterCount() == 0) {
                    Object result = selectedMethod.invoke(obj);
                    System.out.println("Результат виклику методу: " + result);
                } else {
                    System.out.println("Вибраний метод має параметри і не може бути викликаний без їх передачі.");
                }
            } else {
                System.out.println("Неправильний вибір методу.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Неправильний ввід. Спробуйте ще раз.");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Рекурсивно викликати метод inspect для можливості виклику інших методів
        inspect(obj);
    }

    public static void main(String[] args) {
        // Створення тестового об'єкту
        Check testObject = new Check(3.0, 4.0);

        // Виклик методу inspect з тестовим об'єктом
        inspect(testObject);
    }
}
