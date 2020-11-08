package ru.otus.algorithms.homework07;

/*-
    Двоичное дерево поиска.
    Элементы можно выдать в отсортированном виде.
    Структура двоичного дерева поиска зависит то того в каком порядке приходят элементы.
    Например, если элементы приходят в уже отсортированном виде,
    то сложность добавления будет O(N^2) - плохо. Получаем односвязанный список/одинарное дерево.

    Visualization: https://www.cs.usfca.edu/~galles/visualization/BST.html

    Цель: Создать двоичное дерево поиска, реализовать один из вариантов балансировки.
    Сравнить эффективность алгоритмов на случайных и упорядоченных данных.
    1 часть, обязательная. 5 байтов.
    Создать простейшее двоичное дерево поиска. +3 байта.
    Методы к реализации:
        void insert(int x) - вставка элемента
        bool search(int x) - поиск элемента
        void remove(int x) - удаление элемента

    Пример: insert -> 10, 8, 20, 6, 9, 30, 15
    Получаем двоичное дерево поиска:

             10
            /   \
           /     \
          8       20
        /   \    /  \
       6     9  15   30

    - Можно ли выдать элементы в отсортированном виде?
    - Да

    Алгоритм(рекурсия) выдачи элементов (обход дерева) в отсортированном виде(псевдокод):
        fun go(Node n) {
            if (n == null) {
              return;
            }
            go(n.left);
            print(n.value);
            go(n.right);
        }

    В результате отображаем/печатаем элементы в следующем отсортированном порядке:
    6 8 9 10 15 20 30

    - В какой последовательности была закончена обработка каждого узла?
    - 6 9 8 15 30 20 10

    - Какова сложность такого алгоритма сортировки?
    - O(N) - O(NlogN) - O(N^2)

    Характеристики:
        Сложность:
            Search item: O(N)
            Insert item: O(n)
            Remove item: O(n)
        Online: да
*/
public class BinarySearchTree {
    private Node nearestNode = null;

    public Node insert(Node root, int key) {
        if (root == null) {
            root = new Node(key);
            return root;
        }
        if (key < root.getValue()) {
            Node node = insert(root.getLeft(), key);
            node.setParent(root);
            root.setLeft(node);
        }
        if (key > root.getValue()) {
            Node node = insert(root.getRight(), key);
            node.setParent(root);
            root.setRight(node);
        }
        return root;
    }

    // Return root node
    public Node remove(Node root, int key) throws NodeNotFoundException {
        Node removeNode = searchNode(root, key);
        Node removeNodeParent = removeNode.getParent();
        Node removeNodeLeft = removeNode.getLeft();
        Node removeNodeRight = removeNode.getRight();
        if (removeNodeParent == null) { // remove root node
            int minDelta = Math.abs(removeNodeRight.getValue() - removeNodeLeft.getValue());
            Node nearestNode = searchNearestNode(removeNodeLeft, removeNodeRight.getValue(), minDelta);
            root = removeNodeLeft;
            removeNodeRight.setParent(nearestNode);
            nearestNode.setRight(removeNodeRight);
        } else { // remove not root node
            if (removeNodeParent.getLeft() != null && removeNode.getValue() == removeNodeParent.getLeft().getValue()) {
                removeNodeParent.setLeft(removeNodeLeft);
                if (removeNodeLeft != null) {
                    removeNodeLeft.setParent(removeNodeParent);
                }
                if (removeNodeRight != null) {
                    int minDelta = Math.abs(removeNodeRight.getValue());
                    if (removeNodeLeft!= null) {
                         minDelta = Math.abs(removeNodeRight.getValue() - removeNodeLeft.getValue());
                    }
                    Node nearestNode = searchNearestNode(removeNodeLeft, removeNodeRight.getValue(), minDelta);
                    if (nearestNode == null) {
                        removeNodeRight.setParent(removeNodeParent);
                        removeNodeParent.setLeft(removeNodeRight);
                    } else {
                        removeNodeRight.setParent(nearestNode);
                        nearestNode.setRight(removeNodeRight);
                    }
                }
            }
            if (removeNodeParent.getRight() != null && removeNode.getValue() == removeNodeParent.getRight().getValue()) {
                removeNodeParent.setRight(removeNodeRight);
                if (removeNodeRight != null) {
                    removeNodeRight.setParent(removeNodeParent);
                }
                if (removeNodeLeft != null) {
                    int minDelta = Math.abs(removeNodeLeft.getValue());
                    if (removeNodeRight != null) {
                        minDelta = Math.abs(removeNodeLeft.getValue() - removeNodeRight.getValue());
                    }
                    Node nearestNode = searchNearestNode(removeNodeRight, removeNodeLeft.getValue(), minDelta);
                    if (nearestNode == null) {
                        removeNodeLeft.setParent(removeNodeParent);
                        removeNodeParent.setRight(removeNodeLeft);
                    } else {
                        removeNodeLeft.setParent(nearestNode);
                        nearestNode.setLeft(removeNodeLeft);
                    }
                }
            }
        }
        //System.out.println("nearestNode = " + nearestNode);
        return root;
    }


    public Node searchNode(Node root, int key) throws NodeNotFoundException {
        if (root == null) { // is leaf
            return null;
        }
        if (key == root.getValue()) {
            return root;
        }
        Node node = null;
        if (key < root.getValue()) {
            node = searchNode(root.getLeft(), key);
        }
        if (key > root.getValue()) {
            node = searchNode(root.getRight(), key);
        }
        if (node == null) {
            throw new NodeNotFoundException(key);
        }
        return node;
    }

    // Search nearest node of node
    private Node searchNearestNode(Node node, int key, int minDelta) {
        if (node == null) { // is leaf
            return null;
        }
        int delta = Math.abs(node.getValue() - key);
        if (delta <= minDelta) {
            minDelta = delta;
            nearestNode = node;
        }
        if (key < node.getValue()) {
            searchNearestNode(node.getLeft(), key, minDelta);
        }
        if (key > node.getValue()) {
            searchNearestNode(node.getRight(), key, minDelta);
        }
        return nearestNode;
    }

    public void traversal(Node node) {
        if (node == null) {
            return;
        }
        traversal(node.getLeft());
        System.out.println(node.getValue());
        traversal(node.getRight());
    }

}
