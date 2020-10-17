package ru.otus.algorithms.homework04;

import ru.otus.algorithms.util.DateUtil;

/*-
   Entry point for homework#04
*/
public class Main {
    /*-
        Динамические массивы.
        Написать метод добавления и удаления элементов:
        void add(T item, int index);
        T remove(int index); // возвращает удаляемый элемент
        по индексу во все варианты динамических массивов:
        SingleArray, VectorArray, FactorArray, MatrixArray *.
    */
    public static void main(String[] args) {
        System.out.println("Start homework04");
        // 1 задание. Динамические массивы.
        //IArray<Integer> singleArray = new SingleArray();
        //IArray<Integer> vectorArray = new VectorArray(10);
        //IArray<Integer> factorArray = new FactorArray();
        //IArray<Integer> matrixArray = new MatrixArray();

        // Tests
        //int countItem = 1500000;
        //int item = -5;
        //int updateItem = -123;
        // Test "append" operation
        //testAppend(singleArray, countItem);
        //testAppend(vectorArray, countItem);
        //testAppend(factorArray, countItem);
        //testAppend(matrixArray, countItem);

        // Test "get" operation from the middle of array
        //testGet(singleArray, countItem, countItem / 2);
        //testGet(vectorArray, countItem, countItem / 2);
        //testGet(factorArray, countItem, countItem / 2);
        //testGet(matrixArray, countItem, countItem / 2);

        // Test "insert" operation in the middle of array
        //testInsert(singleArray, countItem, item, countItem / 2);
        //testInsert(vectorArray, countItem, item, countItem / 2);
        //testInsert(factorArray, countItem, item, countItem / 2);
        //testInsert(matrixArray, countItem, item, countItem / 2);

        // Test "remove" on position operation
        //testRemoveOnPosition(singleArray, countItem, countItem / 2);
        //testRemoveOnPosition(vectorArray, countItem, countItem / 2);
        //testRemoveOnPosition(factorArray, countItem, countItem / 2);
        //testRemoveOnPosition(matrixArray, countItem, countItem / 2 - 1);

        // Test "remove" item operation
        //testRemoveItem(singleArray, countItem, countItem / 2);
        //testRemoveItem(vectorArray, countItem, countItem / 2);
        //testRemoveItem(factorArray, countItem, countItem / 2);
        //testRemoveItem(matrixArray, countItem, countItem / 2 - 1);

        // 2 задание. Приоритетная рчередь
        /*-
        PriorityQueue pq = new PriorityQueue();
        Node node50 = new Node(Node.PriorityEnum.MIDDLE, 50);
        pq.enqueue(node50);

        Node node5 = new Node(Node.PriorityEnum.HIGHEST, 5);
        pq.enqueue(node5);

        Node node500 = new Node(Node.PriorityEnum.LOWEST, 500);
        pq.enqueue(node500);

        Node node10 = new Node(Node.PriorityEnum.MIDDLE, 10);
        pq.enqueue(node10);

        Node node40 = new Node(Node.PriorityEnum.MIDDLE, 40);
        pq.enqueue(node40);

        Node node100 = new Node(Node.PriorityEnum.LOWEST, 100);
        pq.enqueue(node100);

        Node node70 = new Node(Node.PriorityEnum.MIDDLE, 70);
        pq.enqueue(node70);

        Node node1 = new Node(Node.PriorityEnum.HIGHEST, 1);
        pq.enqueue(node1);

        Node node4 = new Node(Node.PriorityEnum.HIGHEST, 4);
        pq.enqueue(node4);

        Node node7 = new Node(Node.PriorityEnum.HIGHEST, 7);
        pq.enqueue(node7);

        Node node9 = new Node(Node.PriorityEnum.HIGHEST, 9);
        pq.enqueue(node9);

        System.out.println("Before:pq(" + pq.getSize() + ") = " + pq);
        System.out.println("");
        int pqSize = pq.getSize();
        for (int index = 0; index < pqSize; index++) {
            Node currentNode = pq.dequeue();
            System.out.println("dequeue(" + pq.getSize() + "): currentNode = " + currentNode);
        }
        System.out.println("\nAfter:pq(" + pq.getSize() + ") = " + pq);
         */
    }

    private static void testAppend(IArray<Integer> singleArray, int total) {
        long startDate = System.currentTimeMillis();
        for (int index = 0; index < total; index++) {
            singleArray.append(index);
        }
        System.out.println("Append: total = " + total + ", array = " + singleArray.getClass().getSimpleName()
                + " -> duration = " + DateUtil.getDurationInMills(startDate, System.currentTimeMillis()));
    }

    private static void testUpdate(IArray<Integer> array, int total, int position, int item) {
        // 1. Fill array (append total items)
        for (int i = 0; i < total; i++) {
            array.append(i);
        }

        // 2. Update item in position in array
        long startDate = System.currentTimeMillis();
        array.update(item, position);
        System.out.println("Update: total = " + total + ", array = " + array.getClass().getSimpleName()
                + " -> duration = " + DateUtil.getDurationInMills(startDate, System.currentTimeMillis()));
    }


    private static void testGet(IArray<Integer> array, int total, int position) {
        // 1. Fill array (append total items)
        for (int i = 0; i < total; i++) {
            array.append(i);
        }

        // 2. Insert item in in position in array
        long startDate = System.currentTimeMillis();
        int findItem = array.get(position);
        System.out.println("Get: total = " + total + ", findItem = " + findItem + ", array = " + array.getClass().getSimpleName()
                + " -> duration = " + DateUtil.getDurationInMills(startDate, System.currentTimeMillis()));
    }

    private static void testInsert(IArray<Integer> array, int total, int item, int position) {
        // 1. Fill array (append total items)
        for (int i = 0; i < total; i++) {
            array.append(i);
        }

        // 2. Insert item in position in array
        long startDate = System.currentTimeMillis();
        array.insert(item, position);
        System.out.println("Insert: total = " + total + ", array = " + array.getClass().getSimpleName()
                + " -> duration = " + DateUtil.getDurationInMills(startDate, System.currentTimeMillis()));
    }

    private static void testRemoveOnPosition(IArray<Integer> array, int total, int position) {
        // 1. Fill array (append total items)
        for (int i = 0; i < total; i++) {
            array.append(i);
        }

        // 2. Remove item from array by position
        long startDate = System.currentTimeMillis();
        array.remove(position);
        System.out.println("RemoveOnPosition: total = " + total + ", array = " + array.getClass().getSimpleName()
                + " -> duration = " + DateUtil.getDurationInMills(startDate, System.currentTimeMillis()));
    }

    private static void testRemoveItem(IArray<Integer> array, int total, int item) {
        // 1. Fill array (append total items)
        for (int i = 0; i < total; i++) {
            array.append(i);
        }

        // 2. Remove item from array
        long startDate = System.currentTimeMillis();
        array.removeItem(item);
        System.out.println("Remove item: total = " + total + ", array = " + array.getClass().getSimpleName()
                + " -> duration = " + DateUtil.getDurationInMills(startDate, System.currentTimeMillis()));
    }

}
