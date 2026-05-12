package com.kamneem.aibot.analytics;

import com.kamneem.aibot.startup.StartupTask;

public class AnalyticsLoader implements StartupTask {

    @Override
    public void execute() {
        System.out.println(
                "Analytics system initialized"
        );
    }
}