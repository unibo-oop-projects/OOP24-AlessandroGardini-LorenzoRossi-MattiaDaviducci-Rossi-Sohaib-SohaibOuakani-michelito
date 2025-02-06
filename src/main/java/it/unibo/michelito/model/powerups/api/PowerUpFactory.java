package it.unibo.michelito.model.powerups.api;

import it.unibo.michelito.util.Position;

import java.util.Optional;

/**
 * Interface for a factory that creates {@link PowerUp}
 */
public interface PowerUpFactory {
    PowerUp generateSpeedPowerUp(Position position);

    PowerUp generateBombPowerUp(Position position);

    Optional<PowerUp> generateRandomPowerUp(Position position);
}
