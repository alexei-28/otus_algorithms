package ru.otus.algorithms.homework05;

import ru.otus.algorithms.tester.Task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/*-
 Shell sort. Улучшенный Insertion sort.
 При сортировке Шелла сначала сравниваются и сортируются между собой значения,
 стоящие один от другого на некотором расстоянии d.
 После этого процедура повторяется для некоторых меньших значений d,
 а завершается сортировка Шелла упорядочиванием элементов при d=1 (то есть обычной сортировкой вставками).

 Help: https://en.wikipedia.org/wiki/Shellsort
 Visualization: https://www.cs.usfca.edu/~galles/visualization/ComparisonSort.html

    Характеристики:
        Используемая память: 1
        Кол-во операций сравнения: N^2
        Кол-во операций присвоения(обмена): 3N^2
        Сложность: N^1.5 + 3N = O(N^1.5)
        Стабильность: - (если происходит обмен соседних элементов, то скорее всего алгоритм стабильный)
        Online: -
        Адаптивность: +
*/
public class ShellSort implements Task {

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
        List<Integer> gapsList = getShellGaps(arraySize);
        for (int gap : gapsList) {
            // insertion sort
            for (int i = gap; i < arraySize; i++) {
                for (int j = i - gap; j >= 0; j = j - gap) {
                    long currentItem = arrayToSort[j + gap];
                    long prevItem = arrayToSort[j];
                    if (currentItem < prevItem) {
                        arrayToSort[j] = currentItem;
                        arrayToSort[j + gap] = prevItem;
                    }
                }
            }
        }
        String result = Arrays
                .stream(arrayToSort)
                .mapToObj(String::valueOf) // convert each long to a string
                .collect(Collectors.joining(" ")); // join them with " "
        return result;
    }

    // Shell's gap = N/2^k, k>= 1
    private List<Integer> getShellGaps(int n) {
        List<Integer> gapsList = new ArrayList<>();
        for (int k = 1; k < n; k++) {
            int gap = (int) (n / Math.pow(2, k));
            gapsList.add(gap);
            if (gap == 1) {
                break;
            }
        }
        return gapsList; // e.g. [5,2,1]
    }

    // Frank's gap = 2(N/2^(k+1)) + 1
    private List<Integer> getFrankGaps(int n) {
        List<Integer> gapsList = new ArrayList<>();
        for (int k = 1; k < n; k++) {
            int gap = (2 * (int) (n / Math.pow(2, (k + 1)))) + 1;
            gapsList.add(gap);
            if (gap == 1) {
                break;
            }
        }
        return gapsList; // e.g. [5,3,1]
    }

    // Hibbard's gap = 2^k - 1
    private List<Integer> getHibbardGaps(int n) {
        List<Integer> gapsList = new ArrayList<>();
        for (int k = 1; k < n; k++) {
            int gap = (int) (Math.pow(2, k) - 1);
            if (gap > n) {
                break;
            }
            gapsList.add(gap);
        }
        Collections.reverse(gapsList);
        return gapsList; // e.g. [7,3,1]
    }

    // Sedgewick's gap = 4^k + 3*2^(k-1) + 1
    private List<Integer> getSedgewickGaps(int n) {
        List<Integer> gapsList = new ArrayList<>();
        for (int k = 1; k < n; k++) {
            int gap = (int) (Math.pow(4,k) + 3*(Math.pow(2,(k-1))) + 1);
            if (gap > n) {
                break;
            }
            gapsList.add(gap);
        }
        gapsList.add(0, 1);
        Collections.reverse(gapsList);
        return gapsList; // e.g. [8,1]
    }

}
