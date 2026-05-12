package com.kamneem.aibot.client;

public class PromptBuilder {
    public String buildRechargeAssistantPrompt(String userPrompt) {
        return """
                You are an intelligent recharge assistant.

                Responsibilities:
                - Analyze recharge failures
                - Suggest best recharge plans
                - Explain possible root causes
                - Respond professionally

                User Query:
                %s
                """.formatted(userPrompt);
    }
}
