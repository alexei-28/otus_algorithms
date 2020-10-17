package ru.otus.algorithms.homework04;

import java.security.InvalidParameterException;
import java.util.Arrays;

/*-
  SingleArray - динамический массив
  Сложность N^2. Так как мы каждый раз, при добавлении, копируем весь массив.

   Продолжительность - 1 час
*/
public class SingleArray<T> implements IArray<T> {
    private T[] array;

    public SingleArray() {
        this.array = (T[]) new Object[0];
    }

    @Override
    public int size() {
        return array.length;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    // При добавлении элемента копируется весь массив и потом добавляем новый элемент в конец массива
    @Override
    public void append(T item) {
        //System.out.println("\nAppend(before):  item " + item + ", array(" + array.length + ") = " + Arrays.toString(array));
        resize();
        array[size() - 1] = item;
        //System.out.println("Append(after): item " + item + ", array(" + array.length + ") = " + Arrays.toString(array));
    }

    @Override
    public void update(T item, int position) {
        //System.out.println("\nUpdate(before): item " + item + " in position = " + position + ", array(" + array.length + ") = " + Arrays.toString(array));
        array[position] = item;
        //System.out.println("Update(after): item " + item + " in position = " + position + ", array(" + array.length + ") = " + Arrays.toString(array));
    }

    // Увеличиваем размер массива на 1
    private void resize() {
        T[] newArray = (T[]) new Object[size() + 1];
        // вместо System.arraycopy(array, 0, newArray, 0, array.length); можно использовать Arrays.copy
        System.arraycopy(array, 0, newArray, 0, array.length);
        array = newArray;
    }

    @Override
    public T get(int nr) {
        return array[nr];
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
        T[] newArray = (T[]) new Object[size() + 1];
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
        T[] newArray = (T[]) new Object[size() - 1];
        boolean isRemove = false;
        for (int index = 0; index < array.length; index++) {
            T currentItem = array[index];
            if (currentItem.equals(item)) {
                isRemove = true;
                // стоит избегать применение continue; это запутывает код
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
            throw new InvalidParameterException("Remove: position = " + position + " >= array's length = " + array.length);
        }
        //System.out.println("\nRemove(before): position " + position + ", array(" + array.length + ") = " + Arrays.toString(array));
        T[] newArray = (T[]) new Object[size() - 1];
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
        //System.out.println("Remove(after): position " + position + ", removeItem = " + removeItem + ", array(" + array.length + ") = " + Arrays.toString(array));
        return removeItem;
    }

    @Override
    public void clear() {
        this.array = (T[]) new Object[0];
    }
}
