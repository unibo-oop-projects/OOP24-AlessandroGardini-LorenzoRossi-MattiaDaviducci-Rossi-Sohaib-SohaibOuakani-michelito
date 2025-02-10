package it.unibo.michelito.model.powerups.powerupfactory.impl;

import it.unibo.michelito.model.powerups.impl.BombLimitPowerUp;
import it.unibo.michelito.model.powerups.impl.BombTypePowerUp;
import it.unibo.michelito.model.powerups.impl.SpeedPowerUp;
import it.unibo.michelito.model.powerups.powerupfactory.api.PowerUpFactory;
import it.unibo.michelito.model.powerups.api.PowerUp;
import it.unibo.michelito.util.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 * Implementation of {@link PowerUpFactory}.
 */
public class PowerUpFactoryImpl implements PowerUpFactory {
    private static final double DROP_CHANCE = 0.1;
    private final Random random = new Random();

    /**
     *{@inheritDoc}
     */
    @Override
    public Optional<PowerUp> generateRandomPowerUp(final Position position) {
        final double chance = this.random.nextDouble();
        List<PowerUp> list = List.of(
            new BombTypePowerUp(position),
            new BombLimitPowerUp(position),
            new SpeedPowerUp(position)
        );

        if (chance <= DROP_CHANCE) {
            return Optional.of(list.get(random.nextInt(list.size())));
        } else {
            return Optional.empty();
        }
    }
}
