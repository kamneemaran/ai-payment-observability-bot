package com.kamneem.aibot.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QueryRequest {
    private IntentType intentType;
    private String userPrompt;
    private String transactionId;
    private String operator;
    private String circle;
    private Integer amount;
    private String userId;
    private String paymentMode;
    private String status;
    private String planType;
}