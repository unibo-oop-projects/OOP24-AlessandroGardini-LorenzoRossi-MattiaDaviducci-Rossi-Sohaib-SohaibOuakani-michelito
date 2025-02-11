package it.unibo.michelito.view.gameview.frame.api;

import it.unibo.michelito.util.GameObject;

import java.util.Set;

public interface GameFrame {
    void setGameObjects(Set<GameObject> gameObjects);

    void setStatistics(int lives, int levelNumber);

    Set<Integer> getKeysPressed();
}
