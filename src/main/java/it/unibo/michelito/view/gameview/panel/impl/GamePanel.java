package it.unibo.michelito.view.gameview.panel.impl;

import it.unibo.michelito.util.GameObject;
import it.unibo.michelito.view.gameview.panel.api.InputHandler;

import java.awt.Dimension;
import java.awt.Graphics;
import java.io.Serial;
import java.util.Objects;
import java.util.Collections;
import java.util.Set;
import javax.swing.JPanel;

/**
 * Implementation of the game panel.
 */
public final class GamePanel extends JPanel {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * The width of the game panel.
     */
    public static final int WIDTH = 840;
    /**
     * The height of the game panel.
     */
    public static final int HEIGHT = 600;

    private Set<GameObject> gameObjects;
    private int currentLives;
    private int currentLevelNumber;
    private final InputHandler inputHandler = new InputHandlerImpl();

    /**
     * Constructor for the game panel.
     */
    public GamePanel() {
        super();
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.addKeyListener(inputHandler);
        this.setFocusable(true);
        this.requestFocusInWindow();
    }

    @Override
    public void paint(final Graphics g) {
        super.paintComponent(g);
        if (Objects.nonNull(gameObjects)) {
            for (final GameObject gameObject : gameObjects) {
               GameObjectRenderer.render(g, gameObject, this);
            }
        }
        GameStatisticRenderer.render(g, this, this.currentLives, this.currentLevelNumber);
    }

    /**
     * Displays the game objects.
     *
     * @param gameObjects the game objects to display.
     * @param lives the number of lives.
     * @param levelNumber the level number.
     */
    public void display(final Set<GameObject> gameObjects, final int lives, final int levelNumber) {
        boolean needsRepaint = false;
        if (this.currentLives != lives) {
            this.currentLives = lives;
            needsRepaint = true;
        }
        if (this.currentLevelNumber != levelNumber) {
            this.currentLevelNumber = levelNumber;
            needsRepaint = true;
        }
        if (!Objects.equals(this.gameObjects, gameObjects)) {
            this.gameObjects = Collections.unmodifiableSet(gameObjects);
            needsRepaint = true;
        }
        if (needsRepaint) {
            this.repaint();
        }
    }

    /**
     * Get the keys pressed.
     *
     * @return the keys pressed.
     */
    public Set<Integer> getKeysPressed() {
        return inputHandler.keysPressed();
    }
}
