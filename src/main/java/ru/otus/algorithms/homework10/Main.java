package ru.otus.algorithms.homework10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*-
    Entry point for homework#10

*/
public class Main {
    public static void main(String[] args) {
        System.out.println("Homework #10!\nJDK: " + System.getProperty("java.version"));
        List<Integer> list = Arrays.asList(0, 4, 2, 1, 6, 5, 7, 3);
        DFS dfs = new DFS(list);
        dfs.dfsByRecursive(3);
        boolean[] used = dfs.getUsed();
        System.out.println("used = " + Arrays.toString(used));
    }
}
