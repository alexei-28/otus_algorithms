package ru.otus.algorithms.homework10;

import java.util.List;

/*-
        Поиск в глубину.
        Depth-first search (DFS) visualization: https://www.cs.usfca.edu/~galles/visualization/DFS.html

        Используется когда:
        - нужно определить есть ли путь от одной до другой вершины
        - найти компоненту связанности (все вершины соединены друг с другом)
        Можно реализовать через рекурсию или через стек.


        Алгоритм(через стек):
        Пометить v как использованную
        Положить на стек(v)
        пока стек не пуст
            u = верхняя вершина на стеке
            если u цель то
                вернуть истина
            если u не использовалась(не была в стеке) то
                пометить u как использованную
                для w инцидентной/смежной с u
                    если  w не использована(не была в стеке) то
                        положить на стек w
        вернуть ложь
*/
public class DFS {
    private final List<Integer> list;
    private boolean[] used;

    public DFS(List<Integer> list) {
        this.list = list;
        used = new boolean[list.size()];
    }

    /*-
        Алгоритм(через рекурсию):
        DFS(v)
        used(v) = true;
        foreach(u смежный V)
           if !used[u]
             DFS(u);
    */
    public void dfsByRecursive(int item) {
        System.out.println("dfsByRecursive: item = " + item);
        int pos = list.indexOf(item);
        used[pos] = true;
        for (int index = 0; index < list.size(); index++) {
            int currentItem = list.get(index);
            if (!used[index]) {
                dfsByRecursive(currentItem);
            }
        }
    }

    public void dfsByStack() {

    }

    public boolean[] getUsed() {
        return used;
    }
}
