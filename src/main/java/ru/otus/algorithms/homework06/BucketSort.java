package ru.otus.algorithms.homework06;

import ru.otus.algorithms.tester.Task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*-
    Ведерная/блочная сортировка.

    Количество ведер = количеству чисел в массиве (для макс эффективности).

    Элементы в ведре - это односвязанный список.
    При добавлении элементов в ведро, сразу их сортировать (в ведре).
    Эл-ты в ведре должны добавляться по возрастанию.

  В какое ведро класть элемент массива? Номер первого ведра = 0
    - Формула: x*n/(max+1)
    , где x - элемент массива
    n - кол-во элементов в массиве

    Visualisation: https://www.cs.usfca.edu/~galles/visualization/BucketSort.html

     Характеристики:
        Сложность сортировки в ведре: k^2, где k - кол-во элементов в ведре
        Сложность: от линейной до квадратичной, O(N + k) -> O(N^2)
        Стабильность: да (если происходит обмен соседних элементов, то скорее всего алгоритм стабильный) (одинаковые элементы сохраняют свой порядок)
        Online: да (нужно ли знать последующие элементы, чтобы выполнить предыдущие части)
        Адаптивность: нет
 */

public class BucketSort implements Task {
    private List<List<Long>> bucketsMatrix;
    private int countOfBuckets = 0;

    @Override
    public String run(String[] data) {
        int arraySize = Integer.parseInt(data[0]);
        if (arraySize == 0) {
            return "";
        }
        bucketsMatrix = new ArrayList<>();
        long[] arrayToSort = Arrays.stream(data[1].split(" ")).mapToLong(Long::parseLong).toArray();
        countOfBuckets = arrayToSort.length;
        // init bucketsArray
        for (int index = 0; index < countOfBuckets; index++) {
            List<Long> bucket = new ArrayList<>();
            bucketsMatrix.add(bucket);
        }
        return sort(arrayToSort);
    }

    private String sort(long[] arrayToSort) {
        long maxItem = getMaxItem(arrayToSort);
        for (long currentItem : arrayToSort) {
            int bucketNumber = (int) (currentItem * countOfBuckets / (maxItem + 1));
            addItemToBucket(bucketNumber, currentItem);
        }
        printBucketsMatrix();
        String sortItems = getSortItems();
        return sortItems;
    }

    private void addItemToBucket(int bucketNumber, long newItem) {
        List<Long> bucketList = bucketsMatrix.get(bucketNumber);
        bucketList.add(0, newItem);
        for (int index = 1; index < bucketList.size(); index++) {
            int prevIndex = index - 1;
            long prevItem = bucketList.get(prevIndex);
            long currentItemInBucket = bucketList.get(index);
            if (currentItemInBucket < prevItem) {
                // swap items
                bucketList.set(index, newItem);
                bucketList.set(prevIndex, currentItemInBucket);
            }
        }
    }

    private long getMaxItem(long[] arrayToSort) {
        long maxItem = -1;
        for (long currentItem : arrayToSort) {
            if (currentItem > maxItem) {
                maxItem = currentItem;
            }
        }
        return maxItem;
    }

    private void printBucketsMatrix() {
        StringBuilder sb = new StringBuilder();
        for (int bucketNumber = 0; bucketNumber < countOfBuckets; bucketNumber++) {
            List<Long> currentBucket = bucketsMatrix.get(bucketNumber);
            sb.append("\n").append(bucketNumber).append(" -> ").append(currentBucket);
        }
        //System.out.println("bucketsMatrix:" + sb.toString());
    }

    private String getSortItems() {
        StringBuilder sb = new StringBuilder();
        for (int bucketNumber = 0; bucketNumber < countOfBuckets; bucketNumber++) {
            List<Long> currentBucket = bucketsMatrix.get(bucketNumber);
            for (long currentItemInBucket : currentBucket) {
                sb.append(currentItemInBucket).append(" ");
            }
        }
        return sb.toString().trim();
    }
}
