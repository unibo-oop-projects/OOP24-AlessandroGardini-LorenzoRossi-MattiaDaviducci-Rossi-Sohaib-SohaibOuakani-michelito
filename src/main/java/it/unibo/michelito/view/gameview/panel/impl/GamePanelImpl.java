package it.unibo.michelito.view.gameview.panel.impl;

import it.unibo.michelito.util.GameObject;
import it.unibo.michelito.view.gameview.panel.api.GamePanel;
import it.unibo.michelito.view.gameview.panel.api.InputHandler;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;
import java.util.Set;

public class GamePanelImpl extends JPanel implements GamePanel {
    private Set<GameObject> gameObjects;
    private int currentLives;
    final private InputHandler inputHandler = new InputHandlerImpl();

    public GamePanelImpl() {
        setPreferredSize(new Dimension(800, 600)); // Size of the game window
        this.addKeyListener(inputHandler);
        this.setFocusable(true);
        this.requestFocusInWindow();
    }

    @Override
    public void paint(Graphics g) {
        super.paintComponent(g);
        if (Objects.nonNull(gameObjects)) {
            for (GameObject gameObject : gameObjects) {
               GameObjectRenderer.render(g, gameObject, this);
            }
        }
        GameLivesRenderer.render(g, this, this.currentLives);
    }

    @Override
    public void setGameObjects(Set<GameObject> gameObjects) {
        this.gameObjects = gameObjects;
        if (!gameObjects.isEmpty()) {
            this.repaint();
        }
    }

    @Override
    public void setLives(int currentLives) {
        if (this.currentLives != currentLives) {
            this.currentLives = currentLives;
            this.repaint();
        }
    }

    @Override
    public Set<Integer> getKeysPressed() {
        return inputHandler.keysPressed();
    }
}