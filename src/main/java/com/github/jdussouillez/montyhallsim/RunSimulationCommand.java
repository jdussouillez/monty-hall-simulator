package com.github.jdussouillez.montyhallsim;

import com.github.jdussouillez.montyhallsim.bean.DoorStrategy;
import com.github.jdussouillez.montyhallsim.bean.PlayerSwitchStrategy;
import com.github.jdussouillez.montyhallsim.runner.ThreadRunner;
import java.util.concurrent.Callable;
import picocli.CommandLine.Option;

/**
 * Run simulation command
 */
public class RunSimulationCommand implements Callable<Integer> {

    /**
     * Thread type
     */
    @Option(
        names = {"-t", "--thread-type"},
        paramLabel = "THREAD_TYPE",
        description = "Thread type (\"os\" or \"virtual\"). Default is \"os\"."
    )
    private String threadType = "os";

    /**
     * Number of OS threads
     */
    @Option(
        names = {"-n", "--threads"},
        paramLabel = "THREADS",
        description = "Number of threads. By default, use the available processors in the JVM."
            + " Only applicable when thread type is \"os\"."
    )
    private int nbThreads;

    /**
     * Number of games
     */
    @Option(
        names = {"-g", "--games"},
        paramLabel = "GAMES",
        description = "Number of games. Default is 10'000."
    )
    private int nbGames = 10_000;

    /**
     * Car door strategy
     */
    @Option(
        names = {"-cds", "--car-door-strategy"},
        paramLabel = "STRATEGY",
        description = "Car door strategy. 0, 1 or 2 for fixed car door. \"r\" for random. Default is random."
    )
    private String cds = "r";

    /**
     * Player door strategy
     */
    @Option(
        names = {"-pds", "--player-door-strategy"},
        paramLabel = "STRATEGY",
        description = "Player door strategy. 0, 1 or 2 for fixed car door. \"r\" for random. Default is random."
    )
    private String pds = "r";

    /**
     * Player switch strategy
     */
    @Option(
        names = {"-pss", "--player-switch-strategy"},
        paramLabel = "STRATEGY",
        description = "Player switch strategy. \"n\" for never, \"a\" for always, \"r\" for random. Default is random."
    )
    private String pss = "r";

    @Override
    public Integer call() throws Exception {
        var carDoorStrategy = parseDoorStrategy(cds);
        var playerDoorStrategy = parseDoorStrategy(pds);
        var playerSwitchStrategy = parseSwitchStrategy(pss);
        // TODO: handle virtual thread
        if (nbThreads == 0) {
            nbThreads = Runtime.getRuntime().availableProcessors();
        }
        var runner = new ThreadRunner(carDoorStrategy, playerDoorStrategy, playerSwitchStrategy, nbGames, nbThreads);
        var nbWins = runner.run();
        double winPercent = 0;
        if (nbWins > 0) {
            winPercent = ((double) nbWins / nbGames) * 100;
        }
        Loggers.MAIN.info("Simulations completed: {} simulations run, {} cars won ({}%)", nbGames, nbWins, winPercent);
        return 0;
    }

    /**
     * Parse a door strategy
     *
     * @param arg Command line argument
     * @return Door strategy
     */
    protected static DoorStrategy parseDoorStrategy(final String arg) {
        return arg.equals("r")
            ? new DoorStrategy.Random()
            : new DoorStrategy.Fixed(Integer.parseInt(arg));
    }

    /**
     * Parse a switch strategy
     *
     * @param arg Command line argument
     * @return Switch strategy
     */
    protected static PlayerSwitchStrategy parseSwitchStrategy(final String arg) {
        return switch (arg) {
            case "r" -> new PlayerSwitchStrategy.Random();
            case "a" -> new PlayerSwitchStrategy.Fixed(true);
            default -> new PlayerSwitchStrategy.Fixed(false);
        };
    }
}
