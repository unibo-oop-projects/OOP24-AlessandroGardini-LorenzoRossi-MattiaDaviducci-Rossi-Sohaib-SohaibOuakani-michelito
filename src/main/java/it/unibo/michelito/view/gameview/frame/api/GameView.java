package it.unibo.michelito.view.gameview.frame.api;

import it.unibo.michelito.util.GameObject;

import java.util.Set;

public interface GameView {
    boolean isViewShowing();

    void setViewVisibility(boolean show);

    void display(Set<GameObject> gameObjects, int lives, int levelNumber);

    Set<Integer> getPressedKeys();
}
