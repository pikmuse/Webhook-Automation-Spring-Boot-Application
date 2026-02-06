package com.webhook.dto;

public class TestWebhookRequest {
    private String finalQuery;

    public TestWebhookRequest() {
    }

    public TestWebhookRequest(String finalQuery) {
        this.finalQuery = finalQuery;
    }

    public String getFinalQuery() {
        return finalQuery;
    }

    public void setFinalQuery(String finalQuery) {
        this.finalQuery = finalQuery;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String finalQuery;

        public Builder finalQuery(String finalQuery) {
            this.finalQuery = finalQuery;
            return this;
        }

        public TestWebhookRequest build() {
            return new TestWebhookRequest(finalQuery);
        }
    }
}
