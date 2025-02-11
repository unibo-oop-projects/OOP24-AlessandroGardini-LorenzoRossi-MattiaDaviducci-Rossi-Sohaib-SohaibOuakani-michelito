package it.unibo.michelito.view.gameview.panel.impl;

import it.unibo.michelito.util.GameObject;
import it.unibo.michelito.util.ObjectType;
import it.unibo.michelito.view.gameview.panel.api.GamePanel;
import it.unibo.michelito.view.gameview.panel.api.InputHandler;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;
import java.util.Set;

public class GamePanelImpl extends JPanel implements GamePanel {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    private Set<GameObject> gameObjects;
    private int currentLives;
    private int currentLevelNumber;
    final private InputHandler inputHandler = new InputHandlerImpl();

    public GamePanelImpl() {
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

    @Override
    public void setStatistics(int lives, int levelNumber) {
        if (this.currentLives != lives) {
            this.currentLives = lives;
            this.repaint();
        }
        if (this.currentLevelNumber != levelNumber) {
            this.currentLevelNumber = levelNumber;
            this.repaint();
        }
    }

    @Override
    public void setGameObjects(Set<GameObject> gameObjects) {
        this.gameObjects = gameObjects;
        if (!gameObjects.isEmpty()) {
            this.repaint();
        }
    }

    @Override
    public Set<Integer> getKeysPressed() {
        return inputHandler.keysPressed();
    }
}