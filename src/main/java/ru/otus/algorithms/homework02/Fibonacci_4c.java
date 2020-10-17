package ru.otus.algorithms.homework02;

import ru.otus.algorithms.tester.Task;

import java.math.BigDecimal;
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
    4c. По формуле золотого сечения

    f = (1 + sqrt(5))/2

    F(n) = f^n/sqrt(5) + 1/2

 */
public class Fibonacci_4c implements Task {

    @Override
    public String run(String[] data) {
        BigInteger n = new BigInteger(data[0]);
        BigInteger result = getFibo(n);
        return String.valueOf(result);
    }

    private BigInteger getFibo(BigInteger n) {
        double f = (1 + Math.sqrt(5)) / 2;
        double powerF = Math.pow(f, n.intValue());
        double fnDouble = powerF / Math.sqrt(5) + 0.5;
        BigInteger result = BigDecimal.valueOf(fnDouble).toBigInteger();
        return result;
    }
}
