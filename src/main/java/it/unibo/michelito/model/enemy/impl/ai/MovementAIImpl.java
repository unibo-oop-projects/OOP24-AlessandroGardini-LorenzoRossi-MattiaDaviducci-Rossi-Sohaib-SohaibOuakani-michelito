package it.unibo.michelito.model.enemy.impl.ai;

import it.unibo.michelito.model.enemy.api.ai.MovementAI;
import it.unibo.michelito.util.Direction;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class MovementAIImpl implements MovementAI {


    @Override
    public Direction getDirection() {
        Random random = new Random();
        return possibility().get(random.nextInt(possibility().size()));
    }

    abstract List<Direction> possibility();

}
