package com.kamneem.aibot.strategy;

import com.kamneem.aibot.model.ProviderResponseDetails;
import com.kamneem.aibot.model.QueryRequest;
import com.kamneem.aibot.model.QueryResponse;
import com.kamneem.aibot.service.ObservabilityService;

public class ProviderResponseHandler implements QueryHandler {
    private final ObservabilityService service;

    public ProviderResponseHandler(ObservabilityService service) {
        this.service = service;
    }

    @Override
    public QueryResponse handle(QueryRequest request) {
        ProviderResponseDetails details = service.getProviderResponse(request.getTransactionId());
        return QueryResponse.builder()
                .response(details)
                .build();
    }
}