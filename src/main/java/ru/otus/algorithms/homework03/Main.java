package ru.otus.algorithms.homework03;

import ru.otus.algorithms.tester.Tester;

import java.io.File;

import static ru.otus.algorithms.util.FileUtil.TEST_FOLDER_BASE_PATH;

/*-
   Entry point for homework#03
*/
public class Main {

    public static void main(String[] args) {
        // Test multiple to 2
        //new MultipleManyTimes();

        System.out.println("Start homework03");
        String pathBase =  TEST_FOLDER_BASE_PATH + File.separator + "homework03";

        //Король решил прогуляться по пустынной шахматной доске.
        //Tester tester = new Tester(new File(pathBase + File.separator + "1.Bitboard - Король"), new BitBoardKing());

        //Шахматный конь решил пробежаться по шахматной доске.
        //Tester tester = new Tester(new File(pathBase + File.separator + "2.Bitboard - Конь"), new BitBoardKnight());

        //Перевести её в Bitboard Board-Definition формат.
        //Tester tester = new Tester(new File(pathBase + File.separator + "3.Bitboard - FEN"), new BitBoardFEN());

        //Перевести её в Bitboard Board-Definition формат.
        //Tester tester = new Tester(new File(pathBase + File.separator + "4.Bitboard - Дальнобойщики"), new BitBoardTruckers());
    }

}
