package com.kamneem.aibot.startup;

import com.kamneem.aibot.BotConfiguration;
import com.kamneem.aibot.analytics.AnalyticsLoader;
import com.kamneem.aibot.db.DBInitializer;
import com.kamneem.aibot.file.FileLoader;

import java.util.List;

public class AppBootStarter {
    private final List<StartupTask> tasks;

    public AppBootStarter(BotConfiguration configuration) {
        this.tasks = List.of(
                new DBInitializer(configuration),
                new FileLoader(),
                new AnalyticsLoader()
        );
    }

    public void initialize() {
        for (StartupTask task : tasks) {
            try {
                System.out.println("Running task: " + task.getClass().getSimpleName());
                task.execute();
            } catch (Exception e) {
                System.out.println("Startup task failed: " + task.getClass().getSimpleName());
                e.printStackTrace();
            }
        }
    }
}