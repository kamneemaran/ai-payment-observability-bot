package com.kamneem.aibot.dao;

import com.kamneem.aibot.BotConfiguration;
import com.kamneem.aibot.model.QueryRequest;
import com.kamneem.aibot.model.RechargePlan;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RechargePlanDao {

    private final BotConfiguration configuration;

    public RechargePlanDao(BotConfiguration configuration) {
        this.configuration = configuration;
    }

    public List<RechargePlan> getPlans(QueryRequest request) {

        StringBuilder query = new StringBuilder("SELECT * FROM recharge_plans WHERE 1=1");

        List<Object> params = new ArrayList<>();
        if (request.getOperator() != null) {
            query.append(" AND operator = ?");
            params.add(request.getOperator());
        }
        if (request.getAmount() != null) {
            query.append(" AND amount = ?");
            params.add(request.getAmount());
        }
        if (request.getCircle() != null) {
            query.append(" AND circle = ?");
            params.add(request.getCircle());
        }
        if (request.getPlanType() != null) {
            query.append(" AND plan_type = ?");
            params.add(request.getPlanType());
        }

        try (
                Connection connection = DriverManager.getConnection(configuration.getDatabase().getUrl(),
                                configuration.getDatabase().getUser(), configuration.getDatabase().getPassword());

                PreparedStatement stmt = connection.prepareStatement(query.toString())
        ) {

            // Dynamically bind params
            for (int i = 0; i < params.size(); i++) {
                stmt.setObject(i + 1, params.get(i));
            }

            ResultSet rs = stmt.executeQuery();

            List<RechargePlan> responses = new ArrayList<>();
            while (rs.next()) {
                responses.add(
                        RechargePlan.builder()
                                .planId(rs.getString("plan_id"))
                                .operator(rs.getString("operator"))
                                .circle(rs.getString("circle"))
                                .amount(rs.getInt("amount"))
                                .validity(rs.getInt("validity"))
                                .planType(rs.getString("plan_type"))
                                .description(rs.getString("description"))
                                .dataLimit(rs.getDouble("data_limit"))
                                .build()
                );
            }
            return responses;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}