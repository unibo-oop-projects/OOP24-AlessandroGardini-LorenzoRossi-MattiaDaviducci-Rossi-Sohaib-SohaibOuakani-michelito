package it.unibo.michelito.model.powerups.impl;

import it.unibo.michelito.model.powerups.api.PowerUpFactory;
import it.unibo.michelito.model.powerups.api.PowerUp;
import it.unibo.michelito.util.Position;

import java.util.Optional;
import java.util.Random;

/**
 * Implementation of {@link PowerUpFactory}.
 */
public class PowerUpFactoryImpl implements PowerUpFactory {
    private static final double BOMB_CHANCE = 0.1;
    private static final double BOMB_PLUS_SPEED_CHANCE = 0.3;


    @Override
    public final PowerUp generateSpeedPowerUp(final Position position) {
        return new SpeedPowerUp(position);
    }

    @Override
    public final PowerUp generateBombPowerUp(final Position position) {
        return new BombPowerUp(position);
    }

    @Override
    public final Optional<PowerUp> generateRandomPowerUp(final Position position) {
        final double chance = new Random().nextDouble();

        if (chance <= BOMB_CHANCE) {
            return Optional.of(new BombPowerUp(position));
        }
        if (chance <= BOMB_PLUS_SPEED_CHANCE) {
            return Optional.of(new SpeedPowerUp(position));
        }
        return Optional.empty();
    }
}
