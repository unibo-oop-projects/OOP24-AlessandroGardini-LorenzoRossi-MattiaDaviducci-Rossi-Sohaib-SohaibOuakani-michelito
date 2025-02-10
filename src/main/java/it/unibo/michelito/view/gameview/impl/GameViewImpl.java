package it.unibo.michelito.view.gameview.impl;

import it.unibo.michelito.controller.gamecontroller.api.Switcher;
import it.unibo.michelito.util.GameObject;
import it.unibo.michelito.view.gameview.api.GameView;
import it.unibo.michelito.view.gameview.api.InputHandler;

import java.util.Set;

public class GameViewImpl implements GameView {
    private boolean showing = false;
    private InputHandler inputHandler = new InputHandlerImpl();
    private final Switcher switcher;

    public GameViewImpl(final Switcher switcher) {
        this.switcher = switcher;
    }

    @Override
    public void show(boolean show) {
        this.showing = show;
    }

    @Override
    public void display(Set<GameObject> gameObjects, final int lives) {
        //TODO: make the view display the new state of the game
    }

    @Override
    public Set<Integer> getPressedKeys() {
        return this.inputHandler.keyPressed();
    }
}
