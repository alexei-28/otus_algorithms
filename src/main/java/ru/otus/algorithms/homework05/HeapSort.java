package ru.otus.algorithms.homework05;

import ru.otus.algorithms.tester.Task;

import java.util.Arrays;
import java.util.stream.Collectors;

/*
    Heapsort - сортировка кучей  или пирамидальная сортировка.
    Представляем массив в виде двоичного дерева.
    Располагаем элементы массива слева направо в элементах дерева.
    1. Меняем элементы дерева до тех пор пока макс. элемент не окажется в корне(не превратим в кучу).
    Сложность поиска макс. элемента - logN
    Сложность восстановления кучи - logN
    2. Сортировка кучи (через обмен элементов). Т.е помещать макс. элемент(root, индекс = 0) в конец массива (индекс = (length-1)).
    T.е когда макс. элемент в корне и куча верная, тогда меняем root с последним элементом массива.
    Иначе делаем восстановление кучи - сравниваем родительский элемент с дочерними и меняем (в случае необходимости).
    Повторяем до тех пор пока не отсортируется весь массив.

    Начинаем с элемента (Length -1)/2, где Length - длина массива.
    Двигаемся влево по массиву.

    Parent = (Left node -1)/2 или (Right node - 1)/2
    Left node = 2*x + 1
    Right node = 2*x + 2

    Для обхода дерева,как правило, используется цикл с рекурсией.

    Характеристики:
        Используемая память: 1
        Сложность поиска макс. элемента - N*logN
 */
public class HeapSort  implements Task {
    private long[] arrayToSort;

    @Override
    public String run(String[] data) {
        int arraySize = Integer.parseInt(data[0]);
        if (arraySize == 0) {
            return "";
        }
        long[] arrayToSort = Arrays.stream(data[1].split(" ")).mapToLong(Long::parseLong).toArray();
        return sort(arraySize, arrayToSort);
    }

    private void moveMaxToRoot(int root, int size) {
        int leftNode = 2 * root + 1;
        int rightNode = leftNode + 1;
        int x = root; // max (x, leftNode, rightNode)
        if (leftNode < size && arrayToSort[x] < arrayToSort[leftNode]) {
            x = leftNode;
        }
        if (rightNode < size && arrayToSort[x] < arrayToSort[rightNode]) {
            x = rightNode;
        }

        if (x != root) {
            swap(x, root);
            moveMaxToRoot(x, size);
        }
    }

    private String sort(int arraySize, long[] arrayToSort) {
        this.arrayToSort = arrayToSort;
        for (int root = arraySize/2 -1; root >=0; root--) {
            moveMaxToRoot(root, arraySize);
        }
        for (int j = arraySize-1; j>=0;j--) {
            swap(0,j);
            moveMaxToRoot(0, j);
        }
        String result = Arrays
                .stream(arrayToSort)
                .mapToObj(String::valueOf) // convert each long to a string
                .collect(Collectors.joining(" ")); // join them with " "
        return result;
    }

    private void swap(int a, int b) {
        long x = arrayToSort[a];
        arrayToSort[a] = arrayToSort[b];
        arrayToSort[b] = x;
    }
}
