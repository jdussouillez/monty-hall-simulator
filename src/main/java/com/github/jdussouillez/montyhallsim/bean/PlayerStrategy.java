package com.github.jdussouillez.montyhallsim.bean;

/**
 * Player strategy
 */
public interface PlayerStrategy {

    /**
     * Get the first door number to open
     *
     * @return The first door number to open
     */
    public int firstDoor();

    /**
     * Player will switch door
     *
     * @return True if the player will switch door
     */
    public boolean switchDoor();
}
