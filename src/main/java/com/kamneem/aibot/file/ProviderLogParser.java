package com.kamneem.aibot.file;

import com.kamneem.aibot.model.ProviderLogEntry;

public class ProviderLogParser {
    public ProviderLogEntry parse(String line) {
        String[] parts = line.split("\\|");
        return ProviderLogEntry.builder()
                .transactionId(parts[0])
                .provider(parts[1])
                .apiUrl(parts[2])
                .requestPayload(parts[3])
                .responsePayload(parts[4])
                .failureReason(parts[5])
                .latencyMs(Long.parseLong(parts[6]))
                .build();
    }
}