package ru.otus.algorithms.homework06;

import ru.otus.algorithms.tester.Task;

import java.util.Arrays;
import java.util.stream.Collectors;

/*-
   QuickSort (предварительно сортируем два подмассива, т.е есть предварительная обработка)
   Общая идея алгоритма состоит в следующем:

   1. Исходный массив: [2,7,3,6,1,4,9,0,8,5]
   2. Выбираем опорный элемент: 5 (лучше брать последний элемент, в результате всегда будет обмен)
   3. Делим массив на две части (меньше/больше pivot): [2, 3, 1, 4, 0, 5, 9, 7, 8, 6], indexPivot = 5
      Массив состоит из 3-х частей: [2, 3, 1, 4, 0] [5] [9, 7, 8, 6]
      Опорный элемент является максимальным из элементов <= опорному: [2, 3, 1, 4, 0] [5]
      Т.е опорный элемент должен находиться именно в этой позиции(он отсортирован).
   4. Сортируем каждую часть отдельно: [2, 3, 1, 4, 0] и [9, 7, 8, 6]
      Рекурсивно применить шаги 2 и 3 к двум подмассивам слева и справа от опорного элемента.
      Рекурсия не применяется к массиву, в котором только один элемент или отсутствуют элементы.

   https://www.geeksforgeeks.org/quick-sort/
   Visualization: https://www.cs.usfca.edu/~galles/visualization/ComparisonSort.html

   Характеристики:
        Используемая память: 1
        Кол-во операций сравнения:
        Кол-во операций присвоения(обмена):
        Сложность деления массив на две части: O(logN)
        Сложность: O(NlogN)
        Стабильность: нет (если происходит обмен соседних элементов, то скорее всего алгоритм стабильный) (одинаковые элементы сохраняют свой порядок)
        Online: нет (нужно ли знать последующие элементы, чтобы выполнить предыдущие части)
        Адаптивность: нет (с отсортированным массивом работает плохо)

        1. Нахождение нового отсортированного элемента , после каждой итерации гарантирует, что этот элемент больше не сдвинется с этой позиции
        2. Эти отсортированные элементы не всегда идут последовательно.
        В примере(левее 5) будет так (в квадратных скобках находятся pivot-ы):

        [5]
        [0] 3 1 4 2 [5]
        [0] 1 [2] 4 3 [5]
        [0] [1] [2] 4 3 [5]
        [0] [1] [2] [3] 4 [5]
        [0] [1] [2] [3] [4] [5]

        Важно что в конце все отсортировано. Аналогично с элементами правее 5.
 */
public class QuickSort implements Task {
    private long[] arr;

    @Override
    public String run(String[] data) {
        int arraySize = Integer.parseInt(data[0]);
        if (arraySize == 0) {
            return "";
        }
        arr = Arrays.stream(data[1].split(" ")).mapToLong(Long::parseLong).toArray();
        sort(0, arraySize-1);
        String result = Arrays
                .stream(arr)
                .mapToObj(String::valueOf) // convert each long to a string
                .collect(Collectors.joining(" ")); // join them with " "
        return result;
    }

    private void sort(int firstIndex, int lastIndex) {
        if (firstIndex > lastIndex) {
            return;
        }
        int indexPivot = getIndexPivot(firstIndex, lastIndex, arr[lastIndex]);
        sort(firstIndex, indexPivot - 1); // sort left subarray
        sort(indexPivot + 1, lastIndex); // sort right subarray
      }

    private int getIndexPivot(int firstIndex, int lastIndex, long pivot) {
        int indexPivot = firstIndex - 1;
        for (int index = firstIndex; index <= lastIndex; index++) {
            if (arr[index] <= pivot) {
                indexPivot++;
                swapItemsByIndex(indexPivot, index);
            }
        }
        return indexPivot;
    }

    private void swapItemsByIndex(int firstIndex, int secondIndex) {
        long temp = arr[firstIndex];
        arr[firstIndex] = arr[secondIndex];
        arr[secondIndex] = temp;
    }
}
