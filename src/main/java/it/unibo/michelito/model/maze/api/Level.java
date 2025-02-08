package it.unibo.michelito.model.maze.api;

import it.unibo.michelito.controller.playercommand.api.PlayerCommand;
import it.unibo.michelito.model.modelutil.Updatable;
import it.unibo.michelito.util.GameObject;

import java.util.Set;

/**
 * Interface that represents the Maze as a level.
 */
public interface Level {
    /**
     * Updates all {@link Updatable} and applies the player's command.
     *
     * @param currentTime the current time in milliseconds, used for game state updates.
     */
    void update(long currentTime);

    /**
     * Set the next command to apply.
     *
     * @param playerCommand to be saved.
     */
    void setCommand(PlayerCommand playerCommand);

    /**
     * Getter for the Set of {@link GameObject} currently in the {@link Maze}.
     *
     * @return the Set of {@link GameObject} in the {@link Maze}.
     */
    Set<GameObject> getGameObjects();

    /**
     * Getter for the level state won.
     *
     * @return true if michelito has won the level.
     */
    boolean isWon();

    /**
     * Getter for the level state lost.
     *
     * @return true if michelito has lost the level.
     */
    boolean isLost();
}
