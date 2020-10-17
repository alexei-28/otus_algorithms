package ru.otus.algorithms.homework05;

import ru.otus.algorithms.tester.Task;

import java.util.Arrays;

/*
    Алгоритм сортировки Selection/Выбором (самый простой алгоритм):

    Берем первый элемент массива, и бегаем по всему оставшемуся массиву в поисках числа меньше нашего первого элемента.
    Когда мы пробегаем весь массив, если меньшее число найдено — меняем его местами с первым элементом.
    После этого мы выбираем второй элемент, и все повторяется, пока для каждого элемента мы не пройдем оставшуюся часть массива.
    Либо искать максимальный элемент и менять его с последним элементом массива.

    Visualization: https://www.cs.usfca.edu/~galles/visualization/ComparisonSort.html

    Характеристики:
        Используемая память: 1
        Кол-во операций сравнения: N^2
        Кол-во операций присвоения(обмена): 3N
        Сложность: N^2 + 3N = O(N^2)
        Стабильность: - (если происходит обмен соседних элементов, то скорее всего алгоритм стабильный)
        Online: -
        Адаптивность: -
 */
public class SelectionSort implements Task {

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
        StringBuilder sortItems = new StringBuilder();
        for (int i = 0; i < arraySize; i++) {
            long minItem = arrayToSort[i];
            int minItemPos = i;
            for (int j = i + 1; j < arraySize; j++) {
                if (arrayToSort[j] < minItem) {
                    minItem = arrayToSort[j];
                    minItemPos = j;
                }
            }
            // swap items
            arrayToSort[minItemPos] = arrayToSort[i];
            arrayToSort[i] = minItem;

            // append min item to sortItems
            sortItems.append(minItem).append(" ");
        }
        return sortItems.toString().trim();
    }
}
