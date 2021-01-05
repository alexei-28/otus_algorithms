package ru.otus.algorithms.homework10;

/*-
        Depth-first search (DFS) visualization: https://www.cs.usfca.edu/~galles/visualization/DFS.html
        Поиск в глубину.
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
    private int[] array = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    private boolean[] used = new boolean[array.length];

    /*-
        Алгоритм(через рекурсию):
        DFS(v)
        used(v) = true;
        foreach(u смежный V)
           if !used[u]
             DFS(u);
    */
    public void dfsByRecursive() {
        System.out.println("dfsByRecursive");

    }

    public void dfsByStack() {

    }
}
