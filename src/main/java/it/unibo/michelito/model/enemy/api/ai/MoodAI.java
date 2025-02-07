package it.unibo.michelito.model.enemy.api.ai;

import it.unibo.michelito.util.Direction;

/**
 *
 */
public interface MoodAI {
    /**
     *
     * @param mood
     */
    void setMood(MoodType mood);

    /**
     *
     * @return
     */
    MoodType getMood();

    /**
     *
     * @return
     */
    Direction getDirection();

    /**
     *
     * @param currentTime
     */
    void update(long currentTime);

}
