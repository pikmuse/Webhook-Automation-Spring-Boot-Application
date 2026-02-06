package com.webhook.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SqlQueryService {

    private static final Logger log = LoggerFactory.getLogger(SqlQueryService.class);

    /**
     * Step 4: Determine SQL question path based on regNo parity
     * Step 5: Generate SQL query for the determined path
     */
    public String generateSqlQuery(String regNo) {
        log.info("Step 4: Determining SQL question path based on regNo parity");

        int lastTwoDigits = extractLastTwoDigits(regNo);
        boolean isOdd = lastTwoDigits % 2 != 0;

        String questionPath = isOdd ? "Question 1 (Odd)" : "Question 2 (Even)";
        log.info("regNo: {}, Last two digits: {}, Path: {}", regNo, lastTwoDigits, questionPath);

        String sqlQuery;
        if (isOdd) {
            // Question 1 - Odd path
            sqlQuery = "SELECT department, COUNT(*) as employee_count FROM employees GROUP BY department ORDER BY employee_count DESC;";
            log.info("Step 5: Question 1 (Odd) selected. Generated SQL query");
        } else {
            // Question 2 - Even path
            sqlQuery = "SELECT department, COUNT(*) FROM employees GROUP BY department;";
            log.info("Step 5: Question 2 (Even) selected. Generated SQL query");
        }

        log.debug("Final SQL Query: {}", sqlQuery);
        return sqlQuery;
    }

    /**
     * Extract last two digits from regNo
     */
    private int extractLastTwoDigits(String regNo) {
        if (regNo == null || regNo.length() < 2) {
            log.error("Invalid regNo format: {}", regNo);
            throw new IllegalArgumentException("Invalid regNo format");
        }

        String lastTwo = regNo.substring(regNo.length() - 2);
        try {
            return Integer.parseInt(lastTwo);
        } catch (NumberFormatException e) {
            log.error("Last two characters of regNo are not numeric: {}", lastTwo);
            throw new IllegalArgumentException("Last two characters of regNo must be numeric", e);
        }
    }
}
