package ru.otus.algorithms.homework07;

public class Node {
    private int height;
    private int value;
    private Node parent;
    private Node left;
    private Node right;

    public Node getParent() {
        if (parent == null) {
            return null;
        } else {
            return parent;
        }
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node(int value) {
        this.value = value;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                " value = " + value +
                ", parent.value = " + getParentValue() +
                ", height = " + height +
                ", left = " + left +
                ", right = " + right +
                '}';
    }

    private Integer getParentValue() {
        if (parent== null) {
            return null;
        }
        return parent.getValue();
    }
}
