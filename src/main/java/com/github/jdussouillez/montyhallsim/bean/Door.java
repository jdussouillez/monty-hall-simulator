package com.github.jdussouillez.montyhallsim.bean;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Door
 */
@RequiredArgsConstructor
public class Door {

    /**
     * Door number
     */
    @Getter
    protected final int number;

    /**
     * Door has car prize
     */
    @Getter
    protected final boolean car;

    /**
     * Door was opened
     */
    protected boolean open;

    /**
     * Test if the door is open
     *
     * @return True if the door is open
     */
    public boolean isOpen() {
        return open;
    }

    /**
     * Open door
     */
    public void open() {
        open = true;
    }

    @Override
    public String toString() {
        var s = "?";
        if (open) {
            s = car ? "C" : "G";
        }
        return String.format("{%s}", s);
    }
}
