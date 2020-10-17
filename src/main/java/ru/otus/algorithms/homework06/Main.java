package ru.otus.algorithms.homework06;

import ru.otus.algorithms.tester.Tester;

import java.io.File;

import static ru.otus.algorithms.util.FileUtil.TEST_FOLDER_BASE_PATH;

/*-
   Entry point for homework#06
*/
public class Main {

    public static void main(String[] args) {
        System.out.println("Start homework#06");
        String pathBase = TEST_FOLDER_BASE_PATH + File.separator + "homework06";

        // Реализовать алгоритм QuickSort
        // а) random - массив из случайных чисел
        //System.out.println("random - массив из случайных чисел");
        Tester tester = new Tester(new File(pathBase + File.separator + "0.random"), new QuickSort());

        // б) digits - массив из случайных цифр
        //System.out.println("digits - массив из случайных цифр");
        //Tester tester = new Tester(new File(pathBase + File.separator + "1.digits"), new QuickSort());

        // sorted - на 99% отсортированный массив
        //System.out.println("sorted - на 99% отсортированный массив");
        //Tester tester = new Tester(new File(pathBase + File.separator + "2.sorted"), new QuickSort());

        // г) revers - обратно-отсортированный массив
        //System.out.println("г) revers - обратно-отсортированный массив");
        //Tester tester = new Tester(new File(pathBase + File.separator + "3.revers"), new QuickSort());


        // Реализовать алгоритм MergeSort
        // а) random - массив из случайных чисел
        //System.out.println("random - массив из случайных чисел");
        //Tester tester = new Tester(new File(pathBase + File.separator + "0.random"), new MergeSort());

        // б) digits - массив из случайных цифр
        //System.out.println("digits - массив из случайных цифр");
        //Tester tester = new Tester(new File(pathBase + File.separator + "1.digits"), new MergeSort());

        // sorted - на 99% отсортированный массив
        //System.out.println("sorted - на 99% отсортированный массив");
        //Tester tester = new Tester(new File(pathBase + File.separator + "2.sorted"), new MergeSort());

        // г) revers - обратно-отсортированный массив
        //System.out.println("г) revers - обратно-отсортированный массив");
        //Tester tester = new Tester(new File(pathBase + File.separator + "3.revers"), new MergeSort());

        // Блочная сортировка.
        //Tester tester = new Tester(new File(pathBase + File.separator + "0.random"), new BucketSort());
        //Tester tester = new Tester(new File(pathBase + File.separator + "1.digits"), new BucketSort());
        //Tester tester = new Tester(new File(pathBase + File.separator + "2.sorted"), new BucketSort());
        //Tester tester = new Tester(new File(pathBase + File.separator + "3.revers"), new BucketSort());
    }
}
