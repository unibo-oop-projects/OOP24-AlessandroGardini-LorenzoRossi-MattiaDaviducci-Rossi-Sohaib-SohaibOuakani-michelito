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
    /**
     * {@inheritDoc}
     */
    @Override
    public PowerUp createBombLimitPowerUp(final Position position) {
        return new BombLimitPowerUp(position);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PowerUp createBombTypePowerUp(final Position position) {
        return new BombTypePowerUp(position);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PowerUp createSpeedPowerUp(final Position position) {
        return new SpeedPowerUp(position);
    }
}
