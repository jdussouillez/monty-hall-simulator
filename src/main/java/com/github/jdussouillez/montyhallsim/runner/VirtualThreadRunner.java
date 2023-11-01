package com.github.jdussouillez.montyhallsim.runner;

import com.github.jdussouillez.montyhallsim.Loggers;
import com.github.jdussouillez.montyhallsim.bean.DoorStrategy;
import com.github.jdussouillez.montyhallsim.bean.SwitchStrategy;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Simulation virtual thread runner
 */
public class VirtualThreadRunner extends Runner {

    /**
     * Execution time (in milliseconds)
     */
    protected long executionTime;

    /**
     * Constructor
     *
     * @param carDoorStrategy Car door strategy
     * @param playerDoorStrategy Player door strategy
     * @param switchStrategy Switch strategy
     * @param nbGames Number of games
     */
    public VirtualThreadRunner(final DoorStrategy carDoorStrategy, final DoorStrategy playerDoorStrategy,
        final SwitchStrategy switchStrategy, final int nbGames) {
        super(carDoorStrategy, playerDoorStrategy, switchStrategy, nbGames);
    }

    @Override
    public int run() {
        var start = Instant.now();
        try (var executorService = Executors.newVirtualThreadPerTaskExecutor()) {
            var tasks = new ArrayList<Future<Boolean>>();
            for (var i = 0; i < nbGames; i++) {
                tasks.add(executorService.submit(this::play));
            }
            executorService.shutdown();
            var carWins = tasks.stream()
                .mapToInt(task -> {
                    try {
                        return task.get() ? 1 : 0;
                    } catch (InterruptedException | ExecutionException ex) {
                        Loggers.MAIN.error("Simulations interrupted", ex);
                        Thread.currentThread().interrupt();
                        return 0;
                    }
                })
                .sum();
            executionTime = Duration.between(start, Instant.now()).toMillis();
            return carWins;
        }
    }

    @Override
    public long executionTime() {
        return executionTime;
    }

    @Override
    public String toString() {
        return "VirtualThreadRunner";
    }
}
