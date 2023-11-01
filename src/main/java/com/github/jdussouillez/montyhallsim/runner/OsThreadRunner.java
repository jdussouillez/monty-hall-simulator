package com.github.jdussouillez.montyhallsim.runner;

import com.github.jdussouillez.montyhallsim.bean.DoorStrategy;
import com.github.jdussouillez.montyhallsim.bean.SwitchStrategy;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Simulation OS thread runner
 */
public class OsThreadRunner extends ThreadRunner {

    /**
     * Number of threads
     */
    protected final int nbThreads;

    /**
     * Constructor
     *
     * @param carDoorStrategy Car door strategy
     * @param playerDoorStrategy Player door strategy
     * @param switchStrategy Switch strategy
     * @param nbGames Number of games
     * @param nbThreads Number of threads
     */
    public OsThreadRunner(final DoorStrategy carDoorStrategy, final DoorStrategy playerDoorStrategy,
        final SwitchStrategy switchStrategy, final int nbGames, final int nbThreads) {
        super(carDoorStrategy, playerDoorStrategy, switchStrategy, nbGames);
        this.nbThreads = nbThreads;
    }

    @Override
    public String toString() {
        return String.format("ThreadRunner{%d}", nbThreads);
    }

    @Override
    protected ExecutorService executorService() {
        return Executors.newFixedThreadPool(nbThreads);
    }
}
