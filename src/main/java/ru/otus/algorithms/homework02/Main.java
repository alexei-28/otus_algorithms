package ru.otus.algorithms.homework02;

import ru.otus.algorithms.tester.Tester;

import java.io.File;
import java.security.InvalidParameterException;

import static ru.otus.algorithms.util.FileUtil.TEST_FOLDER_BASE_PATH;

/*-
   Entry point for homework#02
*/
public class Main {

    public static void main(String[] args) {
        try {
            System.out.println("Start homework02");
            String pathBase = TEST_FOLDER_BASE_PATH + File.separator + "homework02";

            // Вывести наибольший общий делитель
            // 1a. Алгоритм Евклида через вычитание
            Tester tester = new Tester(new File(pathBase + File.separator + "2.GCD"), new GCD_1a());

            // Алгоритм Евклида через остаток
            //Tester tester = new Tester(new File(pathBase + File.separator + "2.GCD"), new GCD_1b());

            // Необходимо найти a^n
            // 2a. Итеративный (n умножений)
            //Tester tester = new Tester(new File(pathBase + File.separator + "3.Power"), new PowerN_2a());

            //2b. Через степень двойки с домножением
            //Tester tester = new Tester(new File(pathBase + File.separator + "3.Power"), new PowerN_2b());

            // Алгоритм поиска чисел Фибоначчи
            // 4a. Через рекурсию
            //Tester tester = new Tester(new File(pathBase + File.separator + "4.Fibo"), new Fibonacci_4a());

            // 4b. Через итерацию
            //Tester tester = new Tester(new File(pathBase + File.separator + "4.Fibo"), new Fibonacci_4b());

            // 4c. По формуле золотого сечения
            //Tester tester = new Tester(new File(pathBase + File.separator + "4.Fibo"), new Fibonacci_4c());

            // 4d. Через умножение матриц
            //Tester tester = new Tester(new File(pathBase + File.separator + "4.Fibo"), new Fibonacci_4d());
        } catch (InvalidParameterException ex) {
            System.err.println("Error: " + ex.getMessage());
        }
    }
}
