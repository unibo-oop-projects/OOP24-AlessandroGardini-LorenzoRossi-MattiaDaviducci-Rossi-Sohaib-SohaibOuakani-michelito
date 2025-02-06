package it.unibo.michelito.model.powerups.api;

import it.unibo.michelito.util.Position;

import java.util.Optional;

public interface PowerUpFactory {
    Powerup generateSpeedPowerUp(Position position);

    Powerup generateBombPowerUp(Position position);

    Optional<Powerup> generateRandomPowerUp(Position position);
}
