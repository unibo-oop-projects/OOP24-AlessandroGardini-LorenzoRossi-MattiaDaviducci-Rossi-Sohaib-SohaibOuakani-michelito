package it.unibo.michelito.view.gameview.impl;

import it.unibo.michelito.util.GameObject;
import it.unibo.michelito.view.gameview.api.InputHandler;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;
import java.util.Set;

public class GamePanel extends JPanel {
    private Set<GameObject> gameObjects;
    private InputHandler inputHandler = new InputHandlerImpl();

    public GamePanel() {
        setPreferredSize(new Dimension(800, 600)); // Size of the game window
        this.addKeyListener(inputHandler);
        this.setFocusable(true);
        this.requestFocusInWindow();
    }

    public void setGameObjects(Set<GameObject> gameObjects) {
        this.gameObjects = gameObjects;
        if (!gameObjects.isEmpty()) {
            this.repaint();
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paintComponent(g);
        if (Objects.nonNull(gameObjects)) {
            for (GameObject gameObject : gameObjects) {
                GameObjectRenderer.render(g, gameObject, this);
            }
        }
    }

    public Set<Integer> getKeysPressed() {
        return inputHandler.keyPressed();
    }
}