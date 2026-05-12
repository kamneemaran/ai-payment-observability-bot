package com.kamneem.aibot.service;

import com.kamneem.aibot.model.IntentType;
import com.kamneem.aibot.model.QueryRequest;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QueryParserService {
    private static final Pattern TXN_PATTERN = Pattern.compile("\\b(TXN\\d+)\\b", Pattern.CASE_INSENSITIVE);
    private static final Pattern USER_PATTERN = Pattern.compile("\\b(USER\\d+)\\b", Pattern.CASE_INSENSITIVE);
    private static final Pattern AMOUNT_PATTERN = Pattern.compile("\\b(\\d{2,5})\\b");

    public QueryRequest parse(String userPrompt, IntentType intentType) {
        return QueryRequest.builder()
                .intentType(intentType)
                .userPrompt(userPrompt)
                .transactionId(extractTransactionId(userPrompt).orElse(null))
                .userId(extractUserId(userPrompt).orElse(null))
                .operator(extractOperator(userPrompt).orElse(null))
                .amount(extractAmount(userPrompt).orElse(null))
                .paymentMode(extractPaymentMode(userPrompt).orElse(null))
                .circle(extractCircle(userPrompt).orElse(null))
                .build();
    }

    private Optional<String> extractTransactionId(String prompt) {
        Matcher matcher = TXN_PATTERN.matcher(prompt);
        if (matcher.find()) {
            return Optional.of(matcher.group(1).toUpperCase());
        }
        return Optional.empty();
    }

    private Optional<String> extractUserId(String prompt) {
        Matcher matcher = USER_PATTERN.matcher(prompt);
        if (matcher.find()) {
            return Optional.of(matcher.group(1).toUpperCase());
        }
        return Optional.empty();
    }

    private Optional<Integer> extractAmount(String prompt) {
        Matcher matcher = AMOUNT_PATTERN.matcher(prompt);
        if (matcher.find()) {
            return Optional.of(Integer.parseInt(matcher.group(1)));
        }
        return Optional.empty();
    }

    private Optional<String> extractOperator(String prompt) {
        if (prompt != null) {
            return Optional.of(prompt);
        }
        return Optional.empty();
    }

    private Optional<String> extractCircle(String prompt) {
        if (prompt != null) {
            return Optional.of(prompt);
        }
        return Optional.empty();
    }

    private Optional<String> extractPaymentMode(String prompt) {
        if (prompt != null) {
            return Optional.of(prompt);
        }
        return Optional.empty();
    }
}