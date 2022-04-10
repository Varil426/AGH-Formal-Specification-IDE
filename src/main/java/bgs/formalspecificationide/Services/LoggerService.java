package bgs.formalspecificationide.Services;

import bgs.formalspecificationide.Exceptions.ResourceNotFoundException;
import org.apache.logging.log4j.*;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;

import java.io.IOException;

public class LoggerService {

    // TODO Determine if debug
    private final boolean isDebug = true;

    private final String configPath = "/bgs/formalspecificationide/Configs/log4j.properties";

    private final LoggerContext logger;

    LoggerService() {
        logger = configureLogger();
    }

    private LoggerContext configureLogger() {
        var configAsStream = getClass().getResourceAsStream(configPath);
        if (configAsStream != null) {
            try {
                var source = new ConfigurationSource(configAsStream);
                return Configurator.initialize(null, source);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        throw new RuntimeException();
    }

    public void logDebug(String message, Class<?> type) {
        if (isDebug)
            createLogger(type).debug(message);
    }

    public void logInfo(String message, Class<?> type) {
        createLogger(type).info(message);
    }

    public void logWarning(String message, Class<?> type) {
        createLogger(type).warn(message);
    }

    public void logError(String message, Class<?> type) {
        createLogger(type).error(message);
    }

    private Logger createLogger(Class<?> type) {
        return logger.getLogger(type);
    }
}
