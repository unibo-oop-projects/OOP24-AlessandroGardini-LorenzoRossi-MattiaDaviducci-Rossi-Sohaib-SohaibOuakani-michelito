package it.unibo.michelito.model.powerups.impl;

import it.unibo.michelito.model.bomb.api.BombType;
import it.unibo.michelito.model.player.api.Player;
import it.unibo.michelito.util.Position;

public class BombTypePowerUp extends AbsPowerUp {
    public BombTypePowerUp(final Position position) {
        super(position);
    }
    @Override
    public void applyEffect(Player player) {
        player.changeBombType(BombType.getRandomType());
    }
}
