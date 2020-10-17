package ru.otus.algorithms.homework01;

/*-
    Посмотреть видеоурок «Квадрат Гарри Поттера» и
    разгадать дюжину заклинаний. https://www.youtube.com/watch?v=KmV3aBHfo5w
    Заклинания выписать в отдельном файле и пронумеровать.
    Файл с заклинаниями: src/main/resources/homework01/3.HarryPotter/incantations.properties

    Время выполнения - 4 часа
    Оценка: +3 байта
 */
public class HarryPotterIncantations {

    public HarryPotterIncantations() {
        for (int y = 0; y < 25; y++) {
            for (int x = 0; x < 25; x++) {
                String stringToPrint = y < x ? "# " : ". ";
                System.out.print(stringToPrint);
            }
            System.out.println();
        }
    }
}
