package it.unibo.michelito.model.enemy.api.ai;

/**
 *
 */
public interface MoodAIFactory {
    /**
     *
     * @return
     */
    MovementAI chilling();

    /**
     *
     * @return
     */
    MovementAI sleeping();

    /**
     *
     * @return
     */
    MovementAI searching();
}
