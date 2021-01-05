package ru.otus.algorithms.homework10;

import java.util.Arrays;

/*-
    Entry point for homework#10

*/
public class Main {
    public static void main(String[] args) {
        System.out.println("Homework #10!\nJDK: " + System.getProperty("java.version"));
        int[] array = new int[]{0, 4, 2, 1, 6, 5, 7, 3};
        DFS dfs = new DFS(array);
        dfs.dfsByRecursive(array[0]);
        boolean[] used = dfs.getUsed();
        System.out.println("used = " + Arrays.toString(used));
    }
}
