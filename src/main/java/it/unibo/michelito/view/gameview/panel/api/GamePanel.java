package it.unibo.michelito.view.gameview.panel.api;

import it.unibo.michelito.util.GameObject;

import java.util.Set;

public interface GamePanel {
    void setGameObjects(Set<GameObject> gameObjects);

    Set<Integer> getKeysPressed();
}
