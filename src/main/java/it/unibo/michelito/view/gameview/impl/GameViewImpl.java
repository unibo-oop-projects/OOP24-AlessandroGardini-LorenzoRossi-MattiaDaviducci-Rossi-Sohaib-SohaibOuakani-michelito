package it.unibo.michelito.view.gameview.impl;

import it.unibo.michelito.util.GameObject;
import it.unibo.michelito.view.gameview.api.GameView;

import java.util.Set;

public class GameViewImpl implements GameView {
    private boolean showing = false;

    @Override
    public void show(boolean show) {
        this.showing = show;
    }

    @Override
    public void display(Set<GameObject> gameObjects) {
        //TODO: make the view display the new state of the game
    }
}
