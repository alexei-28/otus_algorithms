package ru.otus.algorithms.homework04;

public interface IArray<T> {
    int size();
    boolean isEmpty();
    void append(T item);
    void update(T item, int position);
    T get(int nr);
    void insert(T item, int position);
    void removeItem(T item);
    T remove(int index);
    void clear();
}
