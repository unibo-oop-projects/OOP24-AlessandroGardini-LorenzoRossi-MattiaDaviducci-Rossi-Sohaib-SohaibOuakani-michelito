package it.unibo.michelito.view.gameview.frame.impl;

import it.unibo.michelito.util.GameObject;
import it.unibo.michelito.view.gameview.panel.impl.GamePanel;
import it.unibo.michelito.view.gameview.frame.api.GameView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Set;

public class GameViewImpl extends JFrame implements GameView {
    private boolean showing = true;
    private final double aspectRatio;
    private final GamePanel gamePanel = new GamePanel();

    public GameViewImpl() {
        setTitle("Michelito");

        add(gamePanel, BorderLayout.CENTER);

        //set frame spawn
        setLocationRelativeTo(null);

        //set custom resizing
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

        //set custom quit closing action
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
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
                    setShowingFalse();
                }
            }
        });
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

    @Override
    public void display(Set<GameObject> gameObjects, int lives, int levelNumber) {
        SwingUtilities.invokeLater(() -> gamePanel.display(gameObjects, lives, levelNumber));
    }

    @Override
    public Set<Integer> getPressedKeys() {
        return gamePanel.getKeysPressed();
    }

    private synchronized void setShowingFalse() {
        this.showing = false;
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
