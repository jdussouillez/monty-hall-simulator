package com.github.jdussouillez.montyhallsim.bean;

import com.github.jdussouillez.montyhallsim.Constants;
import lombok.RequiredArgsConstructor;

/**
 * Car door choosing strategy
 */
@FunctionalInterface
public interface CarDoorStrategy {

    /**
     * Get the car door number
     *
     * @return The car door number
     */
    public int getCarDoor();

    /**
     * Random door strategy
     */
    public static class Random implements CarDoorStrategy {

        /**
         * Random generator
         */
        private final java.util.Random randomGenerator = new java.util.Random();

        @Override
        public int getCarDoor() {
            return randomGenerator.nextInt(Constants.NB_DOORS);
        }
    }

    /**
     * Fixed door strategy
     *
     * <p>
     * Define a fixed car door number for each game
     * </p>
     */
    @RequiredArgsConstructor
    public static class Fixed implements CarDoorStrategy {

        /**
         * Door number
         */
        private final int door;

        @Override
        public int getCarDoor() {
            return door;
        }
    }
}
