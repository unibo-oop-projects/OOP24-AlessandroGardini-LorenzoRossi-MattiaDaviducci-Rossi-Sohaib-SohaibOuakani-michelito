package it.unibo.michelito.view.gameview.impl;

import it.unibo.michelito.util.GameObject;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

public class GameFrame extends JFrame {
    GamePanel gamePanel = new GamePanel();

    public GameFrame() {
        setTitle("Game Window");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(gamePanel, BorderLayout.CENTER);

        pack();  // Resize the window to fit the preferred size
        setLocationRelativeTo(null); // Center the window
        setVisible(true);
    }

    public void setGameObjects(Set<GameObject> gameObjects) {
        gamePanel.setGameObjects(gameObjects);
    }

    public Set<Integer> getKeysPressed() {
        return gamePanel.getKeysPressed();
    }
}
