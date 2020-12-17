package com.company;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

class Directory{
    private Directory parent;
    private List<Directory> childs;
    private List<FileDirectory> files;
    private String name;
    private int nestingLevel;

    public static int calculateNestingLevel(Path path, Path rootDir){
        String[] levels = path.toString().split("\\\\");
        String[] rootLevels = rootDir.toString().split("\\\\");
        return levels.length - rootLevels.length;
    }

    public Directory(Directory parent, Path pathToDirectory, Path root){
        this.parent = parent;
        childs = new ArrayList<>();
        String[] paths = pathToDirectory.toString().split("\\\\");
        this.name = paths[paths.length - 1];
        this.files = new ArrayList<>();
        nestingLevel = calculateNestingLevel(pathToDirectory, root);
    }

    public void setParent(Directory parent) {
        this.parent = parent;
    }

    public int getNestingLevel() {
        return nestingLevel;
    }

    public String getName() {
        return name;
    }

    public List<Directory> getChilds() {
        return childs;
    }

    public List<FileDirectory> getFiles() {
        return files;
    }

    public Directory getParent() {
        return parent;
    }

    public void printToDirectory(){
        for (int i = 0; i < nestingLevel; i++){
            System.out.print("  ");
        }
        System.out.print(name + ":" + "\n");
        if (childs.size() != 0){
            childs.forEach(x -> x.printToDirectory());
        }
        if (files.size() != 0)
            files.forEach(x -> x.printToFiles());
    }
}
