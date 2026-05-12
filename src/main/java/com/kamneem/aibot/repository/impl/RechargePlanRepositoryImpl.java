package com.kamneem.aibot.repository.impl;

import com.kamneem.aibot.dao.RechargePlanDao;
import com.kamneem.aibot.model.QueryRequest;
import com.kamneem.aibot.model.RechargePlan;
import com.kamneem.aibot.repository.RechargePlanRepository;

import java.util.List;

public class RechargePlanRepositoryImpl implements RechargePlanRepository {
    private final RechargePlanDao rechargePlanDao;

    public RechargePlanRepositoryImpl(RechargePlanDao rechargePlanDao) {
        this.rechargePlanDao = rechargePlanDao;
    }

    @Override
    public List<RechargePlan> getPlans(QueryRequest request) {
        return rechargePlanDao.getPlans(request);
    }
}