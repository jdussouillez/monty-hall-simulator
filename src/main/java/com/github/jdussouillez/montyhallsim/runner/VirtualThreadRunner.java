package com.github.jdussouillez.montyhallsim.runner;

import com.github.jdussouillez.montyhallsim.bean.DoorStrategy;
import com.github.jdussouillez.montyhallsim.bean.SwitchStrategy;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Simulation virtual thread runner
 */
public class VirtualThreadRunner extends ThreadRunner {

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
    protected ExecutorService executorService() {
        return Executors.newVirtualThreadPerTaskExecutor();
    }
}
