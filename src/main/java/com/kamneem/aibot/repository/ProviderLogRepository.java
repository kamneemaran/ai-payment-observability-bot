package com.kamneem.aibot.repository;

import com.kamneem.aibot.model.ProviderLogEntry;

public interface ProviderLogRepository {
    ProviderLogEntry getByTransactionId(String transactionId);
}