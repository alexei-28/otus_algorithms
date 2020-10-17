package ru.otus.algorithms.homework02;

import ru.otus.algorithms.tester.Task;

import java.math.BigInteger;

/*--
    На двух строчках записаны два целых числа.
    Вывести их наибольший общий делитель.

    2. Алгоритм Евклида через остаток

    Пример:
    125, 15 -> НОД = 5
    200, 160 -> НОД = 40

 */
public class GCD_1b implements Task {

    @Override
    public String run(String[] data) {
        BigInteger n = new BigInteger(data[0]);
        BigInteger m = new BigInteger(data[1]);
        BigInteger result = getGCD(n, m);
        return String.valueOf(result);
    }

    /*--
       1. Делим бОльшее число на меньшее
       2. Если нет остатка, то меньшее число - это НОД -> выход
       3. Если есть остаток, то большее число = меньшему и делим на остаток от деления
       4. Пункт 1
    */
    private BigInteger getGCD(BigInteger n, BigInteger m) {
        BigInteger remainder;
        if (n.compareTo(m) > 0) {
            remainder = n.mod(m);
        } else {
            remainder = m.mod(n);
        }
        if (remainder.compareTo(BigInteger.ZERO) == 0) {
            return m;
        }
        return getGCD(m, remainder);
    }
}
