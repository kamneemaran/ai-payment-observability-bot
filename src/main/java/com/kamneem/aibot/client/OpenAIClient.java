package com.kamneem.aibot.client;

import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class OpenAIClient {
    private final OkHttpClient client = new OkHttpClient();

    public String getCompletion(String systemPrompt, String userQuery) {
        AIProvider provider = getAIProvider();
        boolean isGemini = provider.getName().equals("GEMINI");

        // 1. Build the body based on provider type
        JSONObject body = isGemini ?
                buildGeminiBody(systemPrompt, userQuery) :
                buildOpenAIBody(provider.getModel(), systemPrompt, userQuery);

        // 2. Build the request
        Request.Builder requestBuilder = new Request.Builder()
                .url(provider.getApiUrl())
                .addHeader("Content-Type", "application/json")
                .post(RequestBody.create(body.toString(), MediaType.parse("application/json")));

        // 3. Add specific Auth headers
        if (isGemini) {
            requestBuilder.addHeader("X-goog-api-key", provider.getApiKey());
        } else {
            requestBuilder.addHeader("Authorization", "Bearer " + provider.getApiKey());
        }

        try (Response response = client.newCall(requestBuilder.build()).execute()) {
            if (!response.isSuccessful()) {
                throw new RuntimeException("LLM API failed [" + provider.getName() + "]: " + response.code() + " " + response.body().string());
            }
            JSONObject json = new JSONObject(response.body().string());

            // 4. Parse the specific response structure
            return isGemini ?
                    json.getJSONArray("candidates").getJSONObject(0).getJSONObject("content").getJSONArray("parts").getJSONObject(0).getString("text") :
                    json.getJSONArray("choices").getJSONObject(0).getJSONObject("message").getString("content");

        } catch (IOException e) {
            throw new RuntimeException("Network error calling LLM", e);
        }
    }

    private JSONObject buildGeminiBody(String system, String user) {
        return new JSONObject()
                .put("system_instruction", new JSONObject()
                        .put("parts", new JSONArray().put(new JSONObject().put("text", system))))
                .put("contents", new JSONArray().put(new JSONObject()
                        .put("role", "user")
                        .put("parts", new JSONArray().put(new JSONObject().put("text", user)))))
                .put("generationConfig", new JSONObject().put("temperature", 0.1));
    }

    private JSONObject buildOpenAIBody(String model, String system, String user) {
        return new JSONObject()
                .put("model", model)
                .put("temperature", 0.1)
                .put("messages", new JSONArray()
                        .put(new JSONObject().put("role", "system").put("content", system))
                        .put(new JSONObject().put("role", "user").put("content", user)));
    }

    private AIProvider getAIProvider() {
        String providerType = System.getenv("AI_PROVIDER");
        if ("GEMINI_AI".equals(providerType)) {
            return AIProvider.builder()
                    .name("GEMINI")
                    .apiUrl("https://generativelanguage.googleapis.com/v1beta/models/gemini-flash-latest:generateContent")
                    .apiKey(System.getenv("GEMINI_API_KEY"))
                    .build();
        }
        return AIProvider.builder()
                .name("OPENAI")
                .apiUrl("https://api.openai.com/v1/chat/completions")
                .apiKey(System.getenv("OPENAI_API_KEY"))
                .model("gpt-4.1-mini")
                .build();
    }

    public String extractIntent(String userPrompt) {
        String systemPrompt = """
            You are an intent classifier.

            Supported intents:

            GET_PROVIDER_RESPONSE
            GET_TXN_DETAILS
            GET_RECHARGE_PLANS

            Rules:
            - Return ONLY one intent
            - No numbering
            - No explanation
            - No extra text
            - Output must exactly match intent name

            Example outputs:
            GET_TXN_DETAILS
            GET_PROVIDER_RESPONSE
            GET_RECHARGE_PLANS
            """;

        return getCompletion(systemPrompt, userPrompt).trim();
    }

    public String generateHumanResponse(String userPrompt, Object data) {
        PromptBuilder promptBuilder = new PromptBuilder();
        String systemPrompt = promptBuilder.buildRechargeAssistantPrompt(userPrompt);
        return getCompletion(systemPrompt, String.valueOf(data));
    }
}