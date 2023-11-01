package com.github.jdussouillez.montyhallsim.runner;

import com.github.jdussouillez.montyhallsim.Loggers;
import com.github.jdussouillez.montyhallsim.bean.DoorStrategy;
import com.github.jdussouillez.montyhallsim.bean.SwitchStrategy;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Simulation thread runner
 */
abstract class ThreadRunner extends Runner {

    /**
     * Number of car wins
     */
    private final AtomicInteger carWins = new AtomicInteger();

    /**
     * Constructor
     *
     * @param carDoorStrategy Car door strategy
     * @param playerDoorStrategy Player door strategy
     * @param switchStrategy Switch strategy
     * @param nbGames Number of games
     */
    public ThreadRunner(final DoorStrategy carDoorStrategy, final DoorStrategy playerDoorStrategy,
        final SwitchStrategy switchStrategy, final int nbGames) {
        super(carDoorStrategy, playerDoorStrategy, switchStrategy, nbGames);
    }

    @Override
    public int run() {
        try (var executorService = executorService()) {
            for (var i = 0; i < nbGames; i++) {
                executorService.submit(() -> {
                    if (play()) {
                        carWins.incrementAndGet();
                    }
                    return null;
                });
            }
            executorService.shutdown();
            if (!executorService.awaitTermination(1, TimeUnit.HOURS)) {
                executorService.shutdownNow();
            }
            return carWins.get();
        } catch (InterruptedException ex) {
            Loggers.MAIN.error("Simulations interrupted", ex);
            Thread.currentThread().interrupt();
            return -1;
        }
    }

    /**
     * Create the executor service
     *
     * @return The executor service
     */
    protected abstract ExecutorService executorService();
}
