package com.github.jdussouillez.montyhallsim;

import lombok.experimental.UtilityClass;
import picocli.CommandLine;

/**
 * Main class
 */
@UtilityClass
public final class Main {

    /**
     * Main class
     *
     * @param args Arguments
     */
    public static void main(final String[] args) {
        int exitCode = new CommandLine(new RunSimulationCommand()).execute(args);
        System.exit(exitCode);
    }
}
