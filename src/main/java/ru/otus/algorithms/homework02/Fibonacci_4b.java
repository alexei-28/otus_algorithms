package ru.otus.algorithms.homework02;

import ru.otus.algorithms.tester.Task;

import java.math.BigInteger;

/*--
    На первой строчке записано целое число N >= 0.
    Найти и вывести на экран точное значение N-ого числа Фибоначчи.

    F(n) = F(n-1) + F(n-2)
    F(1) = F(2) = 1

    Пример:
    N = 0 -> Fibo = 0
    N = 2 -> Fibo = 1
    N = 8 -> Fibo = 21

    Решить задачу разными способами.
    4b. Через итерацию
 */
public class Fibonacci_4b implements Task {
    @Override
    public String run(String[] data) {
        BigInteger n = new BigInteger(data[0]);
        BigInteger result = getFibo(n);
        return String.valueOf(result);
    }

    private BigInteger getFibo(BigInteger n) {
        if (n.intValue() == 1 || n.intValue() == 2) {
            return BigInteger.ONE;
        }
        BigInteger result = BigInteger.ZERO;
        BigInteger f1 = BigInteger.ONE;
        BigInteger f2 = BigInteger.ONE;
        for (int index = 3; index <= n.intValue(); index++) {
            result = f1.add(f2); // сумма
            f1 = f2;
            f2 = result;
        }
        return result;
    }
}
