package ru.otus.algorithms.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
    public static final File TEST_FOLDER_BASE_PATH =
            new File("src" + File.separator + "main" + File.separator + "resources");
    public static final String TEST_FILE_NAME_PREFIX = "test.";

    public static String[] getFileContentAsArray(Path path) {
        List<String> result = new ArrayList<>();
        try {
            Files.lines(path).forEach(result::add);
            return result.toArray(new String[0]);
        } catch (IOException e) {
            e.printStackTrace();
            return new String[0];
        }
    }

    public static void writeBinaryDataToFile(Path path, byte[] data) {
        try {
            Files.write(path, data);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static byte[] readBinaryDataFromFile(Path path) throws IOException {
        return Files.readAllBytes(path);
    }

}
