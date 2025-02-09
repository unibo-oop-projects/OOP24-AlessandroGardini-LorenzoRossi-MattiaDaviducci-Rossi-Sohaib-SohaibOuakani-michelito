package it.unibo.michelito.model.enemy.impl.ai;

import it.unibo.michelito.model.enemy.api.ai.MovementAI;
import it.unibo.michelito.util.Direction;

import java.util.List;
import java.util.Random;

/**
 * Implementation of {@link MovementAI}.
 */
public abstract class MovementAIImpl implements MovementAI {
    /**
     * @return a direction get randomly from the list of possibility so an event can have more possibility than others.
     */
    @Override
    public Direction getDirection() {
        final Random random = new Random();
        return possibility().get(random.nextInt(possibility().size()));
    }

    abstract List<Direction> possibility();

}
