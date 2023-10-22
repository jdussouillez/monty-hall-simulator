package com.github.jdussouillez.montyhallsim.runner;

import com.github.jdussouillez.montyhallsim.bean.CarDoorStrategy;
import com.github.jdussouillez.montyhallsim.bean.Game;
import com.github.jdussouillez.montyhallsim.bean.PlayerStrategy;
import lombok.RequiredArgsConstructor;

/**
 * Simulation runner
 */
@RequiredArgsConstructor
abstract class Runner {

    /**
     * Car door strategy
     */
    protected final CarDoorStrategy carDoorStrategy;

    /**
     * Player strategy
     */
    protected final PlayerStrategy playerStrategy;

    /**
     * Number of games to play
     */
    protected final int nbGames;

    /**
     * Run the simulations
     *
     * @return The number of car wins
     */
    protected abstract int run();

    /**
     * Play a game
     *
     * @return True if the player won the cart
     */
    protected boolean play() {
        return new Game(carDoorStrategy, playerStrategy).play();
    }
}
