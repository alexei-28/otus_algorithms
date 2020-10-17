package ru.otus.algorithms.homework04;

import java.security.InvalidParameterException;
import java.util.Arrays;

/*-
  VectorArray - динамический массив
  append - добавляем с шагом vector. сложность N^2 (не изменилась). Хотя и выполняется в 10 раз быстрее.

*/
public class VectorArray<T> implements IArray<T> {
    private T[] array;
    private int vector; // на сколько элементов добавлять
    private int size; // так как элементов может быть меньше чем выделено памяти

    public VectorArray() {
        this(10);
    }

    public VectorArray(int vector) {
        this.vector = vector;
        this.array = (T[]) new Object[0];
        size = 0;
    }

    public T[] getArray() {
        return array;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public T get(int nr) {
        return array[nr];
    }

    @Override
    public void append(T item) {
        //System.out.println("\nAppend(before): array(" + array.length + ") = " + Arrays.toString(array));
        //"resize" вызываем только когда длина динамического массива сравнялась с длиной текущего массива.
        if (size() == array.length) {
            resize();
        }
        array[size] = item;
        size++;
        //System.out.println("Append(after): array(" + array.length + ") = " + Arrays.toString(array));
    }

    @Override
    public void update(T item, int position) {
        //System.out.println("\nUpdate(before): item " + item + " in position = " + position + ", array(" + array.length + ") = " + Arrays.toString(array));
        array[position] = item;
        //System.out.println("Update(after): item " + item + " in position = " + position + ", array(" + array.length + ") = " + Arrays.toString(array));
    }

    // Увеличиваем размер массива на vector
    private void resize() {
        T[] newArray = (T[]) new Object[size() + vector];
        System.arraycopy(array, 0, newArray, 0, array.length);
        array = newArray;
    }

    @Override
    public void insert(T item, int position) {
        if (position > array.length) {
            throw new InvalidParameterException("Insert: item = " + item + ", position = " + position + " > array's length = " + array.length);
        } else if (position == array.length) {
            append(item);
            return;
        }
        //System.out.println("\nInsert(before): item " + item + " in position = " + position + ", array(" + array.length + ") = " + Arrays.toString(array));
        T[] newArray = (T[]) new Object[size() + vector];
        for (int index = 0; index < array.length; index++) {
            T currentItem = array[index];
            if (index < position) {
                newArray[index] = currentItem;
            } else if (index == position) {
                newArray[index] = item;
                newArray[index + 1] = currentItem;
            } else { // index > position
                newArray[index + 1] = currentItem;
            }
        }
        array = newArray;
        //System.out.println("Insert(after): item " + item + " in position = " + position + ", array(" + array.length + ") = " + Arrays.toString(array));
    }

    @Override
    public void removeItem(T item) {
        //System.out.println("\nRemove(before): item " + item + ", array(" + array.length + ") = " + Arrays.toString(array));
        T[] newArray = (T[]) new Object[array.length - 1];
        boolean isRemove = false;
        for (int index = 0; index < array.length; index++) {
            T currentItem = array[index];
            if (currentItem != null && currentItem.equals(item)) {
                isRemove = true;
                continue;
            }
            if (!isRemove) {
                if (index >= newArray.length) {
                    throw new InvalidParameterException("Remove: not found item " + item + " in array = " + Arrays.toString(array));
                }
                newArray[index] = currentItem;
            } else {
                newArray[index - 1] = currentItem;
            }
        }
        array = newArray;
        //System.out.println("Remove(after): item = " + item + ", array(" + array.length + ") = " + Arrays.toString(array));
    }

    @Override
    public T remove(int position) {
        if (position >= array.length) {
            throw new InvalidParameterException("RemoveByPosition: position = " + position + " >= array's length = " + array.length);
        }
        //System.out.println("\nRemoveByPosition(before): position " + position + ", array(" + array.length + ") = " + Arrays.toString(array));
        T[] newArray = (T[]) new Object[array.length - 1];
        T removeItem = null;
        for (int index = 0; index < array.length; index++) {
            T currentItem = array[index];
            if (index < position) {
                newArray[index] = currentItem;
            } else if (index == position) {
                removeItem = array[index];
            } else { // index > position
                newArray[index - 1] = currentItem;
            }
        }
        array = newArray;
        //System.out.println("RemoveByPosition(after): position " + position + ", removeItem = " + removeItem + ", array(" + array.length + ") = " + Arrays.toString(array));
        return removeItem;
    }

    @Override
    public void clear() {
        this.array = (T[]) new Object[0];
    }
}
