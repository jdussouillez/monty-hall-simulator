package com.github.jdussouillez.montyhallsim.cmd;

import com.github.jdussouillez.montyhallsim.bean.SwitchStrategy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 * Tests of {@link com.github.jdussouillez.montyhallsim.cmd.SwitchStrategyConverter}
 */
public class SwitchStrategyConverterTest {

    @Test
    public void testConvertNull() throws Exception {
        assertTrue(new SwitchStrategyConverter().convert(null) instanceof SwitchStrategy.Random);
    }

    @Test
    public void testConvertRandom() throws Exception {
        assertTrue(new SwitchStrategyConverter().convert("r") instanceof SwitchStrategy.Random);
    }

    @Test
    public void testConvertFixedAlways() throws Exception {
        var strategy = new SwitchStrategyConverter().convert("a");
        assertTrue(strategy instanceof SwitchStrategy.Fixed);
        assertTrue(strategy.switchDoor());
    }

    @Test
    public void testConvertFixedNever() throws Exception {
        var strategy = new SwitchStrategyConverter().convert("n");
        assertTrue(strategy instanceof SwitchStrategy.Fixed);
        assertFalse(strategy.switchDoor());
    }

    @Test
    public void testConvertFixedInvalid() throws Exception {
        var ex = assertThrows(IllegalArgumentException.class, () -> new SwitchStrategyConverter().convert("x"));
        assertEquals("Invalid switch strategy", ex.getMessage());
    }
}
