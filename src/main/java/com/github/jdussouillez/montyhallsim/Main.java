package com.github.jdussouillez.montyhallsim;

import com.github.jdussouillez.montyhallsim.bean.CarDoorStrategy;
import com.github.jdussouillez.montyhallsim.bean.PlayerStrategy;
import com.github.jdussouillez.montyhallsim.runner.ThreadRunner;
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
        var nbSimulations = 10_000;
        var runner = new ThreadRunner(
            new CarDoorStrategy.Random(),
            new PlayerStrategy() {
                @Override
                public int firstDoor() {
                    return 0;
                }

                @Override
                public boolean switchDoor() {
                    return true;
                }
            },
            nbSimulations,
            16
        );
        var nbCarWins = runner.run();
        double winPercentage = 0;
        if (nbCarWins > 0) {
            winPercentage = ((double) nbCarWins / nbSimulations) * 100;
        }
        Loggers.MAIN.info(
            "Simulations completed: {} simulations run, {} cars won ({}%)",
            nbSimulations,
            nbCarWins,
            winPercentage
        );
    }
}
