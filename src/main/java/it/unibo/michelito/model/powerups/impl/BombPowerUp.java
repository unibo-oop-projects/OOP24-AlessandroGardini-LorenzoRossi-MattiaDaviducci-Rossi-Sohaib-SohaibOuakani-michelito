package it.unibo.michelito.model.powerups.impl;

import it.unibo.michelito.model.player.api.Player;
import it.unibo.michelito.util.Position;


public class BombPowerUp extends AbsPowerUp {

    public BombPowerUp(final Position position) {
        super(position);
    }

    @Override
    public void applyEffect(final Player player) {
        player.increaseBombLimit();
    }
}
