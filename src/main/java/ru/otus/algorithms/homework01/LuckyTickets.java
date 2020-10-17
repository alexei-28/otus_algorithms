package ru.otus.algorithms.homework01;

/*-
   Счастливые билеты 20

    Билет с 2N значным номером считается счастливым,
    если сумма N первых цифр равна сумме последних N цифр.
    Посчитать, сколько существует счастливых 2N-значных билетов.
    Начальные данные: число N от 1 до 10.
    Вывод результата: количество 2N-значных счастливых билетов.


    Папка с начальными данными и результатами: src/main/resources/homework01/1.Tickets

    Help: https://habr.com/ru/post/266479/
    https://www.youtube.com/watch?time_continue=455&v=n2uT2sUUgmI&feature=emb_logo
    То есть сумма 10 элементов предыдущего столбца, у которых индекс <= нужному значению.
    Количество счастливых билетов равно сумме каждого значения в квадрате в определенном столбце:

    — для n=1 (2 цифры)-> 1^2 + 1^2 +… + 1^2 = 10;
    — для n=2 (4 цифры)-> 1^2 + 2^2 +… + 1^2 = 670;
    — для n=3 (6 цифр) -> 1^2 + 3^2 +… + 75^2 +… + 1^2 = 55 252;
    — для n=4 (8 цифр) -> 1^2 + 3^2 +… + 670^2 +… + 1^2 = 4 816 030;

*/

import ru.otus.algorithms.tester.Task;

public class LuckyTickets implements Task {
    private int n;
    private int totalDigitsInTicket;
    private int quantity;

    @Override
    public String run(String[] data) {
        setN(Integer.parseInt(data[0]));
        calculateLuckyTickets(0, 0, 0);
        return String.valueOf(quantity);
    }

    private void setN(int n) {
        this.quantity = 0;
        this.n = n;
        this.totalDigitsInTicket = n * 2;
    }

    private int getQuantity() {
        return quantity;
    }

    private void calculateLuckyTickets(int currentDigitsInTicket, int sum1, int sum2) {
        if (currentDigitsInTicket == totalDigitsInTicket) {
            if (sum1 == sum2) {
                quantity++;
            }
            return;
        }
        for (int number = 0; number <= 9; number++) {
            if (currentDigitsInTicket < (totalDigitsInTicket / 2)) {
                calculateLuckyTickets(currentDigitsInTicket + 1, sum1 + number, sum2);
            } else {
                calculateLuckyTickets(currentDigitsInTicket + 1, sum1, sum2 + number);
            }
        }
    }

      /*-
    public void calculateLuckyTickets() {
        for (int a1 = 0; a1 <= 9; a1++) {
            for (int a2 = 0; a2 <= 9; a2++) {
                for (int a3 = 0; a3 <= 9; a3++) {
                    int sum1 = a1 + a2 + a3;
                    for (int b1 = 0; b1 <= 9; b1++) {
                        for (int b2 = 0; b2 <= 9; b2++) {
                            int b3 = sum1 - b1 - b2;
                            if (b3 >= 0 && b3 <= 9) {
                                quantity++;
                            }
                        }
                    }
                }
            }
        }
    }
     */
}