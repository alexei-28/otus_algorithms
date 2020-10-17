package ru.otus.algorithms.homework03;

import ru.otus.algorithms.tester.Task;

import java.math.BigInteger;
import java.util.*;

import static ru.otus.algorithms.homework03.BitBoardTruckers.CheckWhitePiece.*;
import static ru.otus.algorithms.homework03.BitBoardTruckers.CheckWhitePiece.WHITE_ROOKS;

/*-
    Дана шахматная позиция в FEN формате.
    Известно, что в этой позиции есть:
    + одна белая ладья,
    + один белый слон,
    + один белый ферзь,
    а также возможно наличие других белых и чёрных фигур.

    Определить битовую маску возможных ходов белых фигур - ладьи, слона и ферзя.

    FEN chess help: https://lichess.org/analysis
 */
public class BitBoardTruckers implements Task {
    enum CheckWhitePiece {
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

        CheckWhitePiece(String abbr) {
            this.abbr = abbr;
        }

        public static BitBoardTruckers.CheckWhitePiece findByAbbr(String findAbbr) {
            for (BitBoardTruckers.CheckWhitePiece piece : values()) {
                if (piece.abbr.equals(findAbbr)) {
                    return piece;
                }
            }
            return null;
        }
    }

    private final BigInteger lastMask = BigInteger.ONE.shiftLeft(63); // 63
    private final BigInteger firstMask = BigInteger.ONE; // 0
    // нули по периметру
    private final List<BigInteger> topLimitPositionsList = Arrays.asList(
            lastMask, lastMask.shiftRight(1), lastMask.shiftRight(2), lastMask.shiftRight(3),
            lastMask.shiftRight(4), lastMask.shiftRight(5), lastMask.shiftRight(6), lastMask.shiftRight(7));
    private final List<BigInteger> bottomLimitPositionsList = Arrays.asList(
            firstMask, firstMask.shiftLeft(1), firstMask.shiftLeft(2), firstMask.shiftLeft(3),
            firstMask.shiftLeft(4), firstMask.shiftLeft(5), firstMask.shiftLeft(6), firstMask.shiftLeft(7));
    private final List<BigInteger> leftLimitPositionsList = Arrays.asList(
            firstMask, firstMask.shiftLeft(8), firstMask.shiftLeft(16), firstMask.shiftLeft(24),
            firstMask.shiftLeft(32), firstMask.shiftLeft(40), firstMask.shiftLeft(48), firstMask.shiftLeft(56));
    private final List<BigInteger> rightLimitPositionsList = Arrays.asList(
            lastMask.shiftRight(56), lastMask.shiftRight(48), lastMask.shiftRight(40), lastMask.shiftRight(32),
            lastMask.shiftRight(24), lastMask.shiftRight(16), lastMask.shiftRight(8), lastMask);

    private final List<BigInteger> allBlackPiecesList = new ArrayList<>();
    private final List<BigInteger> allWhiteExcludeWhiteRookList = new ArrayList<>();
    private final List<BigInteger> allWhiteExcludeWhiteBishopList = new ArrayList<>();
    private final List<BigInteger> allWhiteExcludeWhiteQueenList = new ArrayList<>();

    @Override
    public String run(String[] data) {
        String fen = data[0];
        BigInteger[] resultArray = solution(fen);
        String whiteRookMasks = String.valueOf(resultArray[0]);
        String whiteBishopMasks = String.valueOf(resultArray[1]);
        String whiteQueenMasks = String.valueOf(resultArray[2]);
        return whiteRookMasks + "\n" + whiteBishopMasks + "\n" + whiteQueenMasks;
    }

