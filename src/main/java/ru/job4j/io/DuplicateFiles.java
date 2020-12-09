package ru.job4j.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.nio.file.FileVisitResult.CONTINUE;

public class DuplicateFiles implements FileVisitor<Path> {
    private final Map<String, List<String>> files;

    public DuplicateFiles() {
        this.files = new HashMap<>();
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
        return CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        String filename = file.toFile().getName();
        files.computeIfAbsent(filename, k -> new ArrayList<>());
        files.get(file.toFile().getName()).add(file.toAbsolutePath().toString());
        return CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) {
        return CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
        return CONTINUE;
    }

    public Map<String, List<String>> getFiles() {
        return files;
    }
}
