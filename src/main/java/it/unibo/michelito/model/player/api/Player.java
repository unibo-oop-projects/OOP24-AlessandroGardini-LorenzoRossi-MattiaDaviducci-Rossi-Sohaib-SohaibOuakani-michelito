package it.unibo.michelito.model.player.api;

import it.unibo.michelito.model.modelutil.Updatable;
import it.unibo.michelito.util.Direction;

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
