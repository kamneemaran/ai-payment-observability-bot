package com.kamneem.aibot.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RechargePlan {
    private String planId;
    private String name;
    private String operator;
    private String circle;
    private double amount;
    private int validity;
    private double dataLimit;
    private String description;
    private String planType;
}