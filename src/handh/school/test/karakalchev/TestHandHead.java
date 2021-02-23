package handh.school.test.karakalchev;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Arrays;
import java.util.Scanner;

public class TestHandHead {
    //Задаем диапазон случайных чисел для значений массивов
    private static final int VALUE_ROW_MIN = -100;
    private static final int VALUE_ROW_MAX = 100;

    //Функция возвращает уникальный размер для очередного массива
    private static int getArraySize(int[] arraysSize) {
        Arrays.sort(arraysSize);

        Random random = new Random();
        int arraySize = random.nextInt(arraysSize.length) + 1;

        while (Arrays.binarySearch(arraysSize, arraySize) >= 0) {
            arraySize = random.nextInt(arraysSize.length) + 1;
        }

        return arraySize;
    }

    //Функция заполнения массива случайными числами
    private static void fillArray(int[] row) {
        Random random = new Random();
        for (int i = 0; i < row.length; i++) {
            row[i] = random.nextInt(VALUE_ROW_MAX - VALUE_ROW_MIN + 1) + VALUE_ROW_MIN;
        }
    }

    //Функция сортировки значений массива по возрастанию
    private static void arraySortAsc(int[] row) {
        Arrays.sort(row);
    }

    //Функция сортировки значений массива по убыванию
    private static void arraySortDesc(int[] row) {
        Arrays.sort(row);

        for (int i = 0; i < row.length / 2; i++) {
            int tempValue = row[i];
            row[i] = row[row.length - i - 1];
            row[row.length - i - 1] = tempValue;
        }
    }

    //Функция создания матрицы с отсортированными значениями строк:
    // - значения строк с четными порядковыми номерами отсортированы по возрастанию;
    // - значения строк с нечетными порядковыми номерами отсортированы по убыванию.
    private static int[][] getSortedMatrix(int arraysCount) {
        int[][] matrix = new int[arraysCount][];
        int[] arraysSize = new int[arraysCount];

        for (int i = 0; i < arraysCount; i++) {
            arraysSize[0] = getArraySize(arraysSize);
            matrix[i] = new int[arraysSize[0]];

            fillArray(matrix[i]);

            if ((i + 1) % 2 == 0) {
                arraySortAsc(matrix[i]);
            } else {
                arraySortDesc(matrix[i]);
            }
        }

        return matrix;
    }

    //Функция вывода матрицы
    private static void printMatrix(int[][] matrix) {
        int i = 1;
        for (int[] array : matrix) {
            System.out.printf("массив № %d (длина %d): ", i, array.length);
            System.out.print(Arrays.toString(array));
            System.out.println();
            i++;
        }
    }

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Введите количество массивов(натуральное число): ");
            int arraysCont = scanner.nextInt();
            int[][] sortedMatrix = getSortedMatrix(arraysCont);
            System.out.println("Результат: ");
            printMatrix(sortedMatrix);
        } catch (InputMismatchException | NegativeArraySizeException e) {
            System.out.println("Не верно указано количество массивов.");
        }
    }
}
