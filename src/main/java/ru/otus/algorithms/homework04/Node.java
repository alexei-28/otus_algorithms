package ru.otus.algorithms.homework04;

public class Node<T> {
    private Node<T> next;
    private T data;
    private PriorityEnum priority  = PriorityEnum.LOWEST;

    enum PriorityEnum {
        LOWEST, MIDDLE, HIGHEST;
    }

    public Node(PriorityEnum priority, T data) {
        this.priority = priority;
        this.data = data;
    }

    public PriorityEnum getPriority() {
        return priority;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data:" + data +
                ",priority:" + priority +
                ",\tnext -> " + next +
                '}';
    }
}
