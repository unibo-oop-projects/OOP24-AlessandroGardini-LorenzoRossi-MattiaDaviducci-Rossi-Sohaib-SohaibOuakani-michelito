package it.unibo.michelito.model.enemy.api.ai;

import it.unibo.michelito.util.Direction;

public interface MoodAI {
    void setMood(MoodType mood);

    MoodType getMood();

    Direction getDirection();

    void update(long currentTime);

}
