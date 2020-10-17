package ru.otus.algorithms.homework04;

import java.util.LinkedList;

/*-
  PriorityQueue - приоритетная очередь.
  Один из способов реализации:
  1. Используем односвязанный список
  2. Каждый элемент имеет приоритет
  3. При вставлении элемента сначала сортируем список по приоритетам
     и затем вставляем элемент там где заканчивается приоритет.
  4. Вытаскивается элемент как из обычного списка.
*/
public class PriorityQueue {
    // лучше использовать: Deque<Node<T>> linkedListHighest = new LinkedList<>();
    private LinkedList<Node> linkedListHighest = new LinkedList<>(); // list with highest priority
    private LinkedList<Node> linkedListMiddle = new LinkedList<>();
    private LinkedList<Node> linkedListLowest = new LinkedList<>(); // list with lowest priority

    public int getSize() {
        return linkedListHighest.size() + linkedListMiddle.size() + linkedListLowest.size();
    }

    /*- Insert item to queue
        Не очень хорошо, что все приоритеты не HIGHEST и MIDDLE попадут в одну очередь.
        Было бы хорошо, если бы очереди создавались динамически и между ними не было бы захардкошенных связей.
    */
    public void enqueue(Node node) {
        LinkedList<Node> priorityList = gePriorityList(node);
        Node lastNode = null;
        if (!priorityList.isEmpty()) {
            lastNode = priorityList.getLast();
        }
        node.setNext(lastNode);
        if (node.getPriority().equals(Node.PriorityEnum.HIGHEST)) {
            linkedListHighest.add(node);
        } else if (node.getPriority().equals(Node.PriorityEnum.MIDDLE)) {
            linkedListMiddle.add(node);
        } else {
            linkedListLowest.add(node);
        }
    }

    // Get item from queue
    public Node dequeue() {
        if (!linkedListHighest.isEmpty()) {
            return linkedListHighest.poll();
        } else if (!linkedListMiddle.isEmpty()) {
            return linkedListMiddle.poll();
        }
        return linkedListLowest.poll();
    }

    private LinkedList<Node> gePriorityList(Node node) {
        if (node.getPriority().equals(Node.PriorityEnum.LOWEST)) {
            return linkedListLowest;
        } else if (node.getPriority().equals(Node.PriorityEnum.MIDDLE)) {
            return linkedListMiddle;
        }
        return linkedListHighest;
    }

    @Override
    public String toString() {
        return "PriorityQueue{" +
                "\nlinkedListHighest(" + linkedListHighest.size() + "): " + linkedListHighest +
                "\nlinkedListMiddle(" + linkedListMiddle.size() + "): " + linkedListMiddle +
                "\nlinkedListLowest(" + linkedListLowest.size() + "): " + linkedListLowest +
                '}';
    }
}
