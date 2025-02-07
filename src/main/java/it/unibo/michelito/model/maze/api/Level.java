package it.unibo.michelito.model.maze.api;

import java.util.Set;

public interface Level {
    void update(long currentTime);

    Set<GameObject> getObjects();

    boolean isWon();

    boolean isLost();
}
