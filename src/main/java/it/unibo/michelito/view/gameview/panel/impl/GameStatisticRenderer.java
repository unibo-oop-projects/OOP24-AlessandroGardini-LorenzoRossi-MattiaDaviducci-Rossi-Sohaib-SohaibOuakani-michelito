package it.unibo.michelito.view.gameview.panel.impl;

import javax.swing.*;
import java.awt.*;

public class GameStatisticRenderer {
    public static final int OFFSET_FROM_VERTICAL_LEFT_CORNER = 10;
    public static final int OFFSET_FROM_VERTICAL_RIGHT_CORNER = 90;
    public static final int FONT_SIZE = 18;
    public static final int OFFSET_FROM_HORIZONTAL_CORNER = 25;

    public static void render(Graphics g, JPanel component, int lives, int levelNumber) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // Set rendering hints for better text quality

        var baseDimension = component.getPreferredSize();
        float widthScale = component.getWidth() / (1.0f * baseDimension.width);
        float heightScale = component.getHeight() / (1.0f * baseDimension.height);

        g2d.setFont(new Font("Serif", Font.BOLD, Math.round(FONT_SIZE * widthScale)));
        g2d.setColor(Color.RED);

        var livesText = "REMAINING LIVES: ";
        g2d.drawString("LIVES: " + "❤ ".repeat(lives), OFFSET_FROM_VERTICAL_LEFT_CORNER * widthScale, OFFSET_FROM_HORIZONTAL_CORNER * heightScale);
        g2d.drawString("LEVEL: " + levelNumber, (baseDimension.width - OFFSET_FROM_VERTICAL_RIGHT_CORNER) * widthScale, OFFSET_FROM_HORIZONTAL_CORNER * heightScale);
        //g2d.drawString("LIVES: " + "\uD83D\uDCA3 ".repeat(lives), OFFSET_FROM_VERTICAL_LEFT_CORNER * widthScale, OFFSET_FROM_HORIZONTAL_CORNER * heightScale);
    }
}
