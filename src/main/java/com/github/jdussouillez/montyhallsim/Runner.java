package com.github.jdussouillez.montyhallsim;

import com.github.jdussouillez.montyhallsim.bean.CarDoorStrategy;
import com.github.jdussouillez.montyhallsim.bean.Game;
import com.github.jdussouillez.montyhallsim.bean.PlayerStrategy;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import lombok.RequiredArgsConstructor;

/**
 * Simulation runner
 */
@RequiredArgsConstructor
public class Runner {

    /**
     * Car door strategy
     */
    protected final CarDoorStrategy carDoorStrategy;

    /**
     * Player strategy
     */
    protected final PlayerStrategy playerStrategy;

    /**
     * Number of games to play
     */
    protected final int nbGames;

    /**
     * Number of threads
     */
    protected final int nbThreads;

    /**
     * Run the simulations
     *
     * @return The number of car wins
     */
    public int run() {
        var executorService = Executors.newFixedThreadPool(nbThreads);
        var tasks = new ArrayList<Future<Boolean>>();
        for (var i = 0; i < nbGames; i++) {
            tasks.add(executorService.submit(() -> new Game(carDoorStrategy, playerStrategy).play()));
        }
        executorService.shutdown();
        return tasks.stream()
            .mapToInt(task -> {
                try {
                    return task.get() ? 1 : 0;
                } catch (InterruptedException | ExecutionException ex) {
                    Loggers.MAIN.error("Simulations interrupted", ex);
                    Thread.currentThread().interrupt();
                    return 0;
                }
            })
            .sum();
    }
}
