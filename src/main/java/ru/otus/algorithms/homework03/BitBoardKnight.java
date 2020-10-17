package ru.otus.algorithms.homework03;

import ru.otus.algorithms.tester.Task;

import java.math.BigInteger;

/*-
    Шахматный конь решил пробежаться по шахматной доске.
    Сейчас он находится в указанной клетке.
    Куда он может сейчас походить?
    Вывести количество возможных ходов коня
    и ulong число с установленными битами тех полей, куда он может походить.

    Картинка доски: src/main/resources/homework03/2.Bitboard - Конь/1745.png

 */
public class BitBoardKnight implements Task {
    // нули в первой колонке
    private final BigInteger firstColumnZeroMask = new BigInteger("fefefefefefefefe", 16);
    // нули в первой и второй колонке
    private final BigInteger firstSecondColumnZeroMask = new BigInteger("FCFCFCFCFCFCFCFC", 16);
    // нули в первой, второй колонке и последней строке
    private final BigInteger firstSecondColumnAndLastRowZeroMask = new BigInteger("00FCFCFCFCFCFCFC", 16);
    // нули в последней колонке
    private final BigInteger lastColumnZeroMask = new BigInteger("7f7f7f7f7f7f7f7f", 16);
    // нули в последней и предпоследней колонке
    private final BigInteger prevLastColumnZeroMask = new BigInteger("3F3F3F3F3F3F3F3F", 16);
    // нули в последней, предпоследней колонке и последней строке
    private final BigInteger prevLastColumnAndLastRowZeroMask = new BigInteger("003F3F3F3F3F3F3F", 16);
    // нули в последней, предпоследней колонке и последней колонке
    private final BigInteger prevLastRowAndLastColumnZeroMask = new BigInteger("00007F7F7F7F7F7F", 16);
    // нули в последней, предпоследней колонке и первой колонке
    private final BigInteger prevLastRowAndFirstColumnZeroMask = new BigInteger("0000FEFEFEFEFEFE", 16);

    @Override
    public String run(String[] data) {
        int x = Integer.parseInt(data[0]);
        BigInteger knight = BigInteger.ONE;
        knight = knight.shiftLeft(x);
        BigInteger[] resultArray = solution(knight);
        String resultFirst = String.valueOf(resultArray[0]);
        String regultSecond = String.valueOf(resultArray[1]);
        return resultFirst + "\n" + regultSecond;
    }

    private BigInteger[] solution(BigInteger knight) {
        BigInteger kFirstColumn = firstColumnZeroMask.and(knight);
        BigInteger kFirstSecondColumn = firstSecondColumnZeroMask.and(knight);
        BigInteger kFirstSecondColumnAndLastRowZero = firstSecondColumnAndLastRowZeroMask.and(knight);
        BigInteger kLastColumn = lastColumnZeroMask.and(knight);
        BigInteger kPrevLastColumn = prevLastColumnZeroMask.and(knight);
        BigInteger kPrevLastColumnAndLastRowZero = prevLastColumnAndLastRowZeroMask.and(knight);
        BigInteger kPrevLastRowAndLastColumnZero = prevLastRowAndLastColumnZeroMask.and(knight);
        BigInteger kPrevLastRowAndFirstColumnZero = prevLastRowAndFirstColumnZeroMask.and(knight);

        BigInteger kUpLeft1 = kPrevLastRowAndFirstColumnZero.shiftLeft(15);
        BigInteger kUpLeft2 = kFirstSecondColumnAndLastRowZero.shiftLeft(6);
        BigInteger kUpRight1 = kPrevLastRowAndLastColumnZero.shiftLeft(17);
        BigInteger kUpRight2 = kPrevLastColumnAndLastRowZero.shiftLeft(10);

        BigInteger kDownLeft = kFirstSecondColumn.shiftRight(10);
        BigInteger kDownLeft2 = kFirstColumn.shiftRight(17);
        BigInteger kDownRight = kPrevLastColumn.shiftRight(6);
        BigInteger kDownRight2 = kLastColumn.shiftRight(15);
        BigInteger mask =
                kUpLeft1.add(kUpLeft2).add(kUpRight1)
                        .add(kUpRight2).add(kDownLeft)
                        .add(kDownLeft2).add(kDownRight).add(kDownRight2);
        BigInteger[] result = new BigInteger[2];
        result[1] = mask;
        int count = 0;
        while (mask.compareTo(BigInteger.ZERO) > 0) {
            count++;
            mask = mask.and(mask.subtract(BigInteger.ONE));
        }
        result[0] = new BigInteger(String.valueOf(count));
        return result;
    }

}
