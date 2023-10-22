package com.github.jdussouillez.montyhallsim.bean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

/**
 * Tests of {@link com.github.jdussouillez.montyhallsim.bean.Game}
 */
public class GameTest {

    /*
     * play
     */
    @Test
    public void testPlayNoSwitch() {
        assertTrue(spyGame(0, 0, false).play());
        assertTrue(spyGame(1, 1, false).play());
        assertTrue(spyGame(2, 2, false).play());

        assertFalse(spyGame(0, 1, false).play());
        assertFalse(spyGame(0, 2, false).play());
        assertFalse(spyGame(1, 0, false).play());
        assertFalse(spyGame(1, 2, false).play());
        assertFalse(spyGame(2, 0, false).play());
        assertFalse(spyGame(2, 1, false).play());
    }

    @Test
    public void testPlayWithSwitch() {
        assertFalse(spyGame(0, 0, true).play());
        assertFalse(spyGame(1, 1, true).play());
        assertFalse(spyGame(2, 2, true).play());

        assertTrue(spyGame(0, 1, true).play());
        assertTrue(spyGame(0, 2, true).play());
        assertTrue(spyGame(1, 0, true).play());
        assertTrue(spyGame(1, 2, true).play());
        assertTrue(spyGame(2, 0, true).play());
        assertTrue(spyGame(2, 1, true).play());
    }

    /*
     * toString
     */
    @Test
    public void testToString() {
        var game = spyGame(0, 0, false);
        assertEquals("Doors=[{?}, {?}, {?}] | wonCar=false", game.toString());
        game.play();
        assertEquals("Doors=[{C}, {G}, {G}] | wonCar=true", game.toString());
    }

    /**
     * Create a Game instance spy
     *
     * @param carDoor Car door
     * @param firstDoor Player first door
     * @param switchDoor Player will switch
     * @return The spied Game instance
     */
    private static Game spyGame(final int carDoor, final int firstDoor, final boolean switchDoor) {
        var game = new Game(
            new CarDoorStrategy.Fixed(carDoor),
            new PlayerStrategy() {

                @Override
                public int firstDoor() {
                    return firstDoor;
                }

                @Override
                public boolean switchDoor() {
                    return switchDoor;
                }
            });
        return Mockito.spy(game);
    }
}
