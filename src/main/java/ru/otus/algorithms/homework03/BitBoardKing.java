package ru.otus.algorithms.homework03;

import ru.otus.algorithms.tester.Task;

import java.math.BigInteger;

/*-
    Король решил прогуляться по пустынной шахматной доске.
    Сейчас он находится в указанной клетке.
    Куда он может сейчас походить?
    Вывести количество возможных ходов короля
    и ulong число с установленными битами тех полей, куда он может походить.


    Битовое смещение влево умножает число на 2.
    Битовое смещение вправо делит число на 2.
    Битовое смещение числа на одну позицию влево приводит к умножению этого числа на 2.

    Пример:
    long x = 10;
    long k = 1 << x; //  2 * 2 * 2 * 2 * 2 * 2 * 2 * 2 * 2 * 2 = 2^10 = 1024

    Картинка доски: src/main/resources/homework03/1.Bitboard - Король/1744.png
    1.Сначала записыввем битовую маску позиции короля.
        Например, если король находится на 10-й позиции(клетке), то только на его (10-й) позиции будет 1.
        k = 1 << 10
        00000000 00000000 00000100 00000000 = 1024 - битовая маска расположения короля на 10-й клетки.
        2^10 = 1024
        , т.e. нужно сместить на десять позиций влево.

        Т.е. для N-ой позиции нужно сместить на N позиций влево.
        k = 1 << N

    2. Необходимо установить биты соседних клеток: 1, 2, 9, 11, 8, 17, 18, 19
        Чтобы получить битовую маску 9-й клетки нужно сместить на 1 позицию вправо (от позиции короля).
        k = 1 >> 1

        00000000 00000000 00000010 00000000 = 512 - битовая маска расположения короля на 9-й клетки.
        2^9 = 512

        Чтобы получить битовую маску 17-й клетки нужно сместить на 7 позицию влево (от позиции короля)
        k = 1 << 7
        00000000 00000010 00000000 00000000 = 131072 - битовая маска расположения короля на 17-й клетки.
        2^17 = 131072

        Чтобы получить битовую маску 1-й клетки нужно сместить на 9 позицию вправо (от позиции короля)
        k = 1 >> 9
        00000000 00000000 00000000 00000010 = 2 - битовая маска расположения короля на 1-й клетки.
        2^1 = 2

        и т.д для всех соседних клеток.

        3. Складываем все полученые числа для получения числа с установленными битами тех полей, куда он может походить.
 */
public class BitBoardKing implements Task {
    // нули в первой колонке
    private final BigInteger firstColumnZeroMask = new BigInteger("FEFEFEFEFEFEFEFE", 16);
    // нули в последней колонке
    private final BigInteger lastColumnZeroMask = new BigInteger("7f7f7f7f7f7f7f7f", 16);
    // нули в последней строке
    private final BigInteger lastRowZeroMask = new BigInteger("00FFFFFFFFFFFFFF", 16);
    // нули в последней строке и последней колонке
    private final BigInteger lastRowAndLastColumnZeroMask = new BigInteger("007F7F7F7F7F7F7F", 16);
    // нули в последней строке и первой колонке
    private final BigInteger lastRowAndFirstColumnZeroMask = new BigInteger("00FEFEFEFEFEFEFE", 16);

    @Override
    public String run(String[] data) {
        int x = Integer.parseInt(data[0]);
        BigInteger king = BigInteger.ONE;
        king = king.shiftLeft(x);
        BigInteger[] resultArray = solution(king);
        String resultFirst = String.valueOf(resultArray[0]);
        String regultSecond = String.valueOf(resultArray[1]);
        return resultFirst + "\n" + regultSecond;
    }

    private BigInteger[] solution(BigInteger king) {
        // "kl" will be "k" or "0"
        // First column only "0" and rest are only "1".
        BigInteger kFirstColumnZero = firstColumnZeroMask.and(king);
        // "kr" will be "k" or "0"
        // Last column only "0" and rest are only "1".
        BigInteger kLastColumnZero = lastColumnZeroMask.and(king);
        BigInteger kLastRowZero = lastRowZeroMask.and(king);
        BigInteger kLastRowAndLastColumnZeroMask = lastRowAndLastColumnZeroMask.and(king);
        BigInteger kLastRowAndFirstColumnZeroMask = lastRowAndFirstColumnZeroMask.and(king);

        BigInteger kUpLeft = kLastRowAndFirstColumnZeroMask.shiftLeft(7);
        BigInteger kUp = kLastRowZero.shiftLeft(8);
        BigInteger kUpRight = kLastRowAndLastColumnZeroMask.shiftLeft(9);
        BigInteger kLeft = kFirstColumnZero.shiftRight(1);
        BigInteger kRight = kLastColumnZero.shiftLeft(1);
        BigInteger kDownLeft = kFirstColumnZero.shiftRight(9);
        BigInteger kDown = king.shiftRight(8);
        BigInteger kDownRight = kLastColumnZero.shiftRight(7);
        BigInteger mask =
                kUpLeft.add(kUp).add(kUpRight)
                        .add(kLeft).add(kRight)
                        .add(kDownLeft).add(kDown).add(kDownRight);
        BigInteger[] result = new BigInteger[2];
        result[1] = mask;
        /*-
         Чтобы узнать кол-во клеток куда может ходить король нужно посчитать количество еденичных
         битов вышенайденной маски.
         Чтобы посчитать кол-во едениц в битовой маске нужно отнять еденицу(в двоичном формате) от маски
         и затем сделать логическое умножение результата с исходной маской.
         В результате "пропадает" последняя еденица.
         Выполняем в цикле пока не достигнем 0.
        */
        int count = 0;
        while (mask.compareTo(BigInteger.ZERO) > 0) {
            count++;
            mask = mask.and(mask.subtract(BigInteger.ONE)); // mask = mask && (mask -1)
        }
        result[0] = new BigInteger(String.valueOf(count));
        return result;
    }

}
