package it.unibo.michelito.model.powerups.api;

import it.unibo.michelito.util.Position;

/**
 * Interface for a factory that creates instances of {@link PowerUp}.
 * This factory provides methods to create different types of power-ups,
 * each associated with a specific {@link Position} in the game world.
 */
public interface PowerUpFactory {
    PowerUp createPowerUp(Position position, PowerUpType type);
}