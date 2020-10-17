package ru.otus.algorithms.homework06;

import ru.otus.algorithms.util.FileUtil;
import ru.otus.algorithms.util.NumberUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

/*-
    Сгенерировать бинарный файл, который содержит N целых, 16-битных чисел (от 0 до 65535), по 2 байта на каждое число.
    Подобрать N для вашего языка программирования: 1e6, 1e7, 1e8, 1e9 или даже больше.
 */
public class GenerateNumbers {
    // На моей машине смог сгенерить максимум 100000000 чисел в файл.
    // Файл: /src/main/resources/homework06/generate/100000000
    public static void writeToFileRandomNumbers(String pathBase, int countOfNumbers) {
        Path path = Paths.get(pathBase + File.separator + countOfNumbers);
        int[] arrInt = new int[countOfNumbers];
        Random random = new Random();
        for (int index = 0; index < countOfNumbers; index++) {
            int randomNumber = random.nextInt(65535);
            arrInt[index] = randomNumber;
        }
        byte[] arrBytes = NumberUtil.intArray2ByteArray(arrInt);
        FileUtil.writeBinaryDataToFile(path, arrBytes);
        System.out.println("Success write to file " + path);
    }

    public static int[] readFromFile(String pathBase, int countOfNumbers) {
       return readFromFile(pathBase, countOfNumbers, 0, countOfNumbers);
    }

    public static int[] readFromFile(String pathBase, int countOfNumbers, int from, int to) {
        try {
            Path path = Paths.get(pathBase + File.separator + countOfNumbers);
            //System.out.println("Read from file " + path);
            byte[] arrBytes = FileUtil.readBinaryDataFromFile(path);
            int[] arrInt = NumberUtil.byteArray2IntArray(arrBytes, from, to);
            //System.out.println("from :" + from + ", to:" + to + " -> arrInt:" + Arrays.toString(arrInt));
            return arrInt;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
