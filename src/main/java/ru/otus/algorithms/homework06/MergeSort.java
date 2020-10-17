package ru.otus.algorithms.homework06;

import ru.otus.algorithms.tester.Task;

import java.util.Arrays;
import java.util.stream.Collectors;

/*-
   MergeSort (внешней сортировки) (нет предварительной обработки)
   Разделяем массив ровно на две части(подмассивы). Сортируем каждый подмассив отдельно. Объединяем оба подмассива.

    1.Сортировка.
    Разделяем массив на части (как в QuickSort), пока он не будет равен 1 элементу. Каждый 1 элемент является отсортированным.

    2. Слияние.
    Слияние отсортированных элементов.
    По принципу двух колод карт. Кладем 2 колоды карт на стол вверх значениями,
    и карту которая из них младше, кладем в третью результирующую стопку карт.
    В конечном итоге, если карты в какой-то колоде закончились, перекладываем их по очереди в результирующую.
    Получится слитый из двух отсортированных массивов, один, новый, отсортированный массив.

    Когда элементов немного (100, 1000), то нужно сортировать одним из предыдущих алгоритмов (например QuickSort, ShellSort).
    Т.е когда все элементы помещаются в память.

    Visualization: https://www.cs.usfca.edu/~galles/visualization/ComparisonSort.html

  Характеристики:
        Используемая память: 2
        Кол-во операций сравнения:
        Кол-во операций присвоения(обмена):
        Сложность деления массив на две части: O(logN)
        Сложность объединения: O(N)
        Сложность: O(NlogN)
        Стабильность: да (если происходит обмен соседних элементов, то скорее всего алгоритм стабильный) (одинаковые элементы сохраняют свой порядок)
        Online: да (нужно ли знать последующие элементы, чтобы выполнить предыдущие части)
        Адаптивность: нет
 */
public class MergeSort implements Task {
    private long[] arr;

    @Override
    public String run(String[] data) {
        int arraySize = Integer.parseInt(data[0]);
        if (arraySize == 0) {
            return "";
        }
        arr = Arrays.stream(data[1].split(" ")).mapToLong(Long::parseLong).toArray();
        sort(0, arr.length - 1);
        String result = Arrays
                .stream(arr)
                .mapToObj(String::valueOf) // convert each long to a string
                .collect(Collectors.joining(" ")); // join them with " "
        return result;
    }

    private void sort(int leftIndex, int rightIndex) {
        if (leftIndex >= rightIndex) {
            return;
        }
        int centerItemIndex = (leftIndex + rightIndex) / 2; // analog of pivot in QuickSort
        sort(leftIndex, centerItemIndex); // sort left subarray
        sort(centerItemIndex + 1, rightIndex); // sort right subarray
        mergeSortArrays(leftIndex, centerItemIndex, rightIndex);
    }

    // Before "mergeSortArrays" -> subarray not sort. After "mergeSortArrays" -> subarray is sort.
    private void mergeSortArrays(int leftIndex, int centerItemIndex, int rightIndex) {
        // additional memory
        long[] mergeArray = new long[rightIndex - leftIndex + 1];
        int a = leftIndex;
        int b = centerItemIndex + 1;
        int m = 0;
        while (a <= centerItemIndex && b <= rightIndex) {
            if (arr[a] < arr[b]) {
                mergeArray[m++] = arr[a++];
            } else {
                mergeArray[m++] = arr[b++];
            }
        }

        // add rest items
        while (a <= centerItemIndex) {
            mergeArray[m++] = arr[a++];
        }
        while (b <= rightIndex) {
            mergeArray[m++] = arr[b++];
        }
        // copy array from mergeArray to arr
        for (int j = leftIndex; j <= rightIndex; j++) {
            arr[j] = mergeArray[j - leftIndex];
        }
    }
}
