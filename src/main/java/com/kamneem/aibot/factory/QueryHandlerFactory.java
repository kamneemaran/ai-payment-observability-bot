package com.kamneem.aibot.factory;

import com.kamneem.aibot.model.IntentType;
import com.kamneem.aibot.strategy.QueryHandler;

import java.util.Map;

public class QueryHandlerFactory {
    private final Map<IntentType, QueryHandler> handlers;

    public QueryHandlerFactory(Map<IntentType, QueryHandler> handlers) {
        this.handlers = handlers;
    }

    public QueryHandler getHandler(IntentType intentType) {
        QueryHandler handler = handlers.get(intentType);
        if (handler == null) {
            throw new RuntimeException("No handler found for intent: " + intentType);
        }
        return handler;
    }
}