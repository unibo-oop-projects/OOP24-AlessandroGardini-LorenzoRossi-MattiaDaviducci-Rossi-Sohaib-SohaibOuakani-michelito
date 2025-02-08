package it.unibo.michelito.model.gamemanager.api;

import it.unibo.michelito.controller.playercommand.api.PlayerCommand;
import it.unibo.michelito.model.maze.api.Maze;
import it.unibo.michelito.model.player.api.Player;
import it.unibo.michelito.util.GameObject;

import java.util.Set;

/**
 * Represents the Game Manager of the Michelito Application.
 * This interface defines the core logic for controlling the game state
 * ant it is the single entry point of the model.
 * Provides getter od the current state of the game.
 */
public interface GameManager {
    /**
     * Getter for the gameOver state.
     *
     * @return true if the game is lost.
     */
    boolean isGameOver() ;

    /**
     * Getter for the gameWon state.
     *
     * @return true if the game is won.
     */
    boolean isGameWon();

    /**
     * Getter for the Set of {@link GameObject} currently in the {@link Maze}.
     *
     * @return the Set of {@link GameObject} currently in the {@link Maze}.
     */
    Set<GameObject> getObjects();

    /**
     * Getter for remaining lives in the game.
     *
     * @return remaining lives.
     */
    int getRemainingLives();

    /**
     * Sets the command to be executed on the current {@link Player}.
     * This method should be called before invoking {@link #update(long)} to ensure
     * that the player's action is processed in the next game update cycle.
     *
     * @param playerCommand the command to be executed on the {@link Player}.
     */
    void setCommand(PlayerCommand playerCommand);

    /**
     * Updates the state of the game, applying the player's command and updating
     * the current {@link Maze}.
     *
     * @param currentTime the current time in milliseconds, used for game state updates.
     */
    void update(long currentTime);
}