    private BigInteger[] solution(String fen) {
        BigInteger[] resultArray = new BigInteger[]{BigInteger.ZERO, BigInteger.ZERO, BigInteger.ZERO};
        allBlackPiecesList.clear();
        allWhiteExcludeWhiteRookList.clear();
        allWhiteExcludeWhiteBishopList.clear();
        allWhiteExcludeWhiteQueenList.clear();
        int whiteRookPositionInBitBoard = -1;
        int whiteBishopPositionInBitBoard = -1;
        int whiteRookPositionInQueen = -1;

        String[] fenTokens = fen.split(BitBoaardUtil.FEN_LINE_SEPARATOR);
        // Fill all white and black pieces in the bit board.
        for (int i = 0; i < fenTokens.length; i++) {
            // e.g.: "7k/8/8/8/8/8/8/K7" -> "7k" in last line(8), "K7" in first line(1)
            String currentToken = fenTokens[i];

            // e.g. [null, null, null, null, null, null, null, k]
            String[] piecesInArray = BitBoaardUtil.createPiecesInArray(currentToken); // "piecesInArray" array's length always = 8
            for (int index = 0; index < piecesInArray.length; index++) {
                String currentPieceAbbr = piecesInArray[index];
                if (currentPieceAbbr != null) { // e.g. "k"
                    int piecePositionInBitBoard = piecesInArray.length * (fenTokens.length - i) - (piecesInArray.length - index);
                    CheckWhitePiece currentPiece = CheckWhitePiece.findByAbbr(currentPieceAbbr);
                    if (currentPiece != null) {
                        if (currentPiece == BLACK_PAWNS || currentPiece == BLACK_KNIGHTS
                                || currentPiece == BLACK_BISHOPS || currentPiece == BLACK_ROOKS
                                || currentPiece == BLACK_QUEENS || currentPiece == BLACK_KING) {
                            allBlackPiecesList.add(BigInteger.ONE.shiftLeft(piecePositionInBitBoard));
                        } else if (currentPiece == WHITE_PAWNS || currentPiece == WHITE_KNIGHTS
                                || currentPiece == WHITE_BISHOPS || currentPiece == WHITE_ROOKS
                                || currentPiece == WHITE_QUEENS || currentPiece == WHITE_KING) {
                            if (currentPiece == WHITE_ROOKS) {
                                whiteRookPositionInBitBoard = piecePositionInBitBoard;
                            } else {
                                // Все белые фигуры кроме белой ладьи.
                                allWhiteExcludeWhiteRookList.add(BigInteger.ONE.shiftLeft(piecePositionInBitBoard));
                            }
                            if (currentPiece == WHITE_BISHOPS) {
                                whiteBishopPositionInBitBoard = piecePositionInBitBoard;
                            } else {
                                allWhiteExcludeWhiteBishopList.add(BigInteger.ONE.shiftLeft(piecePositionInBitBoard));
                            }
                            if (currentPiece == WHITE_QUEENS) {
                                whiteRookPositionInQueen = piecePositionInBitBoard;
                            } else {
                                allWhiteExcludeWhiteQueenList.add(BigInteger.ONE.shiftLeft(piecePositionInBitBoard));
                            }
                        }
                    }
                }
            }
        } // end iterate tokens

        // Calculate masks only for three white pieces (Rook, Bishop, Queen).
        if (whiteRookPositionInBitBoard >= 0) {
            BigInteger whiteRookMask = getWhiteRookMask(whiteRookPositionInBitBoard);
            resultArray[0] = whiteRookMask;
        }
        if (whiteBishopPositionInBitBoard >= 0) {
            BigInteger whiteBishopMask = getWhiteBishopMask(whiteBishopPositionInBitBoard);
            resultArray[1] = whiteBishopMask;
        }
        if (whiteRookPositionInQueen >= 0) {
            BigInteger whiteQueenMask = getWhiteQueenMask(whiteRookPositionInQueen);
            resultArray[2] = whiteQueenMask;
        }
        return resultArray;
    }

