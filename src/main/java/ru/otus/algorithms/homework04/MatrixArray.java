package ru.otus.algorithms.homework04;

import java.security.InvalidParameterException;
import java.util.Arrays;

/*-
  MatrixArray - динамический массив.
 */
public class MatrixArray<T> implements IArray<T> {
    private IArray<IArray<T>> box; // 2D array
    private int size;
    private int countItemsInArray;// сколько элементов в одом масиве

    public MatrixArray() {
        box = new FactorArray<>();
        size = 0;
        countItemsInArray = 10;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }


    // сложность добавления - O(1)
    @Override
    public void append(T item) {
        //System.out.println("\nAppend(before): box(" + box.size() * countItemsInArray + ") = " + box2String());
        if (size == box.size() * countItemsInArray) {
            box.append(new VectorArray(countItemsInArray));
        }
        box.get(size / countItemsInArray).append(item);
        size++;
        //System.out.println("Append(after): box(" + box.size() * countItemsInArray + ") = " + box2String());
    }

    // сложность доступа к элементу - O(1)
    @Override
    public T get(int nr) {
        int rowNumber = nr / countItemsInArray;
        int columnNumber = nr % countItemsInArray;
        IArray<T> array = box.get(rowNumber);
        T item = array.get(columnNumber);
        return item;
    }

    @Override
    public void insert(T insertItem, int position) {
        if (position > size()) {
            throw new InvalidParameterException("Insert: item = " + insertItem + ", position = " + position + " > array's length = " + size());
        } else if (position == size()) {
            append(insertItem);
            return;
        }
        //System.out.println("\nInsert(before): item = " + insertItem + ", position = " + position + ", box(" + box.size() * countItemsInArray + ") = " + box2String());
        if (size == box.size() * countItemsInArray) {
            box.append(new VectorArray(countItemsInArray));
        }
        for (int rowNumber = 0; rowNumber < box.size(); rowNumber++) {
            IArray row = box.get(rowNumber);
            Object[] array = ((VectorArray) row).getArray();
            IArray newArray = new VectorArray(countItemsInArray);
            for (int column = 0; column < array.length; column++) {
                int index = rowNumber * countItemsInArray + column;
                T currentItem = (T) array[column];
                if (index == position) {
                    newArray.append(insertItem);
                    newArray.append(currentItem);
                } else {
                    newArray.append(currentItem);
                }
            }
            update((T) newArray, rowNumber);
        }
        //System.out.println("Insert(after): item = " + insertItem + ", position = " + position + ", box(" + box.size() * countItemsInArray + ") = " + box2String());
    }

    @Override
    public void removeItem(T item) {
        //System.out.println("\nRemove(before): item = " + item + ", box(" + box.size() * countItemsInArray + ") = " + box2String());
        boolean isRemove = false;
        IArray newArray = new VectorArray(countItemsInArray - 1);
        for (int rowNumber = 0; rowNumber < box.size(); rowNumber++) {
            // не забывайте про типизацию коллекций с generics.
            IArray row = box.get(rowNumber);
            Object[] array = ((VectorArray) row).getArray();
            for (int column = 0; column < array.length; column++) {
                T currentItem = (T) array[column];
                if (currentItem != null && currentItem.equals(item)) {
                    int position = rowNumber * countItemsInArray + column;
                    remove(position);
                    break;
                }
            }
        }
        //System.out.println("Remove(after): item = " + item + ", box(" + box.size() * countItemsInArray + ") = " + box2String());
    }

    @Override
    public T remove(int position) {
        if (position >= size) {
            throw new InvalidParameterException("RemoveByPosition: position = " + position + " >= array's length = " + size);
        }
        //System.out.println("\nRemoveByPosition(before): position " + position + ", box(" + box.size() * countItemsInArray + ") = " + box2String());
        int rowNumber = position / countItemsInArray;
        int columnNumber = position % countItemsInArray;
        IArray<T> currentArray = box.get(rowNumber);
        IArray newArray = new VectorArray(countItemsInArray - 1);
        T removeItem = null;
        for (int index = 0; index < currentArray.size(); index++) {
            T currentItem = currentArray.get(index);
            if (index < columnNumber) {
                //newArray.update(currentItem, index);
                newArray.append(currentItem);
            } else if (index == columnNumber) {
                removeItem = currentArray.get(index);
            } else { // index > position
                newArray.update(currentItem, index - 1);
            }
        }
        update((T) newArray, rowNumber);
        //System.out.println("RemoveByPosition(after): position " + position + ", box(" + box.size() * countItemsInArray + ") = " + box2String());
        return removeItem;
    }

    // update entire row
    @Override
    public void update(T newUpdateRow, int rowNumber) {
        //System.out.println("\nUpdate(before): box(" + box.size() * countItemsInArray + ") = " + box2String());
        IArray<IArray<T>> newBox = new FactorArray<>();
        for (int row = 0; row < box.size(); row++) {
            if (row != rowNumber) {
                newBox.append(box.get(row));
            } else {
                newBox.append((IArray<T>) newUpdateRow);
            }
        }
        box = newBox;
        //System.out.println("Update(after): box(" + box.size() * countItemsInArray + ") = " + box2String());
    }

    @Override
    public void clear() {
        box = new FactorArray<>();
    }

    private String box2String() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < box.size(); i++) {
            IArray row = box.get(i);
            Object[] array = ((VectorArray) row).getArray();
            sb.append(Arrays.toString(array));
        }
        return sb.toString();
    }

}
