package com.github.jdussouillez.montyhallsim;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Loggers
 */
public final class Loggers {

    /**
     * Main logger
     */
    public static final Logger MAIN = LogManager.getLogger("com.github.jdussouillez.montyhallsim");

    /**
     * Constructor
     */
    private Loggers() {
        // Just to hide the public constructor
    }
}
