package com.github.jdussouillez.montyhallsim.runner;

import com.github.jdussouillez.montyhallsim.Loggers;
import com.github.jdussouillez.montyhallsim.bean.DoorStrategy;
import com.github.jdussouillez.montyhallsim.bean.PlayerSwitchStrategy;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Simulation thread runner
 */
public class ThreadRunner extends Runner {

    /**
     * Number of threads
     */
    protected final int nbThreads;

    /**
     * Constructor
     *
     * @param carDoorStrategy Car door strategy
     * @param playerDoorStrategy Player door strategy
     * @param playerSwitchStrategy Player door strategy
     * @param nbGames Number of games
     * @param nbThreads Number of threads
     */
    public ThreadRunner(final DoorStrategy carDoorStrategy, final DoorStrategy playerDoorStrategy,
        final PlayerSwitchStrategy playerSwitchStrategy, final int nbGames, final int nbThreads) {
        super(carDoorStrategy, playerDoorStrategy, playerSwitchStrategy, nbGames);
        this.nbThreads = nbThreads;
    }

    @Override
    public int run() {
        var executorService = Executors.newFixedThreadPool(nbThreads);
        var tasks = new ArrayList<Future<Boolean>>();
        for (var i = 0; i < nbGames; i++) {
            tasks.add(executorService.submit(this::play));
        }
        executorService.shutdown();
        return tasks.stream()
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
    }
}
