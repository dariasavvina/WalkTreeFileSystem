package com.company;

import javafx.util.Pair;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InterruptedException, IOException {
        Scanner scanner = new Scanner(System.in);
        Directory root = getDirectory(Paths.get(scanner.nextLine()));
        root.printToDirectory();
    }

    public static Directory getDirectory(Path rootDir) throws IOException, InterruptedException {
        VisitiorForTree visitior = new VisitiorForTree(rootDir);
        Files.walkFileTree(rootDir, visitior);
        String[] paths = rootDir.toString().split("\\\\");
        return visitior.directoryMap.get(new Pair<>(0, paths[paths.length - 1]));
    }

}
