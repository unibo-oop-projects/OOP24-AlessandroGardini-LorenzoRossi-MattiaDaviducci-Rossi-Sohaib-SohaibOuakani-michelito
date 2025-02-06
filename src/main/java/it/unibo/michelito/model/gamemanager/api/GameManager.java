package it.unibo.michelito.model.gamemanager.api;

import it.unibo.michelito.model.player.api.PlayerCommand;
import it.unibo.michelito.model.player.api.Player;

/**
 * This interface models the GameManager of the Michelito Application.
 * This is the single entry point of the Model.
 */
public interface GameManager {
    /**
     * Set the current command to {@link Player}.
     * If a Command is present this method should be called before update.
     *
     * @param playerCommand the command to be executed on the {@link Player}.
     */
    void setCommand(PlayerCommand playerCommand);

    /**
     * Update the state of the game.
     *
     * @param currentTime is the current currentTime in millisecond.
     */
    void update(long currentTime);
}
