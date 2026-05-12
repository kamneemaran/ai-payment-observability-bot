package com.kamneem.aibot.file;

import com.kamneem.aibot.startup.StartupTask;

import java.nio.file.Files;
import java.nio.file.Path;

public class FileLoader implements StartupTask {

    @Override
    public void execute() {
        try {
            Path logPath = Path.of("src/main/resources/logs/provider.log");

            if (!Files.exists(logPath)) {
                System.out.println("No failure log file");
                return;
            }
            String logs = Files.readString(logPath);
            System.out.println("Loaded failure logs size: " + logs.length());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}