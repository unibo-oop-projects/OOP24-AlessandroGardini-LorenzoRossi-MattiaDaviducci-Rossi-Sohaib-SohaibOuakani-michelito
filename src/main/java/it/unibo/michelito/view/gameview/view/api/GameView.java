package it.unibo.michelito.view.gameview.view.api;

import it.unibo.michelito.util.GameObject;

import java.util.Set;

public interface GameView {
    void show(boolean show);

    void display(Set<GameObject> gameObjects, int lives, int levelNumber);

    Set<Integer> getPressedKeys();
}
