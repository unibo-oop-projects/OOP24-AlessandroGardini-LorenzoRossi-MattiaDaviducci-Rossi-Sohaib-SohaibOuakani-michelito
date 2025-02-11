package it.unibo.michelito.model.enemy.api.ai;

/**
 *An AI that change the Movement of the enemy depending on various parameter.
 */
public interface MoodAI {

    /**
     * @return the current Movement.
     */
    Movement getMovement();


    /**
     *
     * @param currentTime update the AI so he can change base of the actual condition.
     */
    void update(long currentTime);

}
