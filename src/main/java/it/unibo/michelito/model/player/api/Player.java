package it.unibo.michelito.model.player.api;

import it.unibo.michelito.model.maze.api.Maze;
import it.unibo.michelito.model.modelutil.Updatable;
import it.unibo.michelito.util.Position;

/**
 * interface for Player.
 */
public interface Player extends Updatable {
    /**
     * set the next command of the player.
     * @param command is the command to be executed.
     */
    void setCommand(PlayerCommand command);

    public long getLastUpdateTime();

    public void increaseBombLimit();

    public void increaseSpeed();

    public void setPosition(final Position newPosition);

    public void placeBomb(final Maze maze);
}
