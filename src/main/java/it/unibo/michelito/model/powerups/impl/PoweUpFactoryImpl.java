package it.unibo.michelito.model.powerups.impl;

import it.unibo.michelito.model.powerups.api.PowerUpFactory;
import it.unibo.michelito.model.powerups.api.PowerUp;
import it.unibo.michelito.util.Position;

import java.util.Optional;
import java.util.Random;

public class PoweUpFactoryImpl implements PowerUpFactory {
    private static final double BOMB_CHANCE = 0.1;
    private static final double BOMB_PLUS_SPEED_CHANCE = 0.3;


    @Override
    public PowerUp generateSpeedPowerUp(final Position position) {
        return new SpeedPowerUp(position);
    }

    @Override
    public PowerUp generateBombPowerUp(final Position position) {
        return new BombPowerUp(position);
    }

    @Override
    public Optional<PowerUp> generateRandomPowerUp(final Position position) {
        final Random random = new Random();
        final double chance = random.nextDouble();

        if (chance <= BOMB_CHANCE) {
            return Optional.of(new BombPowerUp(position));
        }
        if (chance <= BOMB_PLUS_SPEED_CHANCE) {
            return Optional.of(new SpeedPowerUp(position));
        }
        return Optional.empty();
    }
}
