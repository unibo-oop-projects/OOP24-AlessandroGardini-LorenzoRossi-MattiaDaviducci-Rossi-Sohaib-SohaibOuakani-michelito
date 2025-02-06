package it.unibo.michelito.model.gamemanager.api;

import it.unibo.michelito.model.player.api.PlayerCommand;

/**
 * This interface models the GameManager of the Michelito Application.
 * This is the single entry point of the Model.
 */
public interface GameManager {
    void setCommand(PlayerCommand playerCommand);

    //void setCommand(PlayerCommand bombCommand); //TODO: when commandBomb is created

    void update(long currentTime);

    void init();
}
