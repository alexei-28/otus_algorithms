package ru.otus.algorithms.homework02;

import ru.otus.algorithms.tester.Task;

import java.math.BigInteger;

/*--
    На двух строчках записаны два целых числа.
    Вывести их наибольший общий делитель.

    Решить задачу разными способами.
    1a. Алгоритм Евклида через вычитание.
    Из большего числа вычитаем меньшее.

    Пример:
    125, 15 -> НОД = 5
    200, 160 -> НОД = 40

 */
public class GCD_1a implements Task {

    @Override
    public String run(String[] data) {
        BigInteger n = new BigInteger(data[0]);
        BigInteger m = new BigInteger(data[1]);
        BigInteger result = getGCD(n, m);
        return String.valueOf(result);
    }


    /*--
       1. Из бОльшего числа вычитаем меньшее пока бОльшее число не станет меньше меньшего
       2. БОльшее число = меньшему и из бОльшего числа вычитаем меньшее
       3. Если разница = 0, то НОД = меньшему числу -> выход
       4. Из бОльшего числа вычитаем меньшее пока разница != 0
    */
    private BigInteger getGCD(BigInteger n, BigInteger m) {
        while (!n.equals(m)) {
            if (n.compareTo(m) > 0) {
                n = n.subtract(m);
            } else {
                m = m.subtract(n);
            }
        }
        return n;
    }
}
