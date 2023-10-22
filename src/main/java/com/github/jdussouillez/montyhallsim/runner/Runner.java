package com.github.jdussouillez.montyhallsim.runner;

import com.github.jdussouillez.montyhallsim.bean.DoorStrategy;
import com.github.jdussouillez.montyhallsim.bean.Game;
import com.github.jdussouillez.montyhallsim.bean.PlayerSwitchStrategy;
import lombok.RequiredArgsConstructor;

/**
 * Simulation runner
 */
@RequiredArgsConstructor
abstract class Runner {

    /**
     * Car door strategy
     */
    protected final DoorStrategy carDoorStrategy;

    /**
     * Player door strategy
     */
    protected final DoorStrategy playerDoorStrategy;

    /**
     * Player switch strategy
     */
    protected final PlayerSwitchStrategy playerSwitchStrategy;

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
        return new Game(carDoorStrategy, playerDoorStrategy, playerSwitchStrategy).play();
    }
}
