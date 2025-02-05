package it.unibo.michelito.model.powerups;

import it.unibo.michelito.model.modelutil.Temporary;
import it.unibo.michelito.model.player.api.Player;

/**
 * interface that rapresents a power-up for the player.
 */
public interface Powerup extends Temporary {
    /**
     * method that apply the power-up to the player.
     * @param player the target of the power-up.
     */
    void applyEffect(Player player);
}
