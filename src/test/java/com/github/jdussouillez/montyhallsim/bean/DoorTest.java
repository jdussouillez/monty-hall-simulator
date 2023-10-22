package com.github.jdussouillez.montyhallsim.bean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 * Tests of {@link com.github.jdussouillez.montyhallsim.bean.Door}
 */
public class DoorTest {

    /*
     * Constructor
     */
    @Test
    public void testConstructor() {
        var door = new Door(1, false);
        assertEquals(1, door.number());
        assertFalse(door.isOpen());
    }

    /*
     * open
     */
    @Test
    public void testOpen() {
        var door = new Door(1, true);
        assertFalse(door.isOpen());
        door.open();
        assertTrue(door.isOpen());
    }

    /*
     * toString
     */
    @Test
    public void testToStringClosed() {
        assertEquals("{?}", new Door(0, false).toString());
    }

    @Test
    public void testToStringOpenCar() {
        var door = new Door(0, true);
        door.open();
        assertEquals("{C}", door.toString());
    }

    @Test
    public void testToStringOpenGoat() {
        var door = new Door(0, false);
        door.open();
        assertEquals("{G}", door.toString());
    }
}
