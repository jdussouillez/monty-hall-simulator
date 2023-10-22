package com.github.jdussouillez.montyhallsim.bean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

/**
 * Tests of {@link com.github.jdussouillez.montyhallsim.bean.DoorStrategy}
 */
public class DoorStrategyTest {

    /*
     * Random
     */
    @RepeatedTest(1_000)
    public void testRandom() {
        var strategy = new DoorStrategy.Random();
        var door = strategy.getDoor();
        assertTrue(door >= 0 && door <= 2);
    }

    /*
     * Fixed
     */
    @Test
    public void testFixed() {
        assertEquals(0, new DoorStrategy.Fixed(0).getDoor());
        assertEquals(1, new DoorStrategy.Fixed(1).getDoor());
        assertEquals(2, new DoorStrategy.Fixed(2).getDoor());
    }
}
