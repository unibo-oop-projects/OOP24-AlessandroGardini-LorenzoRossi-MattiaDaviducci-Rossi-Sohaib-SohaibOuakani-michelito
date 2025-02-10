package it.unibo.michelito.view.gameview.impl;

import it.unibo.michelito.util.GameObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Set;

class GameFrame extends JFrame {
    double aspectRatio;
    GamePanel gamePanel = new GamePanel();
    GamePanel southPanel = new GamePanel();
    JTextField livesTextField = new JTextField();

    public GameFrame(GameViewImpl gameView) {
        setTitle("Michelito");

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

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                int confirm = JOptionPane.showConfirmDialog(
                        null,
                        "Are you sure you want to exit?",
                        "Exit Confirmation",
                        JOptionPane.YES_NO_OPTION
                );
                if (confirm == JOptionPane.YES_OPTION) {

                }
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void setGameObjects(Set<GameObject> gameObjects) {
        gamePanel.setGameObjects(gameObjects);
    }

    public void setLives(final int lives) {
        livesTextField.setText(String.valueOf(lives));
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
