package it.unibo.michelito.model.enemy.api.ai;

public interface MoodAIFactory {
    MovementAI chilling();

    MovementAI sleeping();

    MovementAI searching();
}