    private BigInteger getWhiteRookMask(int initPosition) {
        BigInteger whiteRookMasks = BigInteger.ZERO;
        BigInteger initMask = BigInteger.ONE.shiftLeft(initPosition);
        // Все допустимые позиции белой ладьи влево и вправо
        BigInteger currentMask = initMask;
        while (!leftLimitPositionsList.contains(currentMask)
                && !allBlackPiecesList.contains(currentMask)) {
            currentMask = currentMask.shiftRight(1); // left side from white rook
            if (allWhiteExcludeWhiteRookList.contains(currentMask)) {
                break;
            } else {
                whiteRookMasks = whiteRookMasks.add(currentMask);
            }
        }
        currentMask = initMask;
        while (!rightLimitPositionsList.contains(currentMask)
                && !allBlackPiecesList.contains(currentMask)) {
            currentMask = currentMask.shiftLeft(1); // right side from white rook
            if (allWhiteExcludeWhiteRookList.contains(currentMask)) {
                break;
            } else {
                whiteRookMasks = whiteRookMasks.add(currentMask);
            }
        }

        // Все допустимые позиции белой ладьи вниз и вверх
        currentMask = initMask;
        while (!bottomLimitPositionsList.contains(currentMask)
                && !allBlackPiecesList.contains(currentMask)) {
            currentMask = currentMask.shiftRight(8); // under side from white rook
            if (allWhiteExcludeWhiteRookList.contains(currentMask)) {
                break;
            } else {
                whiteRookMasks = whiteRookMasks.add(currentMask);
            }
        }
        currentMask = initMask;
        while (!topLimitPositionsList.contains(currentMask)
                && !allBlackPiecesList.contains(currentMask)) {
            currentMask = currentMask.shiftLeft(8); // above side from white rook
            if (allWhiteExcludeWhiteRookList.contains(currentMask)) {
                break;
            } else {
                whiteRookMasks = whiteRookMasks.add(currentMask);
            }
        }
        return whiteRookMasks;
    }

    private BigInteger getWhiteBishopMask(int initPosition) {
        BigInteger whiteBishopMasks = BigInteger.ZERO;
        // Все допустимые позиции белого слона вверх.
        BigInteger initMask = BigInteger.ONE.shiftLeft(initPosition);
        BigInteger currentMask = initMask;
        while (!leftLimitPositionsList.contains(currentMask)
                && !topLimitPositionsList.contains(currentMask)
                && !allBlackPiecesList.contains(currentMask)) {
            currentMask = currentMask.shiftLeft(7); // up and left side from white bishop
            if (allWhiteExcludeWhiteBishopList.contains(currentMask)) {
                break;
            } else {
                whiteBishopMasks = whiteBishopMasks.add(currentMask);
            }
        }
        currentMask = initMask;
        while (!rightLimitPositionsList.contains(currentMask)
                && !topLimitPositionsList.contains(currentMask)
                && !allBlackPiecesList.contains(currentMask)) {
            currentMask = currentMask.shiftLeft(9); // up and right side from white bishop
            if (allWhiteExcludeWhiteBishopList.contains(currentMask)) {
                break;
            } else {
                whiteBishopMasks = whiteBishopMasks.add(currentMask);
            }
        }

        // Все допустимые позиции белого слона вниз
        currentMask = initMask;
        while (!bottomLimitPositionsList.contains(currentMask)
                && !leftLimitPositionsList.contains(currentMask)
                && !allBlackPiecesList.contains(currentMask)) {
            currentMask = currentMask.shiftRight(9); // bottom and left side from white bishop
            if (allWhiteExcludeWhiteBishopList.contains(currentMask)) {
                break;
            } else {
                whiteBishopMasks = whiteBishopMasks.add(currentMask);
            }
        }
        currentMask = initMask;
        while (!bottomLimitPositionsList.contains(currentMask)
                && !rightLimitPositionsList.contains(currentMask)
                && !allBlackPiecesList.contains(currentMask)) {
            currentMask = currentMask.shiftRight(7); // up and right side from white bishop
            if (allWhiteExcludeWhiteBishopList.contains(currentMask)) {
                break;
            } else {
                whiteBishopMasks = whiteBishopMasks.add(currentMask);
            }
        }
        return whiteBishopMasks;
    }

