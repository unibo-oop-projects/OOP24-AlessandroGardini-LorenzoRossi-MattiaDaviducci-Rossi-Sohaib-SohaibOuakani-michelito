package it.unibo.michelito.view.gameview.impl;

import it.unibo.michelito.util.GameObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Set;

public class GameFrame extends JFrame {
    double aspectRatio;
    GamePanel gamePanel = new GamePanel();

    public GameFrame() {
        setTitle("Game Window");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(gamePanel, BorderLayout.CENTER);

        pack();
        Dimension size = getSize();
        aspectRatio = (double) size.width / size.height;
        setMinimumSize(size);
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                resizeDiagonally();
            }
        });

        setLocationRelativeTo(null); // Center the window
        setVisible(true);
    }

    public void setGameObjects(Set<GameObject> gameObjects) {
        gamePanel.setGameObjects(gameObjects);
    }

    public Set<Integer> getKeysPressed() {
        return gamePanel.getKeysPressed();
    }

    private void resizeDiagonally() {
        Dimension size = getSize();
        int newWidth = size.width;
        int newHeight = (int) (newWidth / aspectRatio);

        if (newHeight != size.height) {
            setSize(newWidth, newHeight);
        }
    }
}




