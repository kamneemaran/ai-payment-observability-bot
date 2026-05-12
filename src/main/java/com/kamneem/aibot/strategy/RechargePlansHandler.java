package com.kamneem.aibot.strategy;

import com.kamneem.aibot.model.QueryRequest;
import com.kamneem.aibot.model.QueryResponse;
import com.kamneem.aibot.service.PlanService;

public class RechargePlansHandler implements QueryHandler {

    private final PlanService planService;

    public RechargePlansHandler(PlanService planService) {
        this.planService = planService;
    }

    @Override
    public QueryResponse handle(QueryRequest intent) {
        return QueryResponse.builder()
                .response(planService.getPlans(intent))
                .build();
    }
}