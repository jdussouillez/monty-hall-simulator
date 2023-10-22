package com.github.jdussouillez.montyhallsim.bean;

import com.github.jdussouillez.montyhallsim.Constants;
import lombok.RequiredArgsConstructor;

/**
 * Door strategy
 */
@FunctionalInterface
public interface DoorStrategy {

    /**
     * Get the door number
     *
     * @return The door number
     */
    int getDoor();

    /**
     * Random door strategy
     */
    public static class Random implements DoorStrategy {

        /**
         * Random generator
         */
        private final java.util.Random randomGenerator = new java.util.Random();

        @Override
        public int getDoor() {
            return randomGenerator.nextInt(Constants.NB_DOORS);
        }
    }

    /**
     * Fixed door strategy
     */
    @RequiredArgsConstructor
    public static class Fixed implements DoorStrategy {

        /**
         * Door number
         */
        private final int door;

        @Override
        public int getDoor() {
            return door;
        }
    }
}
