package it.unibo.michelito.model.player.api;

import it.unibo.michelito.model.maze.api.Maze;
import it.unibo.michelito.model.modelutil.Updatable;
import it.unibo.michelito.util.Position;

/**
 * Interface for Player.
 */
public interface Player extends Updatable {
    /**
     * Set the next command of the player.
     * @param command is the command to be executed.
     */
    void setCommand(PlayerCommand command);

    /**
     * Increases the number of placeable bombs by the player.
     */
    void increaseBombLimit();

    /**
     * Increases the speed of the player.
     */
    void increaseSpeed();

    /**
     * Sets a new position for the player.
     * @param newPosition is the new position of the player.
     */
    void setPosition(Position newPosition);

    /**
     * Method to make the player place a Bomb.
     * @param maze where the bomb is placed.
     */
    void placeBomb(Maze maze);
}
