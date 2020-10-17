package ru.otus.algorithms.homework02;

import ru.otus.algorithms.tester.Task;
import ru.otus.algorithms.util.MathUtil;
import ru.otus.algorithms.util.NumberUtil;

import java.security.InvalidParameterException;

/*--
    Дано:
    Вещественное число A > 0.
    Число n > = 0 (натуральное,целое и положительное).
    Необходимо найти a^n (без использования встроенной функции power).
    Умножение начинается с 1, так как a^0=1).

    Алгоритм:
    2b. Через степень двойки с домножением
    1. Если n - это точная степень двойки, то количество операций = a^(2^log n))
    Пример:
    5^4 =
        5 * 5 = 5^2
        5^2 * 5^2 = 5^4

    Итого 5^4 = 5^(2^(log4) = 5^(2^2) = 5^4 = 625
    Т.е. количество операций было сокращено с 4 до 2.
    Т.е. сложность в этом случае = log n

    2. Если n - это не степень двойки, то нам необходимо добавить домножений пока не дойдем до n.
        Пример:
        5^10 =
            5 * 5 = 5^2
            5^2 * 5^2 = 5^4
            5^4 * 5^4 = 5^8
            5^8 * 5 * 5 = 5^8 * 5^2 = 5^10 (сделали 2 домножения)

        Итого 5^10 = 5^8 * 5^2 = 9765625
        Т.е 3 операции + 2 операции (5 -  = 5 операций.

    2.1
        Пример:
        2^31 =
            2 * 2 = 2^2
            2^2 * 2^2 = 2^4
            2^4 * 2^4 = 2^8
            2^8 * 2^8 = 2^16
            2^16 * 2^15 = 2^31 (сделали 15 домножений)

        Итого 2^31 = 2^16 * 2^15 = 2147483648
        Т.е 4 операции(т.к 2^4=16) + 15 операций = 19 операций.

     Т.е. сложность в этом случае = logN + N/2 = O(N)
*/
public class PowerN_2b implements Task {
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
        if (n == 0) {
            return 1;
        }
        if (MathUtil.isPowerOfTwo(n)) {
            return powerOf2(a, n);
        } else {
            return notPowerOf2(a, n);
        }
    }

    private double powerOf2(double a, long n) {
        double result = a;
        long countOfSteps = (long) MathUtil.log2(n);
        //System.out.println("powerOf2: a = " + a + ", n = " + n + " -> countOfSteps = " + countOfSteps);
        for (int index = 0; index < countOfSteps; index++) {
            result = result * result;
        }
        //System.out.println("powerOf2: a = " + a + ", n = " + n + " -> result " + result);
        return result;
    }

    private double notPowerOf2(double a, long n) {
        //System.out.println("\nnotPowerOf2: a = " + a + ", n = " + n);
        double result = 0;
        long logN = (long) MathUtil.log2(n);
        //System.out.println("notPowerOf2: logN = " + logN);
        long quantityMultiply = 1;
        double result1 = a;
        for (int index = 0; index < logN; index++) {
            result1 = result1 * result1;
            // Вычисляем количество домножений
            quantityMultiply = quantityMultiply * 2;
        }
        long deltaSteps = n - quantityMultiply;
        //System.out.println("notPowerOf2: quantityMultiply = " + quantityMultiply + ", deltaSteps = " + deltaSteps);
        result = result1;
        for (int index = 0; index < deltaSteps; index++) {
            result = result * a;
        }
        long totalCountSteps = logN + deltaSteps;
        //System.out.println("notPowerOf2: totalCountSteps = " + totalCountSteps + ", result = " + result);
        return result;
    }


}
