package com.github.jdussouillez.montyhallsim.bean;

import com.github.jdussouillez.montyhallsim.Constants;
import com.github.jdussouillez.montyhallsim.Loggers;
import java.util.Arrays;

/**
 * Game
 */
public class Game {

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
     * Doors
     */
    protected final Door[] doors;

    /**
     * Door number selected by the player
     */
    protected int playerDoor;

    /**
     * Player won the car
     */
    protected boolean wonCar;

    /**
     * Create a new game
     *
     * @param carDoorStrategy Car door strategy
     * @param playerDoorStrategy Player door strategy
     * @param switchStrategy Switch strategy
     */
    public Game(final DoorStrategy carDoorStrategy, final DoorStrategy playerDoorStrategy,
        final SwitchStrategy switchStrategy) {
        this.carDoorStrategy = carDoorStrategy;
        this.playerDoorStrategy = playerDoorStrategy;
        this.switchStrategy = switchStrategy;
        this.doors = initDoors(carDoorStrategy);
    }

    @Override
    public String toString() {
        return String.format("Doors=%s | wonCar=%s", Arrays.toString(doors), wonCar);
    }

    /**
     * Play the game
     *
     * @return True if the player won the car, false otherwise
     */
    public boolean play() {
        choose(playerDoorStrategy.getDoor());
        openNoCar();
        if (switchStrategy.switchDoor()) {
            switchDoor();
        } else {
            keep();
        }
        reveal();
        return wonCar;
    }

    /**
     * Choose a door
     *
     * @param door Door number
     */
    protected void choose(final int door) {
        Loggers.MAIN.trace("Player chooses door {}", door);
        playerDoor = door;
    }

    /**
     * Keep the selected door
     */
    protected void keep() {
        Loggers.MAIN.trace("Player keeps door {}", playerDoor);
    }

    /**
     * Switch player door
     */
    protected void switchDoor() {
        Loggers.MAIN.trace("Player switches door");
        var door = Arrays.stream(doors)
            .filter(d -> !d.isOpen())
            .filter(d -> d.number() != playerDoor)
            .map(Door::number)
            .findAny()
            .orElseThrow(() -> new IllegalStateException("Unable to find a closed door"));
        choose(door);
    }

    /**
     * Open a door without car
     *
     * @return Opened door number
     */
    protected int openNoCar() {
        var door = Arrays.stream(doors)
            .filter(d -> !d.isOpen())
            .filter(d -> !d.car())
            .filter(d -> d.number() != playerDoor)
            .map(Door::number)
            .findAny()
            .orElseThrow(() -> new IllegalStateException("Unable to find a closed door without car"));
        open(door);
        return door;
    }

    /**
     * Open the last goat door
     *
     * @return The last goat door number
     */
    protected int openLastGoatDoor() {
        var door = Arrays.stream(doors)
            .filter(d -> !d.isOpen())
            .filter(d -> !d.car())
            .map(Door::number)
            .findAny()
            .orElseThrow(() -> new IllegalStateException("Unable to find the last goat door to open"));
        open(door);
        return door;
    }

    /**
     * Open the last goat door
     *
     * @return The last goat door number
     */
    protected int openLastDoor() {
        var door = Arrays.stream(doors)
            .filter(d -> !d.isOpen())
            .map(Door::number)
            .findAny()
            .orElseThrow(() -> new IllegalStateException("Unable to find the last door to open"));
        open(door);
        return door;
    }

    /**
     * Open a door
     *
     * @param doorNumber Door number
     */
    protected void open(final int doorNumber) {
        var door = doors[doorNumber];
        Loggers.MAIN.trace(
            "Host opens door {}: {}",
            () -> doorNumber,
            () -> door.car() ? "car" : "goat"
        );
        door.open();
    }

    /**
     * Reveal the doors and end the game
     *
     * <p>
     * Open the last goat door
     * </p>
     */
    protected void reveal() {
        openLastGoatDoor();
        openLastDoor();
        wonCar = doors[playerDoor].car();
        if (wonCar) {
            Loggers.MAIN.trace("Player wins the car! :-D");
        } else {
            Loggers.MAIN.trace("Player wins a goat! :-(");
        }
        Loggers.MAIN.debug(this);
    }

    /**
     * Initialize the doors
     *
     * @param carDoorStrategy Car door strategy
     * @return The doors
     */
    protected static Door[] initDoors(final DoorStrategy carDoorStrategy) {
        var doors = new Door[Constants.NB_DOORS];
        var carDoorNumber = carDoorStrategy.getDoor();
        for (int i = 0; i < doors.length; i++) {
            doors[i] = new Door(i, i == carDoorNumber);
        }
        Loggers.MAIN.trace("Doors initialized with strategy {}, car door is {}", carDoorStrategy, carDoorNumber);
        return doors;
    }
}
