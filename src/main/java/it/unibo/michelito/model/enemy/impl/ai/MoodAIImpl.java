package it.unibo.michelito.model.enemy.impl.ai;

import it.unibo.michelito.model.enemy.api.ai.MoodAI;
import it.unibo.michelito.model.enemy.api.ai.MovementFactory;
import it.unibo.michelito.model.enemy.api.ai.MovementType;
import it.unibo.michelito.model.enemy.api.ai.Movement;
import it.unibo.michelito.model.maze.api.Maze;
import it.unibo.michelito.util.Direction;

/**
 *Implementation of {@link MoodAI}.
 */
public final class MoodAIImpl implements MoodAI {
    private static final long SLEEPTIME = 5_000;
    private static final double DEATHFORVENGENS = 0.70;
    private MovementType actualMood;
    private Movement actualMovement;
    private final MovementFactory movementFactory = new MovementFactoryImpl();
    private long createdTime;
    private final Maze maze;
    private final int initialEnemy;

    /**
     *Constructor for {@link MoodAIImpl}.
     * @param maze the maze so it can accede information about the current state of the maze.
     */
    public MoodAIImpl(final Maze maze) {
        this.createdTime = 0;
        this.maze = maze;
        initialEnemy = maze.getEnemies().size();
        setMood(MovementType.SLEEPING);
    }

    private void setMood(final MovementType mood) {
        switch (mood) {
            case SLEEPING:
                actualMovement = movementFactory.sleeping();
                actualMood = MovementType.SLEEPING;
                break;
            case CHILLING:
                actualMovement = movementFactory.chilling();
                actualMood = MovementType.CHILLING;
                break;
            case SEARCHING:
                actualMovement = movementFactory.searching();
                actualMood = MovementType.SEARCHING;
                break;
            default:
                break;
        }
    }

    @Override
    public Movement getMovement() {
        return actualMovement;
    }


    @Override
    public void update(final long deltaTime) {
        this.createdTime = createdTime + deltaTime;
        if (createdTime >= SLEEPTIME) {
            setMood(MovementType.CHILLING);
        }
        if (maze.getEnemies().size() < initialEnemy * DEATHFORVENGENS) {
            setMood(MovementType.SEARCHING);
        }
    }
}
