package it.unibo.michelito.view.gameview.panel.impl;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import it.unibo.michelito.util.GameObject;

import javax.swing.*;

public class GameObjectRenderer {
    private static final double BASE_SCALE_FACTOR = 6.68;
    private static final double TRANSLATION_FACTOR = 3;
    private static final int BASE_SQUARE_DIMENSION = 40;
    private static final int BASE_RECTANGLE_WIDTH = 20;
    private static final int BASE_RECTANGLE_HEIGHT = 30;

    public static void render(Graphics g, GameObject gameObject, JPanel component) {
        Graphics2D g2d = (Graphics2D) g;  // Cast to Graphics2D for advanced features

        Dimension currentDimension = component.getSize();
        Dimension baseDimension = component.getPreferredSize();
        double widthScaleFactor = (currentDimension.width / (double) baseDimension.width) * BASE_SCALE_FACTOR;
        double heightScaleFactor = (currentDimension.height / (double) baseDimension.height) * BASE_SCALE_FACTOR;

        double currentX = (gameObject.position().x() + TRANSLATION_FACTOR) * widthScaleFactor;
        double currentY = (gameObject.position().y() + TRANSLATION_FACTOR) * heightScaleFactor;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // Enable anti-aliasing for smoother rendering

        final Rectangle2D square = createRectangle(currentDimension, baseDimension, currentX, currentY, BASE_SQUARE_DIMENSION, BASE_SQUARE_DIMENSION);
        final Rectangle2D rectangle = createRectangle(currentDimension, baseDimension, currentX, currentY, BASE_RECTANGLE_WIDTH, BASE_RECTANGLE_HEIGHT);
        final Ellipse2D bombCircle = createEllipse(currentDimension, baseDimension, currentX, currentY, BASE_SQUARE_DIMENSION, BASE_SQUARE_DIMENSION);
        final Ellipse2D powerUpCircle = createEllipse(currentDimension, baseDimension, currentX, currentY, BASE_SQUARE_DIMENSION, BASE_SQUARE_DIMENSION);

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
                g2d.fill(bombCircle);
                break;
            case DOOR:
                g2d.setColor(Color.MAGENTA);
                g2d.fill(rectangle);
                break;
            case FLAME:
                g2d.setColor(Color.ORANGE);
                g2d.fill(square);
                break;
            case BOMB:
                g2d.setColor(Color.BLACK);
                g2d.fill(bombCircle);
                break;
        }
    }

    private static Rectangle2D createRectangle(Dimension currentDimension, Dimension baseDimension, double currentX, double currentY, int stdRectangle, int stdRectangle2) {
        return new Rectangle2D.Double(
                (currentX - ((double) stdRectangle * (currentDimension.width / (double) baseDimension.width) / 2.0)),
                (currentY - ((double) stdRectangle2 * (currentDimension.height / (double) baseDimension.height) / 2.0)),
                stdRectangle * (currentDimension.width / (double) baseDimension.width),
                stdRectangle2 * (currentDimension.height / (double) baseDimension.height)
        );
    }

    private static Ellipse2D createEllipse(Dimension currentDimension, Dimension baseDimension, double currentX, double currentY, int stdRectangle, int stdRectangle2) {
        return new Ellipse2D.Double(
                (currentX - ((double) stdRectangle * (currentDimension.width / (double) baseDimension.width) / 2.0)),
                (currentY - ((double) stdRectangle2 * (currentDimension.height / (double) baseDimension.height) / 2.0)),
                stdRectangle * (currentDimension.width / (double) baseDimension.width),
                stdRectangle2 * (currentDimension.height / (double) baseDimension.height)
        );
    }
}
