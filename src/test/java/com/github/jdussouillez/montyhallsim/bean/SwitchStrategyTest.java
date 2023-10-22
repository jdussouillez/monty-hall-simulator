package com.github.jdussouillez.montyhallsim.bean;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

/**
 * Tests of {@link com.github.jdussouillez.montyhallsim.bean.SwitchStrategy}
 */
public class SwitchStrategyTest {

    /*
     * Random
     */
    @RepeatedTest(1_000)
    public void testRandom() {
        var strategy = new SwitchStrategy.Random();
        strategy.switchDoor();
    }

    /*
     * Fixed
     */
    @Test
    public void testFixed() {
        assertTrue(new SwitchStrategy.Fixed(true).switchDoor());
        assertFalse(new SwitchStrategy.Fixed(false).switchDoor());
    }
}
