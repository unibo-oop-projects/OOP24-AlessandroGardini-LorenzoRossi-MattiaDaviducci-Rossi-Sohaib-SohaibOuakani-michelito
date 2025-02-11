package it.unibo.michelito.view.gameview.frame.api;

import it.unibo.michelito.util.GameObject;

import java.util.Set;

public interface GameFrame {
    void setGameObjects(Set<GameObject> gameObjects);

    void setLives(final int lives);

    Set<Integer> getKeysPressed();
}
