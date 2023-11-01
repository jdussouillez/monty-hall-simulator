package com.github.jdussouillez.montyhallsim.runner;

import com.github.jdussouillez.montyhallsim.bean.DoorStrategy;
import com.github.jdussouillez.montyhallsim.bean.SwitchStrategy;
import java.util.stream.IntStream;

/**
 * Simulation sequential runner
 */
public class SeqRunner extends Runner {

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
    public SeqRunner(final DoorStrategy carDoorStrategy, final DoorStrategy playerDoorStrategy,
        final SwitchStrategy switchStrategy, final int nbGames) {
        super(carDoorStrategy, playerDoorStrategy, switchStrategy, nbGames);
    }

    @Override
    public int run() {
        return IntStream.range(0, nbGames)
            .map(i -> play() ? 1 : 0)
            .sum();
    }
}
