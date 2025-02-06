package it.unibo.michelito.model.powerups.api;

import it.unibo.michelito.util.Position;

import java.util.Optional;

/**
 * Interface for a factory that creates {@link PowerUp}.
 */
public interface PowerUpFactory {
    /**
     * Method that creates a {@link it.unibo.michelito.model.powerups.impl.SpeedPowerUp}.
     * @param position the {@link Position} of the {@link PowerUp}
     * @return returns the created {@link it.unibo.michelito.model.powerups.impl.SpeedPowerUp}
     */
    PowerUp generateSpeedPowerUp(Position position);

    PowerUp generateBombPowerUp(Position position);

    /**
     * Method that can create a random {@link PowerUp} or not.
     * @param position the {@link Position} where to create the random {@link PowerUp}.
     * @return an {@link Optional} that contains the {@link PowerUp} or an {@link Optional} empty if it is not created.
     */
    Optional<PowerUp> generateRandomPowerUp(Position position);
}
