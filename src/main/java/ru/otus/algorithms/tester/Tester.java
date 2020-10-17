package ru.otus.algorithms.tester;

import ru.otus.algorithms.util.DateUtil;
import ru.otus.algorithms.util.FileUtil;

import java.io.File;
import java.util.Arrays;

import static ru.otus.algorithms.util.FileUtil.TEST_FILE_NAME_PREFIX;

/*-
  Создать систему тестирования на основе файлов
  с начальными данными и результатами - /src/main/resources/homework01

  Время выполнения - 2 часа
  Оценка:
  +4 байта - создана система тестирования и проверена на задаче 0.String

  Итого: +4 байта
 */

public class Tester {
    private final Task task;

    public Tester(File pathTests, Task task) {
        System.out.println("pathTests = " + pathTests);
        this.task = task;
        File[] testFilesArray = pathTests.listFiles((dir, name) -> name.startsWith(TEST_FILE_NAME_PREFIX));
        for (int index = 0; index < testFilesArray.length; index++) {
            File inputFile = new File(pathTests + File.separator + TEST_FILE_NAME_PREFIX + index + ".in");
            if (!inputFile.exists()) {
                break;
            }
            File outputFile = new File(pathTests + File.separator + TEST_FILE_NAME_PREFIX + index + ".out");
            boolean isPassTest = runTester(index, inputFile, outputFile);
        }
    }

    private boolean runTester(int testNumber, File inputFile, File outputFile) {
        String[] data = FileUtil.getFileContentAsArray(inputFile.toPath());
        String[] exptectedArray = FileUtil.getFileContentAsArray(outputFile.toPath());
        long startDate = System.currentTimeMillis();
        String[] actualArray = task.run(data).trim().split("\n");
        long duration = DateUtil.getDurationInMills(startDate, System.currentTimeMillis());
        boolean isPass = true;
        for (int index = 0; index < actualArray.length; index++) {
            isPass = isPass && (exptectedArray[index].equals(actualArray[index]));
        }
        if (isPass) {
            System.out.println("Test#" + testNumber + " - " + isPass + ", duration(ms) = " + duration + ", inputFile = " + inputFile.getName() + ", outputFile = " + outputFile.getName());
        } else {
            System.err.println("Test#" + testNumber + " - " + isPass + ", data = " + Arrays.toString(data)
                    + ", expected = " + Arrays.toString(exptectedArray) + ", actual = " + Arrays.toString(actualArray) + ", duration(ms) = " + duration + ", inputFile = " + inputFile.getName() + ", outputFile = " + outputFile.getName());
        }
        return isPass;
    }
}
