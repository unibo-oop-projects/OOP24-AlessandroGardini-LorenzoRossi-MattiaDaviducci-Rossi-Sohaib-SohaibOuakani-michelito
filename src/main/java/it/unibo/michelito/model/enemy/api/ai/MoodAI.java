package it.unibo.michelito.model.enemy.api.ai;

public interface MoodAI {
    enum MoodType {
        CHASING,
        CHILLING,
        SEARCHING
    }

    MoodType getMood();
}
