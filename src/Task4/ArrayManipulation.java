package Task4;

import java.lang.reflect.Array;

public class ArrayManipulation {
    public static void main(String[] args) {
        // Створення масивів
        Integer[] intArray = createArray(Integer.class, 2);
        String[] stringArray = createArray(String.class, 3);
        Double[] doubleArray = createArray(Double.class, 5);

        // Виведення масивів
        printArray(intArray);
        printArray(stringArray);
        printArray(doubleArray);

        // Створення матриць
        Integer[][] intMatrix = createMatrix(Integer.class, 3, 5);
        Integer[][] intMatrix2 = createMatrix(Integer.class, 4, 6);
        Integer[][] intMatrix3 = createMatrix(Integer.class, 3, 7);
        Integer[][] intMatrix4 = createMatrix(Integer.class, 2, 2);

        // Виведення матриць
        printMatrix(intMatrix);
        printMatrix(intMatrix2);
        printMatrix(intMatrix3);
        printMatrix(intMatrix4);
    }

    // Метод для створення одновимірного масиву заданого типу та розміру
    public static <T> T[] createArray(Class<T> type, int size) {
        return (T[]) Array.newInstance(type, size);
    }

    // Метод для створення матриці заданого типу та розмірів
    public static <T> T[][] createMatrix(Class<T> type, int rows, int cols) {
        return (T[][]) Array.newInstance(type, rows, cols);
    }

    // Метод для виведення одновимірного масиву
    public static void printArray(Object array) {
        int length = Array.getLength(array);
        System.out.print(array.getClass().getComponentType().getName() + "[" + length + "] = {");
        for (int i = 0; i < length; i++) {
            System.out.print(Array.get(array, i));
            if (i < length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("}");
    }

    // Метод для виведення матриці
    public static void printMatrix(Object matrix) {
        int rows = Array.getLength(matrix);
        int cols = Array.getLength(Array.get(matrix, 0));
        System.out.println(arrayComponentType(matrix) + "[" + rows + "][" + cols + "] = {");
        for (int i = 0; i < rows; i++) {
            System.out.print("  {");
            for (int j = 0; j < cols; j++) {
                System.out.print(Array.get(Array.get(matrix, i), j));
                if (j < cols - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println("}");
        }
        System.out.println("}");
    }

    // Метод для отримання імені типу компонентів масиву
    private static String arrayComponentType(Object array) {
        Class<?> componentType = array.getClass().getComponentType();
        if (componentType.isArray()) {
            return componentType.getComponentType().getName();
        } else {
            return componentType.getName();
        }
    }
}
