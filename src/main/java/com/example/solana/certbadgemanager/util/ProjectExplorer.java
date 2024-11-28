package com.example.solana.certbadgemanager.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;

public class ProjectExplorer {
    public static void main(String[] args) {
        File projectDir = new File("src"); // Шлях до вашого проекту
        File outputFile = new File("ProjectStructure.txt");
        try (FileWriter writer = new FileWriter(outputFile)) {
            exploreDirectory(projectDir, writer);
            System.out.println("Структура проекту збережена в " + outputFile.getAbsolutePath());
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
                    // Завантажуємо клас за його ім'ям
                    Class<?> clazz = Class.forName(className);

                    // Записуємо ім'я класу в файл
                    writer.write("Class: " + clazz.getName() + "\n");

                    // Отримуємо та записуємо методи класу
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

    // Оновлений метод для отримання імені класу на основі шляху до файлу
    private static String getClassNameFromFile(File file) {
        String filePath = file.getAbsolutePath();
        // Очищаємо шлях до файлу, щоб він починався з 'src' і додаємо пакет
        String className = filePath.substring(filePath.indexOf("src") + 4, filePath.lastIndexOf("."));
        className = className.replace(File.separatorChar, '.'); // Замінюємо роздільники папок на точку

        // Якщо клас знаходиться в тестах, то додаємо 'test.' до початку імені класу
        if (filePath.contains("test")) {
            className = "test." + className;
        }
        return className;
    }
}
