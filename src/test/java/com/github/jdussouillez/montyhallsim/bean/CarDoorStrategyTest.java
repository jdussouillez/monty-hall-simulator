package com.github.jdussouillez.montyhallsim.bean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * Tests of {@link com.github.jdussouillez.montyhallsim.bean.CarDoorStrategy}
 */
public class CarDoorStrategyTest {

    /*
     * Random
     */
    @RepeatedTest(1_000)
    public void testRandom() {
        var strategy = new CarDoorStrategy.Random();
        var door = strategy.getCarDoor();
        assertTrue(door >= 0 && door <= 2);
    }

    /*
     * Fixed
     */
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    public void testFixed() {
        assertEquals(1, new CarDoorStrategy.Fixed(1).getCarDoor());
    }
}
