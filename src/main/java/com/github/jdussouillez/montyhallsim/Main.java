package com.github.jdussouillez.montyhallsim;

import com.github.jdussouillez.montyhallsim.bean.CarDoorStrategy;
import com.github.jdussouillez.montyhallsim.bean.Game;
import com.github.jdussouillez.montyhallsim.bean.PlayerStrategy;
import lombok.experimental.UtilityClass;

/**
 * Main class
 */
@UtilityClass
public final class Main {

    /**
     * Main class
     *
     * @param args Arguments
     */
    public static void main(final String[] args) {
        var g = new Game(new CarDoorStrategy.Fixed(0), new PlayerStrategy() {
            @Override
            public int firstDoor() {
                return 0;
            }

            @Override
            public boolean switchDoor() {
                return true;
            }
        });
        g.play();
        Loggers.MAIN.info(g);
    }
}
