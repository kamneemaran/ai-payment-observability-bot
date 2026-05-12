package com.kamneem.aibot.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class PromptLoaderService {
    public String loadFromConsole() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("====================================");
        System.out.println(" AI Recharge Assistant");
        System.out.println("====================================");
        System.out.print("Ask your question: ");

        return scanner.nextLine();
    }

    public String loadFromFile() {
        try {
            String filePath = "src/main/resources/prompt.txt";
            return Files.readString(Paths.get(filePath));
        } catch (IOException e) {
            throw new RuntimeException("Unable to read prompt file", e);
        }
    }
}
