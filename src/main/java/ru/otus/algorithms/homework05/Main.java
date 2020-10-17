package ru.otus.algorithms.homework05;

import ru.otus.algorithms.tester.Tester;

import java.io.File;

import static ru.otus.algorithms.util.FileUtil.TEST_FOLDER_BASE_PATH;

/*-
   Entry point for homework#05
*/
public class Main {
    /*-
       Реализовать рассмотренные алгоритмы сортировки и сравнить их эффективность.
    */
    public static void main(String[] args) {
        System.out.println("Start homework#05");
        String pathBase = TEST_FOLDER_BASE_PATH + File.separator + "homework05";

        // Реализовать алгоритм SelectionSort
        // 1. SELECTION SORT
        // а) random - массив из случайных чисел
        //System.out.println("random - массив из случайных чисел");
        Tester tester = new Tester(new File(pathBase + File.separator + "0.random"), new SelectionSort());

        // б) digits - массив из случайных цифр
        //System.out.println("digits - массив из случайных цифр");
        //Tester tester = new Tester(new File(pathBase + File.separator + "1.digits"), new SelectionSort());

        // sorted - на 99% отсортированный массив
        //System.out.println("sorted - на 99% отсортированный массив");
        //Tester tester = new Tester(new File(pathBase + File.separator + "2.sorted"), new SelectionSort());

        // г) revers - обратно-отсортированный массив
        //System.out.println("г) revers - обратно-отсортированный массив");
        //Tester tester = new Tester(new File(pathBase + File.separator + "3.revers"), new SelectionSort());


        // 2. BUBBLE SORT
        // а) random - массив из случайных чисел
        //System.out.println("random - массив из случайных чисел");
        //Tester tester = new Tester(new File(pathBase + File.separator + "0.random"), new BubbleSort());

        // б) digits - массив из случайных цифр
        //System.out.println("digits - массив из случайных цифр");
        //Tester tester = new Tester(new File(pathBase + File.separator + "1.digits"), new BubbleSort());

        // sorted - на 99% отсортированный массив
        //System.out.println("sorted - на 99% отсортированный массив");
        //Tester tester = new Tester(new File(pathBase + File.separator + "2.sorted"), new BubbleSort());

        // г) revers - обратно-отсортированный массив
        //System.out.println("г) revers - обратно-отсортированный массив");
        //Tester tester = new Tester(new File(pathBase + File.separator + "3.revers"), new BubbleSort());

        // 3. INSERTION SORT
        // а) random - массив из случайных чисел
        //System.out.println("random - массив из случайных чисел");
        //Tester tester = new Tester(new File(pathBase + File.separator + "0.random"), new InsertionSort());

        // б) digits - массив из случайных цифр
        //System.out.println("digits - массив из случайных цифр");
        //Tester tester = new Tester(new File(pathBase + File.separator + "1.digits"), new InsertionSort());

        // sorted - на 99% отсортированный массив
        //System.out.println("sorted - на 99% отсортированный массив");
        //Tester tester = new Tester(new File(pathBase + File.separator + "2.sorted"), new InsertionSort());

        // г) revers - обратно-отсортированный массив
        //System.out.println("г) revers - обратно-отсортированный массив");
        //Tester tester = new Tester(new File(pathBase + File.separator + "3.revers"), new InsertionSort());

        // 3. SHELL SORT
        // а) random - массив из случайных чисел
        //System.out.println("random - массив из случайных чисел");
        //Tester tester = new Tester(new File(pathBase + File.separator + "0.random"), new ShellSort());

        // б) digits - массив из случайных цифр
        //System.out.println("digits - массив из случайных цифр");
        //Tester tester = new Tester(new File(pathBase + File.separator + "1.digits"), new ShellSort());

        // sorted - на 99% отсортированный массив
        //System.out.println("sorted - на 99% отсортированный массив");
        //Tester tester = new Tester(new File(pathBase + File.separator + "2.sorted"), new ShellSort());

        // г) revers - обратно-отсортированный массив
        //System.out.println("г) revers - обратно-отсортированный массив");
        //Tester tester = new Tester(new File(pathBase + File.separator + "3.revers"), new ShellSort());



        // 4. HEAP SORT
        // а) random - массив из случайных чисел
        //System.out.println("random - массив из случайных чисел");
        //Tester tester = new Tester(new File(pathBase + File.separator + "0.random"), new HeapSort());

        // б) digits - массив из случайных цифр
        //System.out.println("digits - массив из случайных цифр");
        //Tester tester = new Tester(new File(pathBase + File.separator + "1.digits"), new HeapSort());

        // sorted - на 99% отсортированный массив
        //System.out.println("sorted - на 99% отсортированный массив");
        //Tester tester = new Tester(new File(pathBase + File.separator + "2.sorted"), new HeapSort());

        // г) revers - обратно-отсортированный массив
        //System.out.println("г) revers - обратно-отсортированный массив");
        //Tester tester = new Tester(new File(pathBase + File.separator + "3.revers"), new HeapSort());
    }
}