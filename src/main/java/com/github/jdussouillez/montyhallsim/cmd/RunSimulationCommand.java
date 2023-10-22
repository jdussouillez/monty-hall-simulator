package com.github.jdussouillez.montyhallsim.cmd;

import com.github.jdussouillez.montyhallsim.Loggers;
import com.github.jdussouillez.montyhallsim.bean.DoorStrategy;
import com.github.jdussouillez.montyhallsim.bean.SwitchStrategy;
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
        description = "Number of games. Default is 500k."
    )
    private int nbGames = 500_000;

    /**
     * Car door strategy
     */
    @Option(
        names = {"-cds", "--car-door-strategy"},
        paramLabel = "STRATEGY",
        description = "Car door strategy. 0, 1 or 2 for fixed car door. \"r\" for random. Default is random.",
        converter = DoorStrategyConverter.class
    )
    private DoorStrategy carDoorStrategy = new DoorStrategy.Random();

    /**
     * Player door strategy
     */
    @Option(
        names = {"-pds", "--player-door-strategy"},
        paramLabel = "STRATEGY",
        description = "Player door strategy. 0, 1 or 2 for fixed car door. \"r\" for random. Default is random.",
        converter = DoorStrategyConverter.class
    )
    private DoorStrategy playerDoorStrategy = new DoorStrategy.Random();

    /**
     * Switch strategy
     */
    @Option(
        names = {"-ss", "--switch-strategy"},
        paramLabel = "STRATEGY",
        description = "Switch strategy. \"n\" for never, \"a\" for always, \"r\" for random. Default is random.",
        converter = SwitchStrategyConverter.class
    )
    private SwitchStrategy switchStrategy = new SwitchStrategy.Random();

    @Override
    public Integer call() throws Exception {
        // TODO: handle virtual thread
        if (nbThreads == 0) {
            nbThreads = Runtime.getRuntime().availableProcessors();
        }
        var runner = new ThreadRunner(carDoorStrategy, playerDoorStrategy, switchStrategy, nbGames, nbThreads);
        var nbWins = runner.run();
        double winPercent = 0;
        if (nbWins > 0) {
            winPercent = ((double) nbWins / nbGames) * 100;
        }
        Loggers.MAIN.info(
            "Simulations completed: {} simulations run in {}ms, {} cars won ({}%)",
            nbGames,
            runner.executionTime(),
            nbWins,
            winPercent
        );
        return 0;
    }
}
