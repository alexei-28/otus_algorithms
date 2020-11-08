package ru.otus.algorithms.homework07;

public class NodeNotFoundException extends Exception {

    public NodeNotFoundException(int key) {
        super("Key = " + key);
    }
}
