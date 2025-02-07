package it.unibo.michelito.model.enemy.api.ai;

import it.unibo.michelito.util.Direction;

/**
 *An AI that change the mood of the enemy depending on various parameter.
 */
public interface MoodAI {
    /**
     * @param mood set the AI mood in the param state.
     */
    void setMood(MoodType mood);

    /**
     * @return the current mood.
     */
    MoodType getMood();

    /**
     * @return the direction decided by the current mood.
     */
    Direction getDirection();

    /**
     *
     * @param currentTime update the AI so he can change base of the actual condition.
     */
    void update(long currentTime);

}
