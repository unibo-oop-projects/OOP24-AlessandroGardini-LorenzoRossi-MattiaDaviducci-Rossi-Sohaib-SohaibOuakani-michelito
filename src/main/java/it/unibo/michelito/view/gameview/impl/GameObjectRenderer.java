package it.unibo.michelito.view.gameview.impl;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import it.unibo.michelito.util.GameObject;

public class GameObjectRenderer {
    private static final double OBJECT_SPACING = 6.8; // Space between objects

    public static void render(Graphics g, GameObject gameObject) {
        Graphics2D g2d = (Graphics2D) g;  // Cast to Graphics2D for advanced features

        double x = gameObject.position().x() * OBJECT_SPACING;
        double y = gameObject.position().y() * OBJECT_SPACING;
        int size = 40; // Assuming all objects have the same size for simplicity
        double entityWSize = 20; // Assuming all objects have the same size for simplicity
        double entityHSize = 30; // Assuming all objects have the same size for simplicity

        // Enable anti-aliasing for smoother rendering
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        final Rectangle2D rectangle = new Rectangle2D.Double(x - (entityWSize / 2.0), y - (entityHSize / 2.0), entityWSize, entityHSize);
        final Rectangle2D square = new Rectangle2D.Double(x - (size / 2.0), y - (size / 2.0), size, size);

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
                g2d.fill(rectangle);  // Representing enemy as a red circle
                break;
            case POWERUP:
                g2d.setColor(Color.YELLOW);
                g2d.fill(rectangle);  // Representing powerup as a yellow square
                break;
            case DOOR:
                g2d.setColor(Color.CYAN);
                g2d.fill(rectangle);  // Representing door as a cyan square
                break;
            case FLAME:
                g2d.setColor(Color.ORANGE);
                g2d.fill(square);  // Representing flame as an orange circle
                break;
            case BOMB:
                g2d.setColor(Color.BLACK);
                g2d.fill(square);  // Representing bomb as a black circle
                break;
        }

        String text = Integer.toString((int)gameObject.position().x()) + " " + Integer.toString((int)gameObject.position().y()) ;  // Text is the object's type (you can change this)
        g.setColor(Color.BLACK);  // Text color (white)
        g.setFont(new Font("Arial", Font.BOLD, 12));

        // Calculate position to center the text within the object
        FontMetrics metrics = g2d.getFontMetrics();
        int textWidth = metrics.stringWidth(text);
        int textHeight = metrics.getHeight();

        // Draw text in the center of the object
        //g.drawString(text, (int) (x + (size - textWidth) / 2),(int)(y + (size + textHeight) / 2));
    }
}
