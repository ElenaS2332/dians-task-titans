package com.example.demo.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WineryLogger {
    private static final Logger logger = LoggerFactory.getLogger(WineryLogger.class);
    private static final WineryLogger instance = new WineryLogger();

    private WineryLogger() {}

    public static WineryLogger getInstance() {
        return instance;
    }

    public void logRegistrationError(String username, String error) {
        logger.error("Registration error for user {}: {}", username, error);
    }

    public void logLoginError(String username, String error) {
        logger.error("Login error for user {}: {}", username, error);
    }

    public void logCommentError(String username, Long wineryId, String error) {
        logger.error("Error while leaving comment for user {} on winery {}: {}", username, wineryId, error);
    }
}
