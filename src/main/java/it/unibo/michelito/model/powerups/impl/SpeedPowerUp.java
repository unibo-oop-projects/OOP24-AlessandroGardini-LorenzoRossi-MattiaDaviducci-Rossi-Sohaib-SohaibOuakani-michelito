package it.unibo.michelito.model.powerups.impl;

import it.unibo.michelito.model.player.api.Player;
import it.unibo.michelito.util.Position;

public class SpeedPowerUp extends AbsPowerUp {

    public SpeedPowerUp(final Position position) {
        super(position);
    }

    @Override
    public final void applyEffect(final Player player) {
        player.increaseSpeed();
    }
}
