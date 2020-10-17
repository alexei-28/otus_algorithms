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
    4a. Через рекурсию.
    Сложность - экспоненциальная.

 */
public class Fibonacci_4a implements Task {

    @Override
    public String run(String[] data) {
        BigInteger n = new BigInteger(data[0]);
        BigInteger result = getFibo(n);
        return String.valueOf(result);
    }

    private BigInteger getFibo(BigInteger n) {
        if (n.intValue() == 0) {
            return BigInteger.ZERO;
        }
        if (n.intValue() == 1 || n.intValue() == 2) {
            return BigInteger.ONE;
        }
        // сумма: F(n-1) + F(n-2)
        return getFibo(n.subtract(BigInteger.ONE))
                .add(getFibo(n.subtract(new BigInteger("2"))));
    }
}
