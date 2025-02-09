package it.unibo.michelito.model.powerups.impl;

import it.unibo.michelito.model.player.api.ModifiablePlayer;
import it.unibo.michelito.model.player.api.Player;
import it.unibo.michelito.util.Position;

import java.math.BigDecimal;

/**
 * Implementation of a {@link it.unibo.michelito.model.powerups.api.PowerUp} that increases {@link Player} speed.
 */
public class SpeedPowerUp extends AbsPowerUp {
    private static final double SPEED_UPGRADE = 0.1;
    /**
     * {@inheritDoc}
     */
    public SpeedPowerUp(final Position position) {
        super(position);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void applyEffect(final ModifiablePlayer player) {
        final double newSpeed = BigDecimal.valueOf(SPEED_UPGRADE).add(BigDecimal.valueOf(player.getSpeed())).doubleValue();
        player.setSpeed(newSpeed);
    }
}
