package it.unibo.michelito.view.gameview.impl;

import it.unibo.michelito.controller.gamecontroller.api.Switcher;
import it.unibo.michelito.controller.gamecontroller.impl.GameControllerImpl;
import it.unibo.michelito.util.GameObject;
import it.unibo.michelito.view.gameview.api.GameView;
import it.unibo.michelito.view.gameview.api.InputHandler;

import javax.swing.*;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

public class GameViewImpl implements GameView {
    private boolean showing = false;
    private final Switcher switcher;
    private final GameFrame frame = new GameFrame(this);

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
