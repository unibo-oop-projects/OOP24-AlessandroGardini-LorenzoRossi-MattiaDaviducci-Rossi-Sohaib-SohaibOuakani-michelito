package it.unibo.michelito.view.gameview.panel.impl;

import it.unibo.michelito.util.GameObject;
import it.unibo.michelito.view.gameview.panel.api.InputHandler;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;
import java.util.Set;

public class GamePanel extends JPanel {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    private Set<GameObject> gameObjects;
    private int currentLives;
    private int currentLevelNumber;
    final private InputHandler inputHandler = new InputHandlerImpl();

    public GamePanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
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
        GameStatisticRenderer.render(g, this, this.currentLives, this.currentLevelNumber);
    }

    public void display(Set<GameObject> gameObjects, int lives, int levelNumber) {
        if (this.currentLives != lives) {
            this.currentLives = lives;
            this.repaint();
        }
        if (this.currentLevelNumber != levelNumber) {
            this.currentLevelNumber = levelNumber;
            this.repaint();
        }
        this.gameObjects = gameObjects;
        if (!gameObjects.isEmpty()) {
            this.repaint();
        }
    }

    public Set<Integer> getKeysPressed() {
        return inputHandler.keysPressed();
    }
}