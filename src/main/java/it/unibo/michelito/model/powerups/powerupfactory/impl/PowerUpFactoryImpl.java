package it.unibo.michelito.model.powerups.powerupfactory.impl;

import it.unibo.michelito.model.powerups.impl.BombLimitPowerUp;
import it.unibo.michelito.model.powerups.impl.BombTypePowerUp;
import it.unibo.michelito.model.powerups.impl.SpeedPowerUp;
import it.unibo.michelito.model.powerups.powerupfactory.api.PowerUpFactory;
import it.unibo.michelito.model.powerups.api.PowerUp;
import it.unibo.michelito.util.Position;

import java.util.Optional;
import java.util.Random;

/**
 * Implementation of {@link PowerUpFactory}.
 */
public class PowerUpFactoryImpl implements PowerUpFactory {
    private static final double BOMB_LIMIT_CHANCE = 0.1;
    private static final double UPGRADE_SPEED_CHANCE = 0.2;
    private static final double BOMB_TYPE_CHANCE = 0.5;

    /**
     *{@inheritDoc}
     */
    @Override
    public Optional<PowerUp> generateRandomPowerUp(final Position position) {
        final double chance = new Random().nextDouble();

        if (chance <= BOMB_TYPE_CHANCE) {
            if(chance <= UPGRADE_SPEED_CHANCE) {
                if(chance <= BOMB_LIMIT_CHANCE) {
                    return Optional.of(new BombLimitPowerUp(position));
                }
                return Optional.of(new SpeedPowerUp(position));
            }
            return Optional.of(new BombTypePowerUp(position));
        } else {
            return Optional.empty();
        }
    }
}
