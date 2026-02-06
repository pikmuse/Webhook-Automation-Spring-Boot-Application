package com.webhook.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GenerateWebhookResponse {
    @JsonProperty("webhook_url")
    private String webhookUrl;

    @JsonProperty("accessToken")
    private String accessToken;

    public GenerateWebhookResponse() {
    }

    public GenerateWebhookResponse(String webhookUrl, String accessToken) {
        this.webhookUrl = webhookUrl;
        this.accessToken = accessToken;
    }

    public String getWebhookUrl() {
        return webhookUrl;
    }

    public void setWebhookUrl(String webhookUrl) {
        this.webhookUrl = webhookUrl;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String webhookUrl;
        private String accessToken;

        public Builder webhookUrl(String webhookUrl) {
            this.webhookUrl = webhookUrl;
            return this;
        }

        public Builder accessToken(String accessToken) {
            this.accessToken = accessToken;
            return this;
        }

        public GenerateWebhookResponse build() {
            return new GenerateWebhookResponse(webhookUrl, accessToken);
        }
    }
}