    private BigInteger getWhiteQueenMask(int initPosition) {
        BigInteger whiteQueenMasks = BigInteger.ZERO;
        // Все допустимые позиции белого ферзя.
        BigInteger initMask = BigInteger.ONE.shiftLeft(initPosition);
        BigInteger currentMask = initMask;
        while (!topLimitPositionsList.contains(currentMask)
                && !allBlackPiecesList.contains(currentMask)) {
            currentMask = currentMask.shiftLeft(8); // up from white bishop
            if (allWhiteExcludeWhiteQueenList.contains(currentMask)) {
                break;
            } else {
                whiteQueenMasks = whiteQueenMasks.add(currentMask);
            }
        }
        currentMask = initMask;
        while (!leftLimitPositionsList.contains(currentMask) && !allBlackPiecesList.contains(currentMask)) {
            currentMask = currentMask.shiftRight(1); // left side from white bishop
            if (allWhiteExcludeWhiteQueenList.contains(currentMask)) {
                break;
            } else {
                whiteQueenMasks = whiteQueenMasks.add(currentMask);
            }
        }
        currentMask = initMask;
        while (!leftLimitPositionsList.contains(currentMask) && !topLimitPositionsList.contains(currentMask)
                && !allBlackPiecesList.contains(currentMask) && !allWhiteExcludeWhiteQueenList.contains(currentMask)) {
            currentMask = currentMask.shiftLeft(7); // up and left side from white bishop
            if (allWhiteExcludeWhiteQueenList.contains(currentMask)) {
                break;
            } else {
                whiteQueenMasks = whiteQueenMasks.add(currentMask);
            }
        }
        currentMask = initMask;
        while (!rightLimitPositionsList.contains(currentMask)
                && !allBlackPiecesList.contains(currentMask) && !allWhiteExcludeWhiteQueenList.contains(currentMask)) {
            currentMask = currentMask.shiftLeft(1); // right side from white bishop
            if (allWhiteExcludeWhiteQueenList.contains(currentMask)) {
                break;
            } else {
                whiteQueenMasks = whiteQueenMasks.add(currentMask);
            }
        }
        currentMask = initMask;
        while (!rightLimitPositionsList.contains(currentMask) && !topLimitPositionsList.contains(currentMask)
                && !allBlackPiecesList.contains(currentMask) && !allWhiteExcludeWhiteQueenList.contains(currentMask)) {
            currentMask = currentMask.shiftLeft(9); // up and right side from white bishop
            if (allWhiteExcludeWhiteQueenList.contains(currentMask)) {
                break;
            } else {
                whiteQueenMasks = whiteQueenMasks.add(currentMask);
            }
        }
        currentMask = initMask;
        while (!bottomLimitPositionsList.contains(currentMask)
                && !allBlackPiecesList.contains(currentMask) && !allWhiteExcludeWhiteQueenList.contains(currentMask)) {
            currentMask = currentMask.shiftRight(8); // bottom side from white bishop
            if (allWhiteExcludeWhiteQueenList.contains(currentMask)) {
                break;
            } else {
                whiteQueenMasks = whiteQueenMasks.add(currentMask);
            }
        }
        currentMask = initMask;
        while (!bottomLimitPositionsList.contains(currentMask) && !leftLimitPositionsList.contains(currentMask)
                && !allBlackPiecesList.contains(currentMask) && !allWhiteExcludeWhiteQueenList.contains(currentMask)) {
            currentMask = currentMask.shiftRight(9); // bottom and left side from white bishop
            if (allWhiteExcludeWhiteQueenList.contains(currentMask)) {
                break;
            } else {
                whiteQueenMasks = whiteQueenMasks.add(currentMask);
            }
        }
        currentMask = initMask;
        while (!bottomLimitPositionsList.contains(currentMask) && !rightLimitPositionsList.contains(currentMask)
                && !allBlackPiecesList.contains(currentMask) && !allWhiteExcludeWhiteQueenList.contains(currentMask)) {
            currentMask = currentMask.shiftRight(7); // bottom and right side from white bishop
            if (allWhiteExcludeWhiteQueenList.contains(currentMask)) {
                break;
            } else {
                whiteQueenMasks = whiteQueenMasks.add(currentMask);
            }
        }
        return whiteQueenMasks;
    }
}
