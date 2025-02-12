package it.unibo.michelito.view.homeview;

import it.unibo.michelito.controller.homecontroller.api.ViewControllerListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Represents the view of the home menu.
 */
public class HomeView extends JFrame {
    public static final double SCALING = 2.5;
    public static final int BUTTON_X_SCALING = 6;
    public static final int BUTTON_Y_SCALING = 16;

    /**
     * Creates a new instance of {@link HomeView}.
     *
     * @param controller the controller to be used.
     */
    public HomeView(final ViewControllerListener controller) {
        final Dimension syst = Toolkit.getDefaultToolkit().getScreenSize();

        final JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        final JButton startButton = new JButton("Start Game");
        final JButton exitButton = new JButton("Quit");

        this.setTitle("Michelito");
        this.setResizable(false);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.setLocationRelativeTo(null);

        final JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        final JLabel titleLabel = new JLabel("Michelito El Esqueleto Explosivo");
        titleLabel.setFont(new Font("Papyrus", Font.BOLD, 33)); // Ingrandisci il titolo
        titlePanel.add(titleLabel);

        final JPanel buttonPanel1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        final JPanel buttonPanel2 = new JPanel(new FlowLayout(FlowLayout.CENTER));

        final Dimension buttonSize = new Dimension(syst.width / BUTTON_X_SCALING, syst.width / BUTTON_Y_SCALING);
        startButton.setPreferredSize(buttonSize);
        exitButton.setPreferredSize(buttonSize);

        buttonPanel1.add(startButton);
        buttonPanel2.add(exitButton);

        this.addWindowListener(new WindowAdapter() {
            /**
             * {@inheritDoc}
             */
            @Override
            public void windowClosing(WindowEvent e) {
                final int response = JOptionPane.showConfirmDialog(
                        HomeView.this,
                        "Are you sure you want to exit?",
                        "Confirm",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );
                if (response == JOptionPane.YES_OPTION) {
                    controller.quit();
                }
            }
        });

        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(titlePanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(buttonPanel1);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(buttonPanel2);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(Box.createVerticalGlue());

        startButton.addActionListener(e -> controller.switchToGame());
        exitButton.addActionListener(e -> controller.quit());

        this.setSize((int) (syst.width / SCALING), (int) (syst.height / SCALING));
        mainPanel.setBackground(Color.cyan);
        buttonPanel1.setBackground(Color.cyan);
        buttonPanel2.setBackground(Color.cyan);
        titlePanel.setBackground(Color.cyan);

        startButton.setForeground(Color.green);
        exitButton.setForeground(Color.red);

        this.setVisible(true);
    }

    /**
     * Closes the view.
     */
    public void close() {
        this.setVisible(false);
        this.dispose();
    }
}
