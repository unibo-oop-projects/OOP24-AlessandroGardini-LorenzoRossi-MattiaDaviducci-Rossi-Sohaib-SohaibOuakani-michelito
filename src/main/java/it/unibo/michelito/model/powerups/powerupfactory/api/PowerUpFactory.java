package it.unibo.michelito.model.powerups.powerupfactory.api;

import it.unibo.michelito.model.powerups.api.PowerUp;
import it.unibo.michelito.util.Position;

/**
 * Interface for a factory that creates instances of {@link PowerUp}.
 * This factory provides methods to create different types of power-ups,
 * each associated with a specific {@link Position} in the game world.
 */
public interface PowerUpFactory {
    /**
     * Creates a {@link PowerUp} that increases the bomb limit for the player.
     * This power-up allows the player to place more bombs simultaneously.
     *
     * @param position the position in the game world where the power-up will be placed
     * @return a new instance of a bomb limit power-up
     */
    PowerUp createBombLimitPowerUp(Position position);

    /**
     * Creates a {@link PowerUp} that changes the type of bombs the player can place.
     *
     * @param position the position in the game world where the power-up will be placed
     * @return a new instance of a bomb type power-up
     */
    PowerUp createBombTypePowerUp(Position position);

    /**
     * Creates a {@link PowerUp} that increases the player's movement speed.
     * This power-up allows the player to move faster.
     *
     * @param position the position in the game world where the power-up will be placed
     * @return a new instance of a speed power-up
     */
    PowerUp createSpeedPowerUp(Position position);
}