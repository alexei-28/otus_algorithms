package ru.otus.algorithms.homework03;

import ru.otus.algorithms.util.NumberUtil;

public class BitBoaardUtil {
    public static final String FEN_LINE_SEPARATOR = "/";

    // e.g.:
    // "7k" -> [null, null, null, null, null, null, null, k]
    // "ppp4p" -> [p, p, p, null, null, null, null, p]
    // "QQQQQQQQ" -> [Q, Q, Q, Q, Q, Q, Q, Q]
    public static String[] createPiecesInArray(String token) {
        String[] piecesPositionArray = new String[8];
        char[] arrayPieces = token.toCharArray();
        int currentPosition = 0;
        for (char arrayPiece : arrayPieces) {
            String currentPeace = String.valueOf(arrayPiece);
            boolean isNumber = NumberUtil.isNumeric(currentPeace);
            if (isNumber) {
                currentPosition = currentPosition + Integer.parseInt(currentPeace);
            } else {
                piecesPositionArray[currentPosition] = currentPeace;
                currentPosition++;
            }
        }
        return piecesPositionArray;
    }

}
