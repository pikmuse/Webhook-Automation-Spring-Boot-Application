#Webhook Automation Spring Boot Application#

Overview

This project is a Spring Boot application that performs an automated webhook workflow on application startup. The application integrates with external APIs to generate a webhook, processes the response, applies decision logic, and submits a final SQL query using JWT-based authorization.

The application is designed to demonstrate backend integration skills, clean architecture, and proper handling of REST APIs in a production-style setup.

Objective

The application automatically performs the following steps on startup:

Sends a POST request to generate a webhook.

Receives a webhook URL and JWT access token.

Applies decision logic based on the registration number.

Prepares a final SQL query.

Submits the SQL query to the webhook endpoint using the provided JWT token.

This process runs without any manual triggers or REST endpoints.

Tech Stack

Java 17+

Spring Boot

Maven

RestTemplate for HTTP communication

Lombok

SLF4J for logging

Project Structure

The project follows a clean layered structure:

src/main/java/com/example/webhookclient
│
├── dto
│   ├── GenerateWebhookRequest.java
│   ├── GenerateWebhookResponse.java
│   └── SubmitQueryRequest.java
│
├── service
│   └── WebhookService.java
│
├── runner
│   └── StartupRunner.java
│
└── WebhookClientApplication.java

Package Responsibilities

dto
Contains request and response models for API communication.

service
Handles API calls, business logic, and workflow execution.

runner
Executes the automated workflow on application startup.

main application
Bootstraps the Spring Boot application.

How It Works
Step 1: Generate Webhook

On startup, the application sends a POST request to the generate webhook API with:

name

regNo

email

The API returns:

webhook URL

JWT access token

Step 2: Decision Logic

The application extracts the last two digits of the registration number:

Odd → Question 1 path

Even → Question 2 path

This step only determines logic flow. No external question fetching is performed.

Step 3: SQL Query Preparation

A final SQL query is prepared and stored as a string.
This represents the solved SQL problem.

Step 4: Submit Final Query

The application sends the final SQL query to the webhook endpoint with:

Authorization header using the JWT token

JSON request body containing the finalQuery field

Prerequisites

Ensure the following are installed:

Java 17 or higher

Maven 3.8+

Git

Internet connection for API calls

Running the Application
Using Maven
mvn spring-boot:run

Running the JAR

Build:

mvn clean package


Run:

java -jar target/webhookclient.jar

Configuration

application.properties:

server.port=8080
logging.level.root=INFO

Logging

The application logs:

Startup events

API requests and responses

Token and webhook extraction

Decision logic results

Submission results

Errors and exceptions

Error Handling

The application includes:

Try-catch handling for API calls

Handling of HTTP 4xx and 5xx responses

Null safety checks

Clear logging for troubleshooting

Design Decisions

No controllers are used because the workflow must run automatically.

No database is included since persistence is not required.

JWT tokens are used directly without decoding as per requirements.

The structure is kept minimal and focused on API integration.

Build Output

The project produces:

Executable JAR file

Clean, modular codebase

Public GitHub-ready structure
