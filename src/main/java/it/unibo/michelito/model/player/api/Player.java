package it.unibo.michelito.model.player.api;

import it.unibo.michelito.model.modelutil.Updatable;
import it.unibo.michelito.util.Direction;

/**
 * Interface for {@link Player}.
 */
public interface Player extends Updatable {
    /**
     * Sets the direction for the next move of the {@link Player}.
     * @param direction is the {@link Direction} of the next move.
     */
    void setDirection(Direction direction);

    /**
     * Makes the player place a {@link it.unibo.michelito.model.bomb.api.Bomb}.
     */
    void notifyToPlace();
}
