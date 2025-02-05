package it.unibo.michelito.model.powerups.impl;

import it.unibo.michelito.model.player.api.Player;
import it.unibo.michelito.util.Position;

public class SpeedPowerUp extends AbsPoweUp {

    public SpeedPowerUp(Position position) {
        super(position);
    }

    @Override
    public void applyEffect(Player player) {
        player.increaseSpeed();
    }
}
