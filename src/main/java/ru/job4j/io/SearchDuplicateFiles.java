package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SearchDuplicateFiles {
    public static void main(String[] args) throws IOException {
        DuplicateFiles duplicateFiles = new DuplicateFiles();
        Files.walkFileTree(Paths.get("."), duplicateFiles);

        for (String filename : duplicateFiles.getFiles().keySet()) {
            if (duplicateFiles.getFiles().get(filename).size() > 1) {
                System.out.println(filename);
                for (String f : duplicateFiles.getFiles().get(filename)) {
                    System.out.println("\t" + f);
                }
            }
        }
    }
}
