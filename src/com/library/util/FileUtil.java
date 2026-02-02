package com.library.util;

import java.io.*;
import java.util.*;

public class FileUtil {

    public static List<String> readAll(String fileName) throws IOException {
        File file = new File(fileName);
        if (!file.exists()) file.createNewFile();
        return java.nio.file.Files.readAllLines(file.toPath());
    }

    public static void writeAll(String fileName, List<String> data) throws IOException {
        FileWriter writer = new FileWriter(fileName);
        for (String line : data) {
            writer.write(line + "\n");
        }
        writer.close();
    }
}


