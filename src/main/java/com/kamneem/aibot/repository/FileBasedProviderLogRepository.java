package com.kamneem.aibot.repository;

import com.kamneem.aibot.model.ProviderLogEntry;
import com.kamneem.aibot.file.ProviderLogParser;

import java.nio.file.Files;
import java.nio.file.Path;

public class FileBasedProviderLogRepository implements ProviderLogRepository {
    private static final String LOG_FILE = "src/main/resources/logs/provider.log";
    private final ProviderLogParser parser = new ProviderLogParser();

    @Override
    public ProviderLogEntry getByTransactionId(String transactionId) {
        try {
            return Files.lines(Path.of(LOG_FILE))
                    .map(parser::parse)
                    .filter(log -> transactionId.equals(log.getTransactionId()))
                    .findFirst()
                    .orElse(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}