package com.webhook.dto;

public class GenerateWebhookRequest {
    private String name;
    private String regNo;
    private String email;

    public GenerateWebhookRequest() {
    }

    public GenerateWebhookRequest(String name, String regNo, String email) {
        this.name = name;
        this.regNo = regNo;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String name;
        private String regNo;
        private String email;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder regNo(String regNo) {
            this.regNo = regNo;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public GenerateWebhookRequest build() {
            return new GenerateWebhookRequest(name, regNo, email);
        }
    }
}
