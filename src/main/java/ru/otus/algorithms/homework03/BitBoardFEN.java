package ru.otus.algorithms.homework03;

import ru.otus.algorithms.tester.Task;

import java.math.BigInteger;

/*-
    Дано расположение шахматных фигур на доске в FEN-нотации (первая часть fen-кода).
    Например, начальная позиция записывается так:
    rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR
    Подробнее о формате нотации см.
    https://ru.wikipedia.org/wiki/Нотация_Форсайта_—_Эдвардса

    Перевести её в Bitboard Board-Definition формат.

    Программа Bitboards' Little Helper:
    http://www.talkchess.com/forum3/viewtopic.php?t=39053

    FEN chess help: https://lichess.org/analysis

    Цифра задаёт количество пустых полей на горизонтали,
    счёт ведётся либо от левого края доски, либо после фигуры (8 означает пустую горизонталь).
 */
public class BitBoardFEN implements Task {

    private enum Piece {
        WHITE_PAWNS("P"),
        WHITE_KNIGHTS("N"),
        WHITE_BISHOPS("B"),
        WHITE_ROOKS("R"),
        WHITE_QUEENS("Q"), //
        WHITE_KING("K"),

        BLACK_PAWNS("p"), //
        BLACK_KNIGHTS("n"),
        BLACK_BISHOPS("b"),
        BLACK_ROOKS("r"),
        BLACK_QUEENS("q"),
        BLACK_KING("k");

        private final String abbr;

        Piece(String abbr) {
            this.abbr = abbr;
        }

        // Если значение не нашлось, лучше выбрасывать исключение, чем потом возиться с NPE
        public static Piece findByAbbr(String findAbbr) {
            for (Piece piece : values()) {
                if (piece.abbr.equals(findAbbr)) {
                    return piece;
                }
            }
            return null;
        }
    }

    @Override
    public String run(String[] data) {
        String fen = data[0];
        return solution(fen);
    }

    private String solution(String fen) {
        // Для хранения позиции использовать массив board из двенадцати 64-битных беззнаковых целых чисел.
        BigInteger[] board = new BigInteger[12];

        String[] fenTokens = fen.split(BitBoaardUtil.FEN_LINE_SEPARATOR);
        for (int i = 0; i < fenTokens.length; i++) {
            // e.g.: "7k/8/8/8/8/8/8/K7" -> "7k" in last line(8), "K7" in first line(1)
            String currentToken = fenTokens[i];

            // e.g. [null, null, null, null, null, null, null, k]
            String[] piecesInArray = BitBoaardUtil.createPiecesInArray(currentToken); // "piecesInArray" array's length always = 8
            for (int index = 0; index < piecesInArray.length; index++) {
                String currentPieceAbbr = piecesInArray[index];
                if (currentPieceAbbr != null) { // e.g. "k"
                    int piecePositionInBitBoard = piecesInArray.length * (fenTokens.length - i) - (piecesInArray.length - index);
                    BigInteger pieceBitBoardValue = BigInteger.ONE.shiftLeft(piecePositionInBitBoard);
                    Piece currentPiece = Piece.findByAbbr(currentPieceAbbr);
                    BigInteger currentValue = board[currentPiece.ordinal()];
                    if (currentValue != null) {
                        currentValue = currentValue.add(pieceBitBoardValue);
                    } else {
                        currentValue = pieceBitBoardValue;
                    }
                    board[currentPiece.ordinal()] = currentValue;
                }
            }
        }
        StringBuilder result = new StringBuilder();
        for (BigInteger bigInteger : board) {
            if (bigInteger == null) {
                result.append("0\n");
            } else {
                result.append(bigInteger).append("\n");
            }
        }
        return result.toString();
    }

}
