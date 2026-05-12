package com.kamneem.aibot.repository.impl;

import com.kamneem.aibot.dao.TransactionDao;
import com.kamneem.aibot.model.QueryRequest;
import com.kamneem.aibot.model.TransactionResponse;
import com.kamneem.aibot.repository.TransactionRepository;

import java.util.List;

public class TransactionRepositoryImpl implements TransactionRepository {

    private final TransactionDao transactionDao;

    public TransactionRepositoryImpl(TransactionDao transactionDao) {
        this.transactionDao = transactionDao;
    }

    @Override
    public List<TransactionResponse> searchTransactions(QueryRequest request) {
        return transactionDao.searchTransactions(request);
    }
}