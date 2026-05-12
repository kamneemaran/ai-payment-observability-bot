package com.kamneem.aibot.strategy;

import com.kamneem.aibot.model.QueryRequest;
import com.kamneem.aibot.model.QueryResponse;
import com.kamneem.aibot.model.TransactionResponse;
import com.kamneem.aibot.service.TransactionService;

import java.util.List;

public class TransactionDetailsHandler implements QueryHandler {

    private final TransactionService transactionService;

    public TransactionDetailsHandler(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Override
    public QueryResponse handle(QueryRequest request) {
        List<TransactionResponse> transactionResponses = transactionService.searchTransactions(request);
        return QueryResponse.builder()
                .response(transactionResponses)
                .build();
    }
}