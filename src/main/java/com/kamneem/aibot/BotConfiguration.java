package com.kamneem.aibot;

import com.kamneem.aibot.config.DatabaseConfig;
import io.dropwizard.core.Configuration;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BotConfiguration extends Configuration {
    private DatabaseConfig database;

    public DatabaseConfig getDatabase() {
        return database;
    }
}