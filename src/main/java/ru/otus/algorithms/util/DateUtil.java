package ru.otus.algorithms.util;

public class DateUtil {

    public static long getDurationInMills(long millisecondsStart, long millisecondsEnd) {
        long duration = millisecondsEnd - millisecondsStart;
       return duration;
    }
}
