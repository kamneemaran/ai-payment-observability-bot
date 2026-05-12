package com.kamneem.aibot.service;

import com.kamneem.aibot.model.ProviderResponseDetails;
import com.kamneem.aibot.model.ProviderLogEntry;
import com.kamneem.aibot.repository.ProviderLogRepository;

public class ObservabilityService {
    private final ProviderLogRepository repository;

    public ObservabilityService(ProviderLogRepository repository) {
        this.repository = repository;
    }

    public ProviderResponseDetails getProviderResponse(String transactionId) {
        ProviderLogEntry log = repository.getByTransactionId(transactionId);

        if (log == null) {
            return null;
        }

        return new ProviderResponseDetails(
                log.getTransactionId(),
                log.getProvider(),
                log.getApiUrl(),
                log.getRequestPayload(),
                log.getResponsePayload(),
                log.getFailureReason(),
                log.getLatencyMs()
        );
    }
}
