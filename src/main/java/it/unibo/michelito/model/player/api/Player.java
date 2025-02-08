package it.unibo.michelito.model.player.api;

import it.unibo.michelito.model.modelutil.Updatable;
import it.unibo.michelito.util.Direction;

/**
 * Interface for {@link Player}.
 */
public interface Player extends Updatable {
    /**
     * Increases the number of placeable {Bomb} by the {@link Player}.
     * @param amount the number of bombs that {@link Player} can place more
     */
    void increaseBombLimit(int amount);

    /**
     * Increases the speed of the {@link Player}.
     * @param speedIncrease the amount of increase to speed
     */
    void increaseSpeed(double speedIncrease);

    /**
     * Sets the direction for the next move of the {@link Player}.
     * @param direction is the {@link Direction} of the next move.
     */
    void setDirection(Direction direction);

    /**
     * Makes the player place a {Bomb}.
     */
    void notifyToPlace();
}
