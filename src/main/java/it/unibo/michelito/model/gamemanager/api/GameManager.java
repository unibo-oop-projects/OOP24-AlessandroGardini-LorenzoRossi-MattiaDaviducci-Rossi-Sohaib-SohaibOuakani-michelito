package it.unibo.michelito.model.gamemanager.api;

import it.unibo.michelito.controller.palyercommand.api.PlayerCommand;
import it.unibo.michelito.model.player.api.Player;

/**
 * Represents the Game Manager of the Michelito Application.
 * This interface defines the core logic for controlling the game state, processing player commands,
 * and updating the game environment. It serves as the primary entry point of the model layer.
 */
public interface GameManager {

    /**
     * Sets the command to be executed on the {@link Player}.
     * This method should be called before invoking {@link #update(long)} to ensure
     * that the player's action is processed in the next game update cycle.
     *
     * @param playerCommand the command to be executed on the {@link Player}.
     */
    void setCommand(PlayerCommand playerCommand);

    /**
     * Updates the state of the game, applying the player's command and progressing
     * the game logic based on the current time.
     *
     * @param currentTime the current time in milliseconds, used for game state updates.
     */
    void update(long currentTime);
}
