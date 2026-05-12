package com.kamneem.aibot;

import com.kamneem.aibot.client.OpenAIClient;
import com.kamneem.aibot.dao.RechargePlanDao;
import com.kamneem.aibot.dao.TransactionDao;
import com.kamneem.aibot.factory.QueryHandlerFactory;
import com.kamneem.aibot.model.IntentType;
import com.kamneem.aibot.orchestrator.AiOrchestrator;
import com.kamneem.aibot.repository.FileBasedProviderLogRepository;
import com.kamneem.aibot.repository.ProviderLogRepository;
import com.kamneem.aibot.repository.RechargePlanRepository;
import com.kamneem.aibot.repository.TransactionRepository;
import com.kamneem.aibot.repository.impl.RechargePlanRepositoryImpl;
import com.kamneem.aibot.repository.impl.TransactionRepositoryImpl;
import com.kamneem.aibot.service.*;
import com.kamneem.aibot.startup.AppBootStarter;
import com.kamneem.aibot.strategy.ProviderResponseHandler;
import com.kamneem.aibot.strategy.QueryHandler;
import com.kamneem.aibot.strategy.RechargePlansHandler;
import com.kamneem.aibot.strategy.TransactionDetailsHandler;
import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;

import java.util.HashMap;
import java.util.Map;

public class AiBotApplication extends Application<BotConfiguration> {

    public static void main(String[] args)
            throws Exception {

        new AiBotApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<BotConfiguration> bootstrap) {}

    @Override
    public void run(BotConfiguration configuration, Environment environment) {
        //App startup
        AppBootStarter appBootStarter = new AppBootStarter(configuration);
        appBootStarter.initialize();

        // Repository
        ProviderLogRepository providerLogRepository = new FileBasedProviderLogRepository();
        RechargePlanRepository rechargePlanRepository = new RechargePlanRepositoryImpl(new RechargePlanDao(configuration));
        TransactionRepository transactionRepository = new TransactionRepositoryImpl(new TransactionDao(configuration));

        // Service
        ObservabilityService observabilityService = new ObservabilityService(providerLogRepository);
        PlanService planService = new PlanService(rechargePlanRepository);
        TransactionService transactionService = new TransactionService(transactionRepository);
        QueryParserService queryParserService = new QueryParserService();

        // Handler
        QueryHandler providerHandler = new ProviderResponseHandler(observabilityService);
        QueryHandler plansHandler = new RechargePlansHandler(planService);
        QueryHandler txnHandler = new TransactionDetailsHandler(transactionService);

        // Handler Factory
        Map<IntentType, QueryHandler> handlers = new HashMap<>();
        handlers.put(IntentType.GET_PROVIDER_RESPONSE, providerHandler);
        handlers.put(IntentType.GET_RECHARGE_PLANS, plansHandler);
        handlers.put(IntentType.GET_TXN_DETAILS, txnHandler);
        QueryHandlerFactory handlerFactory = new QueryHandlerFactory(handlers);

        // OpenAI Client
        OpenAIClient openAIClient = new OpenAIClient();

        // AI Orchestrator
        AiOrchestrator aiOrchestrator = new AiOrchestrator(openAIClient, handlerFactory, queryParserService);
        PromptLoaderService promptLoaderService = new PromptLoaderService();
        String userPrompt;
        if (System.getenv("INPUT").equals("FILE")) {
            userPrompt = promptLoaderService.loadFromFile();
        } else {
            userPrompt = promptLoaderService.loadFromConsole();
        }
        String response = aiOrchestrator.process(userPrompt);
        System.out.println();
        System.out.println("================ AI RESPONSE ================");
        System.out.println(response);
        System.out.println("=============================================");
    }
}