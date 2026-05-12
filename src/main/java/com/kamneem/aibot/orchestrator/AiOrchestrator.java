package com.kamneem.aibot.orchestrator;

import com.kamneem.aibot.client.OpenAIClient;
import com.kamneem.aibot.factory.QueryHandlerFactory;
import com.kamneem.aibot.model.IntentType;
import com.kamneem.aibot.model.QueryRequest;
import com.kamneem.aibot.model.QueryResponse;
import com.kamneem.aibot.service.QueryParserService;
import com.kamneem.aibot.strategy.QueryHandler;

public class AiOrchestrator {
    private final OpenAIClient openAIClient;
    private final QueryHandlerFactory handlerFactory;
    private final QueryParserService queryParserService;

    public AiOrchestrator(OpenAIClient openAIClient, QueryHandlerFactory handlerFactory, QueryParserService queryParserService) {
        this.openAIClient = openAIClient;
        this.handlerFactory = handlerFactory;
        this.queryParserService = queryParserService;
    }

    public String process(String userPrompt) {
        String intent = openAIClient.extractIntent(userPrompt);
        System.out.println("Detected intent: " + intent);
        IntentType intentType = IntentType.valueOf(intent);
        System.out.println(userPrompt);
        QueryRequest request = queryParserService.parse(userPrompt, intentType);
        System.out.println("Parsed Request: " + request);
        QueryHandler handler = handlerFactory.getHandler(request.getIntentType());
        QueryResponse response = handler.handle(request);
        return openAIClient.generateHumanResponse(userPrompt, response);
    }
}
