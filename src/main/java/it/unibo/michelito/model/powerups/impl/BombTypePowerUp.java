package it.unibo.michelito.model.powerups.impl;

import it.unibo.michelito.model.bomb.api.BombType;
import it.unibo.michelito.model.player.api.ModifiablePlayer;
import it.unibo.michelito.util.Position;

public class BombTypePowerUp extends AbsPowerUp {
    /**
     * {@inheritDoc}
     */
    public BombTypePowerUp(final Position position) {
        super(position);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void applyEffect(final ModifiablePlayer player) {
        BombType newBombType = BombType.getRandomType();
        while(newBombType.equals(player.getBombType())) {
            newBombType = BombType.getRandomType();
        }
        player.setBombType(newBombType);
    }
}
