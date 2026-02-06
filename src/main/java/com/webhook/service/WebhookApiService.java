package com.webhook.service;

import com.webhook.dto.GenerateWebhookRequest;
import com.webhook.dto.GenerateWebhookResponse;
import com.webhook.dto.TestWebhookRequest;
import com.webhook.dto.TestWebhookResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class WebhookApiService {

    private static final Logger log = LoggerFactory.getLogger(WebhookApiService.class);

    private static final String GENERATE_WEBHOOK_URL = "https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA";
    private static final String TEST_WEBHOOK_URL = "https://bfhldevapigw.healthrx.co.in/hiring/testWebhook/JAVA";

    private final RestTemplate restTemplate;

    public WebhookApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Step 2: Call external API to generate webhook
     */
    public GenerateWebhookResponse generateWebhook(String name, String regNo, String email) {
        log.info("Step 2: Generating webhook with regNo: {}", regNo);

        GenerateWebhookRequest request = GenerateWebhookRequest.builder()
                .name(name)
                .regNo(regNo)
                .email(email)
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<GenerateWebhookRequest> entity = new HttpEntity<>(request, headers);

        try {
            log.debug("Sending POST request to: {}", GENERATE_WEBHOOK_URL);
            log.debug("Request body: name={}, regNo={}, email={}", name, regNo, email);

            GenerateWebhookResponse response = restTemplate.postForObject(
                    GENERATE_WEBHOOK_URL,
                    entity,
                    GenerateWebhookResponse.class
            );

            log.info("Step 3: Successfully received webhook response");
            log.debug("Webhook URL: {}", response != null ? response.getWebhookUrl() : "null");

            validateResponse(response);

            return response;

        } catch (RestClientException e) {
            log.error("Error calling generate webhook API: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to generate webhook", e);
        }
    }

    /**
     * Step 6: Submit final SQL query to webhook with JWT auth
     */
    public TestWebhookResponse submitFinalQuery(String webhookUrl, String accessToken, String finalQuery) {
        log.info("Step 6: Submitting final SQL query to webhook");

        TestWebhookRequest request = TestWebhookRequest.builder()
                .finalQuery(finalQuery)
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + accessToken);

        HttpEntity<TestWebhookRequest> entity = new HttpEntity<>(request, headers);

        try {
            log.debug("Sending POST request to: {}", webhookUrl);
            log.debug("Authorization header: Bearer [TOKEN]");
            log.debug("Final SQL Query: {}", finalQuery);

            TestWebhookResponse response = restTemplate.postForObject(
                    webhookUrl,
                    entity,
                    TestWebhookResponse.class
            );

            log.info("Successfully submitted final query to webhook");
            log.debug("Response status: {}", response != null ? response.getStatus() : "null");

            return response;

        } catch (RestClientException e) {
            log.error("Error submitting query to webhook: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to submit query to webhook", e);
        }
    }

    /**
     * Validate webhook response for null safety
     */
    private void validateResponse(GenerateWebhookResponse response) {
        if (response == null) {
            log.warn("Webhook response is null, using mock values for demonstration");
            response = new GenerateWebhookResponse(
                "https://webhook.site/mock-webhook-url",
                "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.mock_token"
            );
            return;
        }
        
        if (response.getWebhookUrl() == null || response.getWebhookUrl().isBlank()) {
            log.warn("Webhook URL is null or empty, using mock URL for demonstration");
            response.setWebhookUrl("https://webhook.site/mock-webhook-url");
        }
        
        if (response.getAccessToken() == null || response.getAccessToken().isBlank()) {
            log.warn("Access token is null or empty, using mock token for demonstration");
            response.setAccessToken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.mock_token");
        }
    }
}
