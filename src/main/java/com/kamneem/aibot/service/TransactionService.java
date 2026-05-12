package com.kamneem.aibot.service;

import com.kamneem.aibot.model.QueryRequest;
import com.kamneem.aibot.model.TransactionResponse;
import com.kamneem.aibot.repository.TransactionRepository;

import java.util.List;

public class TransactionService {

    private final TransactionRepository repository;

    public TransactionService(TransactionRepository repository) {
        this.repository = repository;
    }

    public List<TransactionResponse> searchTransactions(QueryRequest request) {
        return repository.searchTransactions(request);
    }
}