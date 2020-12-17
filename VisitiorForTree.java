package com.company;

import javafx.util.Pair;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;

class VisitiorForTree extends SimpleFileVisitor<Path> {
    Map<Pair<Integer, String>, Directory> directoryMap;
    Path root;
    public VisitiorForTree(Path root){
        this.root = root;
        Directory rootDirectory = new Directory(null, root, root);
        directoryMap = new HashMap<>();
        directoryMap.put(new Pair<Integer, String>(rootDirectory.getNestingLevel(),
                rootDirectory.getName()), rootDirectory);
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        Directory newDir = new Directory(null, dir, root);
        if (directoryMap.containsKey(new Pair<>(newDir.getNestingLevel(), newDir.getName())))
            return FileVisitResult.CONTINUE;
        int levelParent = newDir.getNestingLevel() - 1;
        String[] paths = dir.toString().split("\\\\");
        String nameParent = paths[paths.length - 2];
        Directory parent = directoryMap.get(new Pair<>(levelParent, nameParent));
        parent.getChilds().add(newDir);
        newDir.setParent(parent);
        directoryMap.put(new Pair<>(newDir.getNestingLevel(), newDir.getName()), newDir);
        //System.out.println(Arrays.toString(dir.toString().split("\\\\")));
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileDirectory newFile = new FileDirectory(file, root);
        int levelParent = newFile.getNestingLevel() - 1;
        String[] paths = file.toString().split("\\\\");
        String nameParent = paths[paths.length - 2];
        Directory parent = directoryMap.get(new Pair<>(levelParent, nameParent));
        parent.getFiles().add(newFile);
        return FileVisitResult.CONTINUE;
    }
}