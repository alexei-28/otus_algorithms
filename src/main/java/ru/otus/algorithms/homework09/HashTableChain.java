package ru.otus.algorithms.homework09;

import java.util.*;

/*-
  Реализовать хэш-таблицу, использующую метод цепочек (связанные списки).

  Создаем изначальный массив с запасом.
  Новый элемент добавляем в начало цепочки.

  hash = sum(ascii code of symbol) % N
  , где N кол-во элементов в главном массиве.

  E.g.
  N = 5,
  text = cat
  hash(cat) = 99 + 97 + 116 = 312 % N = 2
  -> save to index = 2 in main array
  Если главный массив заполнен, то при добавлении нового элемента нужно:
  - удвоить размер главного массива
  - пересоздать все элементы и пересчитать hash-и(reHash) всех элементов в цепочках.

  Если цепочки становятся слишком длинные, то вместо связанных списков использовать бинарные деревья поиска.
 */
public class HashTableChain {
    private static final int CHAIN_MAX_LENGTH = 3;
    private List<LinkedList<Node>> mainList = new ArrayList<>();

    public HashTableChain() {
        // init main list with empty items
        mainList = createEmptyMainList(5);
    }

    public void put(Node node) {
        System.out.println("");
        int index = getArrayIndex(mainList.size(), node);
        LinkedList<Node> chainList = mainList.get(index);
        System.out.println("mainList(" + mainList.size() + "), index = " + index + ", chainList(" + chainList.size() + "), put node = " + node);
        if (chainList.size() < CHAIN_MAX_LENGTH) {
            Node firstNode = chainList.peekFirst();
            node.setNext(firstNode);
            chainList.addFirst(node);
            mainList.set(index, chainList);
            printMainArray();
        } else {
            System.out.println("mainList(" + mainList.size() + "), chainList_size = " + CHAIN_MAX_LENGTH + " -> call_reHash()");
            reHash();
            put(node);
            //printMainArray();
        }
    }

    /*-
      - удвоить размер главного массива
      - пересоздать все элементы и пересчитать hash-и(reHash) всех элементов в цепочках.
    */
    private void reHash() {
        System.out.println("reHash:");
        List<LinkedList<Node>> newMainList = createEmptyMainList(mainList.size() * 2); // extend list by size * 2
        for (LinkedList<Node> currentOldChain : mainList) {
            for (Node node : currentOldChain) {
                int newIndex = getArrayIndex(newMainList.size(), node);
                LinkedList<Node> newChainList = newMainList.get(newIndex);
                Node firstNode = newChainList.peekFirst();
                node.setNext(firstNode);
                newChainList.addFirst(node);
                newMainList.set(newIndex, newChainList);
            }
        }
        mainList = newMainList;
    }

    /*-
        Возвращаем java.util.Optional<Node>.
        Так как в случае не успешного поиска лучше возвращать Options<Node> вместо null.
     */
    public Optional<Node> get(String key) {
        for (LinkedList<Node> currentChain : mainList) {
            for (Node currentNode : currentChain) {
                String currentKey = currentNode.getKey();
                if (key.equals(currentKey)) {
                    return Optional.of(currentNode);
                }
            }
        }
        return Optional.empty();
    }

    private int getArrayIndex(int arraySize, Node node) {
        String key = node.getKey();
        int hashCode = getHashCode(key);
        int index = hashCode % arraySize;
        //System.out.println("arraySize = " + arraySize +", key = " + key + ", hashCode = " + hashCode + " -> index = " + index);
        return index;
    }

    private int getHashCode(String key) {
        int result = 0;
        char[] chars = key.toCharArray();
        for (char currentChar : chars) {
            result = result + (int) currentChar;
        }
        return result;
    }

    private void printMainArray() {
        for (int index = 0; index < mainList.size(); index++) {
            LinkedList<Node> chainList = mainList.get(index);
            System.out.println(index + " / " + mainList.size() + " -> chainList(" + chainList.size() + ") " + chainList);
        }
    }

    private List<LinkedList<Node>> createEmptyMainList(int endPos) {
        List<LinkedList<Node>> mainList = new ArrayList<>();
        for (int index = 0; index < endPos; index++) {
            mainList.add(new LinkedList<>());
        }
        return mainList;
    }

}
