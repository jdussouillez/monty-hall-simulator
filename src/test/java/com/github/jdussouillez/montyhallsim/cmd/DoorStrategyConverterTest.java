package com.github.jdussouillez.montyhallsim.cmd;

import com.github.jdussouillez.montyhallsim.bean.DoorStrategy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 * Tests of {@link com.github.jdussouillez.montyhallsim.cmd.DoorStrategyConverter}
 */
public class DoorStrategyConverterTest {

    @Test
    public void testConvertNull() throws Exception {
        assertTrue(new DoorStrategyConverter().convert(null) instanceof DoorStrategy.Random);
    }

    @Test
    public void testConvertRandom() throws Exception {
        assertTrue(new DoorStrategyConverter().convert("r") instanceof DoorStrategy.Random);
    }

    @Test
    public void testConvertFixedValid() throws Exception {
        var s0 = new DoorStrategyConverter().convert("0");
        assertTrue(s0 instanceof DoorStrategy.Fixed);
        assertEquals(0, s0.getDoor());

        var s1 = new DoorStrategyConverter().convert("1");
        assertTrue(s1 instanceof DoorStrategy.Fixed);
        assertEquals(1, s1.getDoor());

        var s2 = new DoorStrategyConverter().convert("2");
        assertTrue(s2 instanceof DoorStrategy.Fixed);
        assertEquals(2, s2.getDoor());
    }

    @Test
    public void testConvertFixedInvalid() throws Exception {
        var ex = assertThrows(IllegalArgumentException.class, () -> new DoorStrategyConverter().convert("3"));
        assertEquals("Invalid door number", ex.getMessage());
    }
}
