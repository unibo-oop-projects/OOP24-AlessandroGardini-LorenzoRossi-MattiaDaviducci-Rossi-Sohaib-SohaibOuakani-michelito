package it.unibo.michelito.view.gameview.impl;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import it.unibo.michelito.util.GameObject;

import javax.swing.*;

public class GameObjectRenderer {
    private static final double BASE_SCALE_FACTOR = 6.7;
    private static final double TRANSLATION_FACTOR = 3;
    private static final int STD_RECTANGLE = 40;
    private static final int ENTITY_WIDTH = 20;
    private static final int ENTITY_HEIGHT = 30;

    public static void render(Graphics g, GameObject gameObject, JPanel component) {
        Graphics2D g2d = (Graphics2D) g;  // Cast to Graphics2D for advanced features

        Dimension currentDimension = component.getSize();
        Dimension baseDimension = component.getPreferredSize();
        double widthScaleFactor = (currentDimension.width / (double) baseDimension.width) * BASE_SCALE_FACTOR;
        double heightScaleFactor = (currentDimension.height / (double) baseDimension.height) * BASE_SCALE_FACTOR;

        double width = (gameObject.position().x() + TRANSLATION_FACTOR) * widthScaleFactor;
        double height = (gameObject.position().y() + TRANSLATION_FACTOR) * heightScaleFactor;

        // Enable anti-aliasing for smoother rendering
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        double scaledSquareWidth = STD_RECTANGLE * widthScaleFactor;
        double scaledSquareHeight = STD_RECTANGLE * heightScaleFactor;
        final Rectangle2D rectangle = new Rectangle2D.Double(width - (ENTITY_WIDTH / 2.0), height - (ENTITY_HEIGHT / 2.0), ENTITY_WIDTH, ENTITY_HEIGHT);
        final Rectangle2D square = new Rectangle2D.Double(width - (scaledSquareWidth / 2.0), height - (scaledSquareHeight / 2.0), scaledSquareWidth, scaledSquareHeight);
        final Ellipse2D ellipse = new Ellipse2D.Double(width - (STD_RECTANGLE / 2.0), height - (STD_RECTANGLE / 2.0), STD_RECTANGLE, STD_RECTANGLE);

        // Draw based on the object type
        switch (gameObject.objectType()) {
            case PLAYER:
                g2d.setColor(Color.GREEN);
                g2d.fill(rectangle);
                break;
            case BOX:
                g2d.setColor(Color.CYAN);
                g2d.fill(square);
                break;
            case WALL:
                g2d.setColor(Color.GRAY);
                g2d.fill(square);
                break;
            case ENEMY:
                g2d.setColor(Color.RED);
                g2d.fill(rectangle);
                break;
            case POWERUP:
                g2d.setColor(Color.YELLOW);
                g2d.fill(ellipse);
                break;
            case DOOR:
                g2d.setColor(Color.CYAN);
                g2d.fill(rectangle);
                break;
            case FLAME:
                g2d.setColor(Color.ORANGE);
                g2d.fill(square);
                break;
            case BOMB:
                g2d.setColor(Color.BLACK);
                g2d.fill(ellipse);
                break;
        }
    }
}
