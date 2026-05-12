package com.kamneem.aibot.db;

import com.kamneem.aibot.BotConfiguration;
import com.kamneem.aibot.startup.StartupTask;

import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBInitializer implements StartupTask {
    private final BotConfiguration configuration;

    public DBInitializer(BotConfiguration configuration) {
        this.configuration = configuration;
    }

    public void execute() {
        try {
            runSqlFile("src/main/resources/db/schema.sql");
            if (isSeedRequired()) {
                runSqlFile("src/main/resources/db/seed.sql");
            }
            System.out.println("DB initialized");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isSeedRequired() {
        try (Connection connection = getConnection(); Statement stmt = connection.createStatement()) {
            var rs = stmt.executeQuery("SELECT COUNT(*) FROM recharge_plans");
            rs.next();
            return rs.getInt(1) == 0;
        } catch (Exception e) {
            return true;
        }
    }

    private void runSqlFile(String path) throws Exception {
        String sql = Files.readString(Path.of(path));
        try (Connection connection = getConnection(); Statement stmt = connection.createStatement()) {
            for (String query : sql.split(";")) {
                if (!query.trim().isEmpty()) {
                    stmt.execute(query);
                }
            }
        }
    }

    private Connection getConnection() throws Exception {
        return DriverManager.getConnection(configuration.getDatabase().getUrl(),
                configuration.getDatabase().getUser(),
                configuration.getDatabase().getPassword());
    }
}