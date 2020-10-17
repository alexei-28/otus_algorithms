package ru.otus.algorithms.homework05;

import ru.otus.algorithms.tester.Task;

import java.util.Arrays;
import java.util.stream.Collectors;

/*-
    Insertion sort
    Сравниваем последовательно элементы и минимальный элемент перемещаем в начало массива.
    Visualization: https://www.cs.usfca.edu/~galles/visualization/ComparisonSort.html

    Характеристики:
        Используемая память: 1
        Кол-во операций сравнения: N^2
        Кол-во операций присвоения(обмена): 3N
        Сложность: N^2 + 3N = O(N^2)
        Стабильность: + (если происходит обмен соседних элементов, то скорее всего алгоритм стабильный)
        Online: +
        Адаптивность: +
 */
public class InsertionSort implements Task {

    @Override
    public String run(String[] data) {
        int arraySize = Integer.parseInt(data[0]);
        if (arraySize == 0) {
            return "";
        }
        long[] arrayToSort = Arrays.stream(data[1].split(" ")).mapToLong(Long::parseLong).toArray();
        return sort(arraySize, arrayToSort);
    }

    private String sort(int arraySize, long[] arrayToSort) {
        for (int i = 1; i < arraySize; i++) {
            for (int j = i - 1; j >= 0; j--) {
                long currentItem = arrayToSort[j + 1];
                long prevItem = arrayToSort[j];
                if (currentItem < prevItem) {
                    arrayToSort[j] = currentItem;
                    arrayToSort[j + 1] = prevItem;
                }
            }
        }
        String result = Arrays
                .stream(arrayToSort)
                .mapToObj(String::valueOf) // convert each long to a string
                .collect(Collectors.joining(" ")); // join them with " "
        return result;
    }
}
