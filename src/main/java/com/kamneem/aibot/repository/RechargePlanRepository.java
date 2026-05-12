package com.kamneem.aibot.repository;

import com.kamneem.aibot.model.QueryRequest;
import com.kamneem.aibot.model.RechargePlan;
import java.util.List;

public interface RechargePlanRepository {
    List<RechargePlan> getPlans(QueryRequest request);
}
