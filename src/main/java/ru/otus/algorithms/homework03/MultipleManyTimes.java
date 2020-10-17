package ru.otus.algorithms.homework03;

import ru.otus.algorithms.util.DateUtil;

/*-
   Проверка скорости выполнения различных методов умноженя на 2:
   - непрсредственно умножение на 2
   - сложения с самим собой
   - сдвиг влево на 1 позицию

   Для проверки умножаем на 2 число 123456789
   Результаты тестов злесь: src/main/resources/homework03/comparison_table.txt
 */
public class MultipleManyTimes {


    public MultipleManyTimes() {
        long currentDate = System.currentTimeMillis();
        long countOfRepeat = 100000000000L;
        int result = 0;
        for (long index = 0; index < countOfRepeat; index++) {
            result = doubleByShift(123456789);
        }
        long duration = DateUtil.getDurationInMills(currentDate, System.currentTimeMillis());
        System.out.println("result = " + result + ", duration(ms) = " + duration);
    }

    private static int doubleByMultiply(int number) {
        int result = number * 2;
        return result;
    }

    private static int doubleByPlus(int numbrer) {
        int result = numbrer + numbrer;
        return result;
    }

    private static int doubleByShift(int number) {
        int result = number << 1;
        return result;
    }
}
