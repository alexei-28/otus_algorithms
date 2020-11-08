package ru.otus.algorithms.homework07;

/*-
    АВЛ-дерево(Адельсона-Вельского,Ландиса)— сбалансированное по высоте двоичное дерево поиска:
    для каждой его вершины высота её двух поддеревьев различается не более чем на 1.

    Высота - берем максимальную высоту прямых наследников и добавляем 1.
    Дерево является сбалансированным, когда разница высот у любого узла не более -1, 0, 1.
    Процесс балансировки должен запускаться при первом же не соответствии высот.

    Visualization: https://www.cs.usfca.edu/~galles/visualization/AVLtree.html
 */
public class AVLTree extends BinarySearchTree {

    @Override
    public Node insert(Node root, int key) {
        root = super.insert(root, key);
        recalculateHeight(root);
        root = rebalance(root, key);
        return root;
    }

    /*-
        Если разница высот прямого левого наследника и прямого правого наследника >1 -> делаем малый правый поворот.
        Если разница высот прямого левого наследника и прямого правого наследника <-1 -> делаем малый левый поворот.
     */
    public Node rebalance(Node root, int key) {
        if (root == null) {
            root = new Node(key);
            return root;
        }
        Node nodeLeft = root.getLeft();
        Node nodeRight = root.getRight();
        int heightDelta = 0;
        if (nodeLeft != null && nodeRight != null) {
            heightDelta = nodeLeft.getHeight() - nodeRight.getHeight();
        } else if (nodeLeft != null) {
            heightDelta = nodeLeft.getHeight();
        } else if (nodeRight != null) {
            heightDelta = -nodeRight.getHeight();
        }
        if (heightDelta > 1) {
            /*-
            System.out.println("\nNot balance, heightDelta = " + heightDelta + ", node = " + root.getValue()
                    + "\nnodeLeft = " + nodeLeft + "\nnodeRight = " + nodeRight + "\n -> do little right turn");
             */
            Node parent = nodeLeft.getParent();
            Node parentOfParent = parent.getParent();
            if (parentOfParent == null) {
                root = nodeLeft;
            }
            parent.setLeft(null);
            parent.setHeight(0);
            nodeLeft.setRight(parent);
            nodeLeft.setParent(parentOfParent);
            parent.setParent(nodeLeft);
            root.setHeight(0);
            recalculateHeight(root);
        } else if (heightDelta < -1) {
            /*-
            System.out.println("\nNot balance, heightDelta = " + heightDelta + ", node = " + root.getValue()
                    + "\nnodeLeft = " + nodeLeft + "\nnodeRight = " + nodeRight + "\n -> do little left turn");
             */
            Node parent = nodeRight.getParent();
            Node parentOfParent = parent.getParent();
            if (parentOfParent == null) {
                root = nodeRight;
            }
            parent.setRight(null);
            parent.setHeight(0);
            nodeRight.setLeft(parent);
            nodeRight.setParent(parentOfParent);
            parent.setParent(nodeRight);
            root.setHeight(0);
            recalculateHeight(root);
        }
        rebalance(nodeLeft, key);
        rebalance(nodeRight, key);
        return root;
    }

    private void recalculateHeight(Node root) {
        if (root == null) {
            return;
        }
        Node nodeLeft = root.getLeft();
        Node nodeRight = root.getRight();
        if (nodeLeft != null && nodeRight != null) {
            root.setHeight(Math.max(nodeLeft.getHeight(), nodeRight.getHeight()) + 1);
        } else if (nodeLeft != null) {
            root.setHeight(nodeLeft.getHeight() + 1);
        } else if (nodeRight != null) {
            root.setHeight(nodeRight.getHeight() + 1);
        } else {
            root.setHeight(1); // leaf
        }
        recalculateHeight(nodeLeft);
        recalculateHeight(nodeRight);
    }

    @Override
    public Node remove(Node root, int key) throws NodeNotFoundException {
        return super.remove(root, key);
    }

    @Override
    public Node searchNode(Node root, int key) throws NodeNotFoundException {
        return super.searchNode(root, key);
    }

}
