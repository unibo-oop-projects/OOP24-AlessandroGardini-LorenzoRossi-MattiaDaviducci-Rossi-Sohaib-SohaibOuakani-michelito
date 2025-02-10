package it.unibo.michelito.model.enemy.api.ai;

/**
 *An AI that change the mood of the enemy depending on various parameter.
 */
public interface MoodAI {

    /**
     * @return the current mood.
     */
    Movement getMovement();


    /**
     *
     * @param currentTime update the AI so he can change base of the actual condition.
     */
    void update(long currentTime);

}
