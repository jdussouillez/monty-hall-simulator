package com.github.jdussouillez.montyhallsim.bean;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

/**
 * Tests of {@link com.github.jdussouillez.montyhallsim.bean.PlayerSwitchStrategy}
 */
public class PlayerSwitchStrategyTest {

    /*
     * Random
     */
    @RepeatedTest(1_000)
    public void testRandom() {
        var strategy = new PlayerSwitchStrategy.Random();
        strategy.switchDoor();
    }

    /*
     * Fixed
     */
    @Test
    public void testFixed() {
        assertTrue(new PlayerSwitchStrategy.Fixed(true).switchDoor());
        assertFalse(new PlayerSwitchStrategy.Fixed(false).switchDoor());
    }
}
