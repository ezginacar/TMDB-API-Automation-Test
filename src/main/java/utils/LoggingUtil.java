package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collection;
import java.util.Map;

public class LoggingUtil {
    private static final Logger logger = LogManager.getLogger(LoggingUtil.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private static final String ANSI_RESET = "\u001B[0m";

    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_PURPLE_BACKGROUND ="\033[45m";




    public static void logRequestBodyIfExists(Object body) {
        if (body == null) {
            logger.info(ANSI_YELLOW + "Request body is null." + ANSI_RESET);
            return;
        }

        if (body instanceof Map) {
            if (((Map<?, ?>) body).isEmpty()) {
                logger.info(ANSI_YELLOW+"Request body is an empty Map."+ANSI_RESET);
                return;
            }
        } else if (body instanceof Collection) {
            if (((Collection<?>) body).isEmpty()) {
                logger.info(ANSI_YELLOW+ "Request body is an empty Collection."+ ANSI_RESET);
                return;
            }
        }

        try {
            String bodyAsJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(body);
            logger.info("Request Body: {}", ANSI_YELLOW + bodyAsJson + ANSI_RESET);
        } catch (JsonProcessingException e) {
            logger.error("Failed to log request body: {}", ANSI_RED+e.getMessage() + ANSI_RESET);
        }
    }


    public static void logResponseDetails(int statusCode, String responseBody) {
        logger.info("Response Status Code: {}", ANSI_YELLOW +statusCode + ANSI_RESET);
        try {
            Object json = objectMapper.readValue(responseBody, Object.class);
            String prettyPrintedJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
            logger.info("Response Body (Pretty Printed): \n{}", ANSI_YELLOW+ prettyPrintedJson + ANSI_RESET);
        } catch (Exception e) {

            logger.warn(ANSI_RED + "Failed to pretty print JSON response. Logging raw response body." + ANSI_RESET);
            logger.info(ANSI_RED+"Response Body: {}", responseBody + ANSI_RESET);
        }
    }

    public static void logTestStarting(String scenarioName){
        logger.info(ANSI_PURPLE_BACKGROUND + String.format("The test, '%s' is starting...", scenarioName) + ANSI_RESET );
    }
}

