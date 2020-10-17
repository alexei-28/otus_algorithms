ДЗ#1

Репозиторий:
https://github.com/alexei-28/Algorithms
Язык программирования: Java 1.8

- Скачайте проект и импортните в IntellIJ IDEA - https://www.jetbrains.com/ru-ru/idea/
- Точка входа приложения для ДЗ#1 - Main

Сделал 2 задачи из домашнего задания:

1.Создать систему тестирования на основе файлов с начальными данными и результатами
- /src/main/resources/homework01

 Исходники:
 ru.otus.algorithms.tester.Tester.java
 ru.otus.algorithms.tester.Task.java

  Время выполнения - 2 часа
  Оценка:
  +4 байта - создана система тестирования и проверена на задаче 0.String

  Итого: +4 байта.

  Папка с начальными данными и результатами: src/main/resources/homework01/0.String
  Для запуска теста (проверка длины строки) нужно раскомментировать строку в файле Main.java:

     Tester tester = new Tester(new StringLength(), "0.String");

2.  Посмотреть видеоурок «Квадрат Гарри Поттера» и
    разгадать дюжину заклинаний. https://www.youtube.com/watch?v=KmV3aBHfo5w
    Заклинания выписать в отдельном файле и пронумеровать.
    Файл с заклинаниями: src/main/resources/homework01/2.HarryPotter/incantations.properties

    Исходники:
     HarryPotterIncantations.java

    Время выполнения - 4 часа
    Оценка: +3 байта

    Для запуска теста (проверка длины строки) нужно раскомментировать строку в файле Main.java:

     HarryPotterIncantations harryPotterIncantations = new HarryPotterIncantations();


3. Задачу с билетами не смог решить лучше чем было показано на вебинаре(рекурсия).
   Мне не понятно как можно решить эту задачу не прибегая к рекурсии и/или вложенным циклам.
   И не просто решить, но и уменьшить сложность алгоритма.
   Буду признателен за подсказку, чтобы знать куда "копать".

   Папка с начальными данными и результатами: src/main/resources/homework01/1.Tickets

    Исходники:
        LuckyTickets.java

    Время выполнения - 4 часа
    Оценка:
    +1 байт - задача 1.Tickets

    Для запуска теста нужно раскомментировать строку в файле Main.java:

     Tester tester = new Tester(new LuckyTickets(), "1.Tickets");