package it.unibo.michelito.model.player.api;

import it.unibo.michelito.model.maze.api.Maze;
import it.unibo.michelito.model.modelutil.Updatable;
import it.unibo.michelito.util.Position;

import java.util.Optional;

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
     * Gets the next command for the player, if there
     * @return Return the next command.
     */
    Optional<PlayerCommand> getCommand();

    /**
     * Get the last time the player received an update.
     * @return the last time the player is updated.
     */
    long getLastUpdateTime();

    /**
     * Increases the number of placeable bombs by the player.
     */
    void increaseBombLimit();

    /**
     * Increases the speed of the player.
     */
    void increaseSpeed();

    /**
     * Gets the current speed of the player.
     * @return The current speed of the player.
     */
    double getSpeed();

    /**
     * Gets the current limit of placeable bombs by the player.
     * @return Max number of bombs placeable.
     */
    int getBombLimit();

    /**
     * Sets a new psoition for the player.
     * @param newPosition is the new position of the player.
     */
    void setPosition(Position newPosition);

    /**
     * Method to make the player place a Bomb.
     * @param maze where the bomb is placed.
     */
    void placeBomb(Maze maze);
}
