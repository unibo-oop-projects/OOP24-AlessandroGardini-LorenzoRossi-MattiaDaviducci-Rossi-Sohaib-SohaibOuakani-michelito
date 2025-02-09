package it.unibo.michelito.model.player.api;

import it.unibo.michelito.model.bomb.api.BombType;

/**
 * Interface that makes the {@link Player} modifiable without giving the ability to update the {@link Player} in the Model.
 */
public interface ModifiablePlayer {
    /**
     * Increases the number of placeable {Bomb} by the {@link Player}.
     * @param amount the number of bombs that {@link Player} can place more
     */
    void setBombLimit(int amount);

    /**
     * Increases the speed of the {@link Player}.
     * @param speedIncrease the amount of increase to speed
     */
    void setSpeed(double speedIncrease);

    /**
     * Method that return the current speed of the {@link ModifiablePlayer}.
     * @return the speed
     */
    double getSpeed();

    /**
     * Method that return the current speed of the {@link ModifiablePlayer}.
     * @return {@link it.unibo.michelito.model.bomb.api.Bomb} limit
     */
    int getBombLimit();

    /**
     * Changes the {@link BombType} of the {@link it.unibo.michelito.model.bomb.api.Bomb} deployed by the {@link Player}.
     * @param type the new {@link BombType}
     */
    void setBombType(BombType type);

    /**
     * Method that returns the {@link BombType} of the next bomb placed by {@link ModifiablePlayer}.
     * @return the {@link BombType}
     */
    BombType getBombType();
}
