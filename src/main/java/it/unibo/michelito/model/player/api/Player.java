package it.unibo.michelito.model.player.api;

import it.unibo.michelito.model.maze.api.Maze;
import it.unibo.michelito.model.modelutil.Updatable;
import it.unibo.michelito.util.Direction;
import it.unibo.michelito.util.Position;

import java.util.Optional;

/**
 * Interface for Player.
 */
public interface Player extends Updatable {
    /**
     * Increases the number of placeable bombs by the player.
     */
    void increaseBombLimit();

    /**
     * Increases the speed of the player.
     */
    void increaseSpeed();

    void setDirection(Direction direction);

    void notifyToPlace();
}
