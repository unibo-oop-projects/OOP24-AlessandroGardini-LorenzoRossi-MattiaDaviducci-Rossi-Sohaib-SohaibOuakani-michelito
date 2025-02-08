package it.unibo.michelito.model.powerups.impl;

import it.unibo.michelito.model.player.api.Player;
import it.unibo.michelito.util.Position;


public class BombLimitPowerUp extends AbsPowerUp {
    private static final int BOMB_LIMIT_UPGRADE = 1;

    public BombLimitPowerUp(final Position position) {
        super(position);
    }

    @Override
    public final void applyEffect(final Player player) {
        player.increaseBombLimit(BOMB_LIMIT_UPGRADE);
    }
}
