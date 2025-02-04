package it.unibo.michelito.model.player.api;

import it.unibo.michelito.model.maze.api.Maze;
import it.unibo.michelito.model.modelutil.Updatable;

/**
 * interface for Player.
 */
public interface Player extends Updatable {
    /**
     * set the next command of the player.
     * @param command is the command to be executed.
     */
    void setCommand(PlayerCommand command);

    @Override
    void update(long time, Maze maze);
}
