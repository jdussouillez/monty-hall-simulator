package com.github.jdussouillez.montyhallsim.bean;

import lombok.RequiredArgsConstructor;

/**
 * Switch strategy
 */
public interface SwitchStrategy {

    /**
     * Player will switch door
     *
     * @return True if the player will switch door
     */
    public boolean switchDoor();

    /**
     * Random switch strategy
     */
    public static class Random implements SwitchStrategy {

        /**
         * Random generator
         */
        private final java.util.Random randomGenerator = new java.util.Random();

        @Override
        public boolean switchDoor() {
            return randomGenerator.nextBoolean();
        }
    }

    /**
     * Fixed switch strategy
     */
    @RequiredArgsConstructor
    public static class Fixed implements SwitchStrategy {

        /**
         * Switch door
         */
        private final boolean switchDoor;

        @Override
        public boolean switchDoor() {
            return switchDoor;
        }
    }
}
