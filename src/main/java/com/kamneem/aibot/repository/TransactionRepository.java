package com.kamneem.aibot.repository;

import com.kamneem.aibot.model.QueryRequest;
import com.kamneem.aibot.model.TransactionResponse;

import java.util.List;

public interface TransactionRepository {
    List<TransactionResponse> searchTransactions(QueryRequest request);
}