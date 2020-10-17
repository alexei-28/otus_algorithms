package ru.otus.algorithms.homework05;

import ru.otus.algorithms.tester.Task;

import java.util.Arrays;
import java.util.stream.Collectors;

/*-
    BubbleSort

    Алгоритм состоит из повторяющихся проходов по сортируемому массиву.
    За каждый проход элементы последовательно сравниваются попарно и,
    если порядок в паре неверный, выполняется обмен элементов.
    Проходы по массиву повторяются N-1 раз или до тех пор,
    пока на очередном проходе не окажется, что обмены больше не нужны,
    что означает — массив отсортирован. При каждом проходе алгоритма по внутреннему циклу,
    очередной наибольший элемент массива ставится на своё место в конце массива рядом с
    предыдущим «наибольшим элементом», а наименьший элемент перемещается на одну
    позицию к началу массива («всплывает» до нужной позиции, как пузырёк в воде — отсюда и название алгоритма).

    Visualization: https://www.cs.usfca.edu/~galles/visualization/ComparisonSort.html

    Характеристики:
        Используемая память: 1
        Кол-во операций сравнения: N^2
        Кол-во операций присвоения(обмена): 3N^2
        Сложность: N^2 + 3N = O(N^2)
        Стабильность: + (если происходит обмен соседних элементов, то скорее всего алгоритм стабильный)
        Online: -
        Адаптивность: +
 */
public class BubbleSort implements Task {

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
        int steps = 0;
        while (steps != arraySize) {
            for (int i = 1; i < arraySize - steps; i++) {
                long prevItem = arrayToSort[i - 1];
                long currentItem = arrayToSort[i];
                if (prevItem > currentItem) {
                    // swap items
                    arrayToSort[i] = prevItem;
                    arrayToSort[i - 1] = currentItem;
                }
            }
            steps++;
        }
        String result = Arrays
                .stream(arrayToSort)
                .mapToObj(String::valueOf) // convert each long to a string
                .collect(Collectors.joining(" ")); // join them with " "
        return result;
    }
}
