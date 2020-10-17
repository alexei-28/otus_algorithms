package ru.otus.algorithms.homework01;

import ru.otus.algorithms.tester.Task;

public class StringLength implements Task {
    /*-
        Дана строка символов. Вычислить её длину.

        Папка с начальными данными и результатами: src/main/resources/homework01/0.String
    */
    @Override
    public String run(String[] data) {
        return String.valueOf(data[0].length());
    }
}
