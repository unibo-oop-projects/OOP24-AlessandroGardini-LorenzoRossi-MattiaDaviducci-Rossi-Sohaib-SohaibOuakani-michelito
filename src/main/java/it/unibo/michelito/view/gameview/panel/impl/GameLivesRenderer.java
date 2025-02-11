package it.unibo.michelito.view.gameview.panel.impl;

import javax.swing.*;
import java.awt.*;

public class GameLivesRenderer {

    public static void render(Graphics g, JPanel component, int lives) {
        Graphics2D g2d = (Graphics2D) g;

        component.getPreferredSize();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // Set rendering hints for better text quality

        g2d.setFont(new Font("Serif", Font.BOLD, 18));
        g2d.setColor(Color.RED);
        g2d.drawString("LIVES: " + lives, 18, 18);
    }
}
