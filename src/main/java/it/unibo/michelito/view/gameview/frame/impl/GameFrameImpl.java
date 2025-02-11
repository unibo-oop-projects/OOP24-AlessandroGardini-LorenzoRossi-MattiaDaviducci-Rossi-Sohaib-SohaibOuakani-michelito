package it.unibo.michelito.view.gameview.frame.impl;

import it.unibo.michelito.util.GameObject;
import it.unibo.michelito.view.gameview.frame.api.GameFrame;
import it.unibo.michelito.view.gameview.panel.impl.GamePanelImpl;
import it.unibo.michelito.view.gameview.view.impl.GameViewImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Set;

public class GameFrameImpl extends JFrame implements GameFrame {
    double aspectRatio;
    GamePanelImpl gamePanel = new GamePanelImpl();
    JTextField livesTextField = new JTextField();

    public GameFrameImpl(GameViewImpl gameView) {
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
//        addWindowListener(new java.awt.event.WindowAdapter() {
//            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
//                int confirm = JOptionPane.showConfirmDialog(
//                        null,
//                        "Are you sure you want to exit?",
//                        "Exit Confirmation",
//                        JOptionPane.YES_NO_OPTION
//                );
//                if (confirm == JOptionPane.YES_OPTION) {
//
//                }
//            }
//        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void setGameObjects(Set<GameObject> gameObjects) {
        gamePanel.setGameObjects(gameObjects);
    }

    @Override
    public void setLives(final int lives) {
        livesTextField.setText(String.valueOf(lives));
    }

    @Override
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
