package com.github.jdussouillez.montyhallsim.runner;

import com.github.jdussouillez.montyhallsim.bean.DoorStrategy;
import com.github.jdussouillez.montyhallsim.bean.Game;
import com.github.jdussouillez.montyhallsim.bean.SwitchStrategy;
import lombok.RequiredArgsConstructor;

/**
 * Simulation runner
 */
@RequiredArgsConstructor
public abstract class Runner {

    /**
     * Car door strategy
     */
    protected final DoorStrategy carDoorStrategy;

    /**
     * Player door strategy
     */
    protected final DoorStrategy playerDoorStrategy;

    /**
     * Switch strategy
     */
    protected final SwitchStrategy switchStrategy;

    /**
     * Number of games to play
     */
    protected final int nbGames;

    /**
     * Run the simulations
     *
     * @return The number of car wins
     */
    public abstract int run();

    /**
     * Return the total execution time of the simulations
     *
     * @return Execution time in seconds
     */
    public abstract long executionTime();

    /**
     * Play a game
     *
     * @return True if the player won the cart
     */
    protected boolean play() {
        return new Game(carDoorStrategy, playerDoorStrategy, switchStrategy).play();
    }
}
