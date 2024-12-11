package com.example.solana.certbadgemanager.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;

public class ProjectExplorer {
    public static void main(String[] args) {
        File projectDir = new File("src");
        File outputFile = new File("ProjectStructure.txt");
        try (FileWriter writer = new FileWriter(outputFile)) {
            exploreDirectory(projectDir, writer);
            System.out.println("The structure of the project is saved in " + outputFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void exploreDirectory(File dir, FileWriter writer) throws IOException {
        for (File file : dir.listFiles()) {
            if (file.isDirectory()) {
                exploreDirectory(file, writer);
            } else if (file.getName().endsWith(".java")) {
                String className = getClassNameFromFile(file);
                try {
                    Class<?> clazz = Class.forName(className);

                    writer.write("Class: " + clazz.getName() + "\n");

                    Method[] methods = clazz.getDeclaredMethods();
                    for (Method method : methods) {
                        writer.write("  Method: " + method.getName() + "\n");
                    }

                } catch (ClassNotFoundException e) {
                    writer.write("Class not found: " + className + "\n");
                }
            }
        }
    }

    private static String getClassNameFromFile(File file) {
        String filePath = file.getAbsolutePath();
        String className = filePath.substring(filePath.indexOf("src") + 4, filePath.lastIndexOf("."));
        className = className.replace(File.separatorChar, '.');

        if (filePath.contains("test")) {
            className = "test." + className;
        }
        return className;
    }
}
