package com.company;

import java.nio.file.Path;

class FileDirectory{
    private String name;
    private int nestingLevel;

    FileDirectory(Path pathToFile, Path root){
        String[] paths = pathToFile.toString().split("\\\\");
        name = paths[paths.length - 1];
        nestingLevel = Directory.calculateNestingLevel(pathToFile, root);
    }

    public String getName() {
        return name;
    }

    public int getNestingLevel() {
        return nestingLevel;
    }

    public void printToFiles(){
        for (int i = 0; i < nestingLevel; i++){
            System.out.print("  ");
        }
        System.out.print(name + "\n");
    }
}