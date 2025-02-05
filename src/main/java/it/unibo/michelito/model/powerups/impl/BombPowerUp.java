package it.unibo.michelito.model.powerups.impl;

import it.unibo.michelito.model.player.api.Player;
import it.unibo.michelito.util.Position;


public class BombPowerUp extends AbsPoweUp {

    public BombPowerUp(Position position) {
        super(position);
    }

    @Override
    public void applyEffect(Player player) {
        player.increaseBombLimit();;
    }
}
