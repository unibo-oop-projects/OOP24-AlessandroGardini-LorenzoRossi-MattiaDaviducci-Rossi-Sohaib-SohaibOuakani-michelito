package it.unibo.michelito.model.enemy.api.ai;

import it.unibo.michelito.util.Direction;

/**
 *The base interface of movement for enemy.
 */
public interface MovementAI {
    /**
     * @return the direction where the enemy will try to go.
     */
    Direction getDirection();
}
