package ru.otus.algorithms.homework02;

import ru.otus.algorithms.tester.Task;
import ru.otus.algorithms.util.MathUtil;

import java.math.BigInteger;

/*--
    На первой строчке записано целое число N >= 0.
    Найти и вывести на экран точное значение N-ого числа Фибоначчи.

    F(n) = F(n-1) + F(n-2)
    F(1) = F(2) = 1

    Пример:
    N = 0 -> F = 0
    N = 2 -> F = 1
    N = 5 -> F = 5
    N = 8 -> F = 21
    N = 9 -> F = 34
    N = 10 -> F = 55
    N = 16 -> F = 987
    N = 21 -> F = 10946
    N = 32 -> F = 2178309

    Формула:
    | 1 1 |^n    | Fn+1 Fn  |  * |F2|
    | 1 0 |   =  | Fn   Fn-1|    |F1|

    или

    | 1 1 |^(n-1)    | Fn   Fn-1|
    | 1 0 |       =  | Fn-1 Fn-2|

    т.е
    Matrix[0][1] = Matrix[1][0]

    Решить задачу разными способами.
    4d. Через умножение матриц
    Сложность = log N
 */
public class Fibonacci_4d implements Task {

    @Override
    public String run(String[] data) {
        long n = Long.parseLong(data[0]);
        BigInteger result = getFibonacci(n);
        return String.valueOf(result);
    }

    private BigInteger getFibonacci(long n) {
        if (n == 0) {
            return BigInteger.ZERO;
        }
        n--; // Fn-1
        BigInteger[][] resultMatrix;
        Matrix2D matrix2D = new Matrix2D();
        if (MathUtil.isPowerOfTwo(n)) {
            resultMatrix = matrix2D.multiplyPowerTwo(n);
        } else {
            resultMatrix = matrix2D.multiplyPowerNoTwo(n);
        }
        // Get Fibonacci value from Matrix[0][0] because use Fn-1
        BigInteger fibonacciN = resultMatrix[0][0];
        return fibonacciN;
    }

    private class Matrix2D {

        public BigInteger[][] multiplyPowerNoTwo(long n) {
            // В матрице значение M[0][1] = M[1][0]
            BigInteger[][] res = multiplyPowerTwo(n);
            // Вычисляем количество домножений на базовую матрицу (без использования встроенной фуцкции power)
            long logN = (long) MathUtil.log2(n);
            long power2 = 1;
            for (int index = 0; index < logN; index++) {
                power2 = power2 * 2;
            }
            long quantityMultiply = n - power2;

            for (int index = 0; index < quantityMultiply; index++) {
                res = multiply2Base(res);
            }
            return res;
        }

        private BigInteger[][] multiply2Base(BigInteger[][] matrix) {
            BigInteger[][] res = new BigInteger[2][2];
            int[][] MATRIX_BASE = {{1, 1}, {1, 0}};

            // row 1
            res[0][0] = matrix[0][0].multiply(BigInteger.valueOf(MATRIX_BASE[0][0])).add(matrix[1][0].multiply(BigInteger.valueOf(MATRIX_BASE[0][1])));
            res[0][1] = matrix[0][0].multiply(BigInteger.valueOf(MATRIX_BASE[1][0])).add(matrix[0][1].multiply(BigInteger.valueOf(MATRIX_BASE[1][1])));

            // row 2
            res[1][0] = res[0][1]; //  Matrix[0][1] = Matrix[1][0]
            res[1][1] = matrix[0][1].multiply(BigInteger.valueOf(MATRIX_BASE[1][0])).add(matrix[1][1].multiply(BigInteger.valueOf(MATRIX_BASE[1][1])));

            return res;
        }

        public BigInteger[][] multiplyPowerTwo(long n) {
            // В матрице значение M[0][1] = M[1][0]
            BigInteger[][] MATRIX_BASE = {{BigInteger.ONE, BigInteger.ONE}, {BigInteger.ONE, BigInteger.ZERO}};
            BigInteger[][] res = MATRIX_BASE;

            int logN = (int) MathUtil.log2(n);
            for (int index = 0; index < logN; index++) {
                res = multiplyYourSelf(res);
            }
            return res;
        }

        private BigInteger[][] multiplyYourSelf(BigInteger[][] matrix) {
            BigInteger[][] res = new BigInteger[2][2];

            // row 1
            res[0][0] = matrix[0][0].multiply(matrix[0][0]).add(matrix[1][0].multiply(matrix[0][1]));
            res[0][1] = matrix[0][0].multiply(matrix[0][1]).add(matrix[0][1].multiply(matrix[1][1]));

            // row 2
            res[1][0] = res[0][1];  //  Matrix[0][1] = Matrix[1][0]
            res[1][1] = matrix[0][1].multiply(matrix[1][0]).add(matrix[1][1].multiply(matrix[1][1]));

            return res;
        }
    }
}
