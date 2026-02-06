package com.webhook.dto;

public class TestWebhookResponse {
    private String status;
    private String message;

    public TestWebhookResponse() {
    }

    public TestWebhookResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String status;
        private String message;

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public TestWebhookResponse build() {
            return new TestWebhookResponse(status, message);
        }
    }
}
