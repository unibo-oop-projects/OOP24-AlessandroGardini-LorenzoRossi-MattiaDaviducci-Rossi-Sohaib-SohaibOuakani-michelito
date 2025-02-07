package it.unibo.michelito.model.enemy.impl.ai;

import it.unibo.michelito.model.enemy.api.ai.MoodAI;
import it.unibo.michelito.model.enemy.api.ai.MoodAIFactory;
import it.unibo.michelito.model.enemy.api.ai.MoodType;
import it.unibo.michelito.model.enemy.api.ai.MovementAI;
import it.unibo.michelito.model.maze.api.Maze;
import it.unibo.michelito.util.Direction;

/**
 *Implementation of {@link MoodAI}.
 */
public final class MoodAIImpl implements MoodAI {
    private static final long SLEEPTIME = 10000;
    private static final double DEATHFORVENGENS = 0.70;
    private MoodType actualMood;
    private MovementAI actualMovement;
    private final MoodAIFactory moodAIFactory = new MoodAIFactoryImpl();
    private final long createdTime;
    private final Maze maze;
    private final int initialEnemy;

    /**
     *Constructor for {@link MoodAIImpl}.
     * @param currentTime the time of creation of the AI
     * @param maze the maze so it can accede information about the current state of the maze.
     */
    public MoodAIImpl(final long currentTime, final Maze maze) {
        this.createdTime = currentTime;
        this.maze = maze;
        initialEnemy = maze.getEnemies().size();
        setMood(MoodType.SLEEPING);
    }

    @Override
    public void setMood(final MoodType mood) {
        switch (mood) {
            case SLEEPING:
                actualMovement = moodAIFactory.sleeping();
                actualMood = MoodType.SLEEPING;
                break;
            case CHILLING:
                actualMovement = moodAIFactory.chilling();
                actualMood = MoodType.CHILLING;
                break;
            case SEARCHING:
                actualMovement = moodAIFactory.searching();
                actualMood = MoodType.SEARCHING;
                break;
            default:
                break;
        }
    }

    @Override
    public MoodType getMood() {
        return actualMood;
    }

    @Override
    public Direction getDirection() {
        return actualMovement.getDirection();
    }

    @Override
    public void update(final long currentTime) {
        if (currentTime - createdTime >= SLEEPTIME) {
            setMood(MoodType.CHILLING);
        }
        if (maze.getEnemies().size() < initialEnemy * DEATHFORVENGENS) {
            setMood(MoodType.SEARCHING);
        }
    }
}
