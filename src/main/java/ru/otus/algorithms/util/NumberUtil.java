package ru.otus.algorithms.util;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;

public class NumberUtil {

    public static double round(double valueToRound, int numberOfDecimalPlaces) {
        double multiplicationFactor = Math.pow(10, numberOfDecimalPlaces);
        double interestedInZeroDPs = valueToRound * multiplicationFactor;
        return Math.round(interestedInZeroDPs) / multiplicationFactor;
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static byte[] intArray2ByteArray(int[] data) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(data.length * 4);
        IntBuffer intBuffer = byteBuffer.asIntBuffer();
        intBuffer.put(data);
        return byteBuffer.array();
    }

    public static int[] byteArray2IntArray(byte[] buf) {
        int[] intArr = new int[buf.length / 4];
        int offset = 0;
        for (int i = 0; i < intArr.length; i++) {
            intArr[i] = (buf[3 + offset] & 0xFF) | ((buf[2 + offset] & 0xFF) << 8) |
                    ((buf[1 + offset] & 0xFF) << 16) | ((buf[offset] & 0xFF) << 24);
            offset += 4;
        }
        return intArr;
    }

    public static int[] byteArray2IntArray(byte[] buf, int from, int to) {
        int[] srcArr = new int[buf.length / 4];
        int offset = 0;
        for (int i = 0; i < srcArr.length; i++) {
            srcArr[i] = (buf[3 + offset] & 0xFF) | ((buf[2 + offset] & 0xFF) << 8) |
                    ((buf[1 + offset] & 0xFF) << 16) | ((buf[offset] & 0xFF) << 24);
            offset += 4;
        }
        int[] destArray = new int[to - from];
        if (destArray.length >= 0)
            System.arraycopy(srcArr, from, destArray, 0, destArray.length);
        return destArray;
    }

    public static int getRandomNumber(int minNumber, int maxNumber) {
        return (int) ((Math.random() * (maxNumber - minNumber)) + minNumber);
    }
}
