package it.unibo.michelito.view.gameview.frame.impl;

import it.unibo.michelito.util.GameObject;
import it.unibo.michelito.view.gameview.frame.api.GameFrame;
import it.unibo.michelito.view.gameview.panel.impl.GamePanelImpl;
import it.unibo.michelito.view.gameview.frame.api.GameView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Set;

public class GameViewImpl extends JFrame implements GameFrame, GameView {
    private boolean showing = true;
    private double aspectRatio;
    private GamePanelImpl gamePanel = new GamePanelImpl();
    private JTextField livesTextField = new JTextField();

    public GameViewImpl() {
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
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int response = JOptionPane.showConfirmDialog(
                        GameViewImpl.this,
                        "Are you sure about that?",
                        "Quit",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );

                if (response == JOptionPane.YES_OPTION) {
                    setShowing(false);
                    GameViewImpl.this.dispose();
                }
            }
        });

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);


        setLocationRelativeTo(null);
    }

    @Override
    public void setGameObjects(Set<GameObject> gameObjects) {
        gamePanel.setGameObjects(gameObjects);
    }

    @Override
    public void setStatistics(final int lives, final int levelNumber) {
        gamePanel.setStatistics(lives, levelNumber);
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

    @Override
    public void setViewVisibility(boolean show) {
        SwingUtilities.invokeLater(() -> {
            this.setVisible(show);
        });
    }

    @Override
    public synchronized boolean isViewShowing() {
        return this.showing;
    }

    private synchronized void setShowing(boolean show) {
        this.showing = show;
    }

    @Override
    public void display(Set<GameObject> gameObjects, int lives, int levelNumber) {
        SwingUtilities.invokeLater(() -> {
            this.setGameObjects(gameObjects);
            this.setStatistics(lives, levelNumber);
        });
    }

    @Override
    public Set<Integer> getPressedKeys() {
        return this.getKeysPressed();
    }
}
