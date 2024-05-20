package ru.otus.algorithms.homework07;

import ru.otus.algorithms.util.DateUtil;
import ru.otus.algorithms.util.NumberUtil;

import java.util.Date;

/*-
    Entry point for homework#07

*/
public class Main {

    public static void main(String[] args) {
        System.out.println("Start homework07");
        System.out.println("Hello, OTUS Algorithms!\nJDK: " + System.getProperty("java.version"));
        long n = 1;
        int minNumber = 1;
        int maxNumber = 1_000_000_000;
        Node root = null;
        /*-
        // Двоичное дерево поиска.
        BinarySearchTree binaryTree = new BinarySearchTree();
        long startDate = System.currentTimeMillis();
        /*-
        ////////////////////////////////////////////////////////////////////////////
        // 1. Добавить N чисел в случайном порядке.
        for (int i = 0; i < n; i++) {
            int nextRandomItem = NumberUtil.getRandomNumber(minNumber, maxNumber);
            root = binaryTree.insert(root, nextRandomItem);
        }
        long duration = DateUtil.getDurationInMills(startDate, System.currentTimeMillis());
        System.out.println("Success inserted random numbers (count = " + n + ") , duration(mls) = " + duration);
        */
        /*-
        startDate = System.currentTimeMillis();
        try {
            // Искать N/10 случайных чисел в дереве.
            for (int i = 0; i <= n / 10; i++) {
                int searchKey = NumberUtil.getRandomNumber(minNumber, maxNumber);
                Node findNode = binaryTree.searchNode(root, searchKey);
            }
            long duration = DateUtil.getDurationInMills(startDate, System.currentTimeMillis());
            System.out.println("Searching random numbers (count = " + n / 10 + ") , duration(mls) = " + duration);
        } catch (NodeNotFoundException ex) {
            System.err.println(ex);
        }
        // 2. Добавить N чисел в возрастающем порядке.
        for (int i = 0; i < n; i++) {
            root = binaryTree.insert(root, i);
        }
        //long duration = DateUtil.getDurationInMills(startDate, System.currentTimeMillis());
        //System.out.println("Success inserted increment numbers (count = " + n + ") , duration, mls = " + duration);
        // Искать N/10 случайных чисел в дереве.
        startDate = System.currentTimeMillis();
        try {
        for (int i = 0; i <= n/10; i++) {
            //int searchKey = NumberUtil.getRandomNumber(minNumber, maxNumber + maxNumber);
            int searchKey = i;
            Node findNode  = binaryTree.searchNode(root, searchKey);
        }
        long duration = DateUtil.getDurationInMills(startDate, System.currentTimeMillis());
        System.out.println("Searching increment numbers (count = " + n/10 + ") , duration(mls) = " + duration);
        } catch (NodeNotFoundException ex) {
            System.err.println(ex);
        }
        ////////////////////////////////////////////////////////////////////////////
         */
        // Remove node
        /*-
        int[] numberArray = new int[]{15, 7, 3, 5, 1, 6, 25, 9, 8, 12, 20, 18, 21, 35, 32, 37};
        for (int i = 0; i < numberArray.length; i++) {
            root = binaryTree.insert(root, numberArray[i]);
        }
        try {
            binaryTree.traversal(root);
            int deleteKey = 7;
            binaryTree.remove(root, deleteKey);
            binaryTree.traversal(root);
        } catch (NodeNotFoundException ex) {
            System.err.println(ex);
        }
         */

        /////////////////////////////////////////////////////////////////////
        // AVL Tree
        int[] numberArray = new int[]{15, 7, 3, 5, 25, 9, 8, 12};
        long startDate = System.currentTimeMillis();
        AVLTree avlTree = new AVLTree();
        for (int key : numberArray) {
            root = avlTree.insert(root, key);
        }
        long duration = DateUtil.getDurationInMills(startDate, System.currentTimeMillis());
        //System.out.println("AVL tree: Success inserted random numbers (count = " + n + ") , duration(mls) = " + duration);
    }
}
