package com.kamneem.aibot.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProviderLogEntry {
    private String transactionId;
    private String provider;
    private String apiUrl;
    private String requestPayload;
    private String responsePayload;
    private String failureReason;
    private long latencyMs;
}
