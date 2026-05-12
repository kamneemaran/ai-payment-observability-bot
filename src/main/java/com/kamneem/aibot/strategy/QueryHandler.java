package com.kamneem.aibot.strategy;

import com.kamneem.aibot.model.QueryRequest;
import com.kamneem.aibot.model.QueryResponse;

public interface QueryHandler {
    QueryResponse handle(QueryRequest request);
}
