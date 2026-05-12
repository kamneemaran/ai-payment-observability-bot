package com.kamneem.aibot.client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AIProvider {
    private String name;
    private String apiUrl;
    private String model;
    private String apiKey;
}
