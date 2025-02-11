package it.unibo.michelito.view.gameview.view.impl;

import it.unibo.michelito.controller.gamecontroller.api.Switcher;
import it.unibo.michelito.util.GameObject;
import it.unibo.michelito.view.gameview.frame.api.GameFrame;
import it.unibo.michelito.view.gameview.view.api.GameView;
import it.unibo.michelito.view.gameview.frame.impl.GameFrameImpl;

import javax.swing.*;
import java.util.Set;

public class GameViewImpl implements GameView {
    private boolean showing = false;
    private final Switcher switcher;
    private final GameFrameImpl frame = new GameFrameImpl(this);

    public GameViewImpl(final Switcher switcher) {
        this.switcher = switcher;
    }

    @Override
    public void show(boolean show) {
        this.showing = show;
        SwingUtilities.invokeLater(() -> frame.setVisible(show));
    }

    @Override
    public void display(final Set<GameObject> gameObjects, final int lives) {
        SwingUtilities.invokeLater(() -> {
            frame.setGameObjects(gameObjects);
            frame.setLives(lives);
        });
    }

    @Override
    public Set<Integer> getPressedKeys() {
        return frame.getKeysPressed();
    }
}
