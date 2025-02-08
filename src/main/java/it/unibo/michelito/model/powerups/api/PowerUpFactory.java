package it.unibo.michelito.model.powerups.api;

import it.unibo.michelito.model.powerups.impl.BombLimitPowerUp;
import it.unibo.michelito.util.Position;

import java.util.Optional;

/**
 * Interface for a factory that creates {@link PowerUp}.
 */
public interface PowerUpFactory {
    /**
     * Method that can create a random {@link PowerUp} or not.
     * @param position the {@link Position} where to create the random {@link PowerUp}.
     * @return an {@link Optional} that contains the {@link PowerUp} or an {@link Optional} empty if it is not created.
     */
    Optional<PowerUp> generateRandomPowerUp(Position position);
}
