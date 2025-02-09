package it.unibo.michelito.model.powerups.impl;

import it.unibo.michelito.model.player.api.ModifiablePlayer;
import it.unibo.michelito.util.Position;


public class BombLimitPowerUp extends AbsPowerUp {
    private static final int BOMB_LIMIT_UPGRADE = 1;

    public BombLimitPowerUp(final Position position) {
        super(position);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void applyEffect(final ModifiablePlayer player) {
        final int newLimit = player.getBombLimit() + BOMB_LIMIT_UPGRADE;
        player.setBombLimit(newLimit);
    }
}
