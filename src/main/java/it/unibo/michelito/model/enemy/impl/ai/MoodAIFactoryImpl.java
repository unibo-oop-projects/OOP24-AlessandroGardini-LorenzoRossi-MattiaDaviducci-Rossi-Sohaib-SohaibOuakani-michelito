package it.unibo.michelito.model.enemy.impl.ai;

import it.unibo.michelito.model.enemy.api.ai.MoodAIFactory;
import it.unibo.michelito.model.enemy.api.ai.MovementAI;
import it.unibo.michelito.util.Direction;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of {@link MoodAIFactory}.
 */
public final class MoodAIFactoryImpl implements MoodAIFactory {
    private static final int CHILLINGPOSSIBILITY = 13;

    @Override
    public MovementAI chilling() {
        return  new MovementAIImpl() {
            @Override
            List<Direction> possibility() {
                final List<Direction> list = new ArrayList<>();
                for (int x = 0; x < CHILLINGPOSSIBILITY; x++) {
                        list.add(Direction.NONE);
                }
                for (int x = 0; x < 3; x++) {
                    list.addAll(List.of(Direction.UP, Direction.DOWN, Direction.LEFT, Direction.RIGHT));
                }
                return list;
            }
        };
    }

    @Override
    public MovementAI sleeping() {
        return new MovementAIImpl() {
            @Override
            List<Direction> possibility() {
                return List.of(Direction.NONE);
            }
        };
    }

    @Override
    public MovementAI searching() {
        return new MovementAIImpl() {
            @Override
            List<Direction> possibility() {
                return List.of(Direction.LEFT, Direction.RIGHT, Direction.UP, Direction.DOWN);
            }
        };
    }
}
