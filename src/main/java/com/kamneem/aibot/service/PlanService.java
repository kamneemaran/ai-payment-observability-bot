package com.kamneem.aibot.service;

import com.kamneem.aibot.model.QueryRequest;
import com.kamneem.aibot.model.RechargePlan;
import com.kamneem.aibot.repository.RechargePlanRepository;

import java.util.List;

public class PlanService {
    private final RechargePlanRepository repository;

    public PlanService(RechargePlanRepository repository) {
        this.repository = repository;
    }

    public List<RechargePlan> getPlans(QueryRequest request) {
        return repository.getPlans(request);
    }
}