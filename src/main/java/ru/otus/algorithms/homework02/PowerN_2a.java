package ru.otus.algorithms.homework02;

import ru.otus.algorithms.tester.Task;
import ru.otus.algorithms.util.NumberUtil;

import java.security.InvalidParameterException;

/*
    Дано:
    Вещественное число A > 0.
    Число n >= 0 (натуральное,целое и положительное).

    Необходимо найти a^n (без использования встроенной функции power).
    Умножение начинается с 1, так как a^0=1).

    Алгоритм:
    2а. Итеративный (n умножений).
    Пример:
    2^3 = 1*2*2*2 = 8
    5^8 = 1*5*5*5*55*5*5*5 = 390625
    1.00000001^100000000 = 2.71828205323

    Т.е. сложность в этом случае = O(N)
*/
public class PowerN_2a implements Task {
    private static final int NUMBER_OF_DECIMAL_PLACES = 11;

    @Override
    public String run(String[] data) {
        double a = Double.parseDouble(data[0]);
        long n = Long.parseLong(data[1]);
        double result = power(a, n);
        result = NumberUtil.round(result, NUMBER_OF_DECIMAL_PLACES);
        return String.valueOf(result);
    }

    public double power(double a, long n) {
        if (a < 0 || n < 0 || (a == 0 && n == 0)) {
            throw new InvalidParameterException();
        }
        double result = 1;
        for (long index = 0; index < n; index++) {
            result = result * a;
        }
        return result;
    }
}
