package ru.otus.algorithms.util;

public class MathUtil {

    /*--
                log[a]x
     log[b]x = ---------
                log[a]b

     As result log2 is:

                log[10]x
     log[2]x = ----------
                log[10]2
    */

    public static double log2(long n) {
        return (Math.log(n) / Math.log(2));
    }

    // Является ли число степенью двойки
    public static boolean isPowerOfTwo(long number) {
        return number > 0 && ((number & (number - 1)) == 0);
    }
}
