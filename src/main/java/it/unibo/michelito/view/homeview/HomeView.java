package it.unibo.michelito.view.homeview;

import it.unibo.michelito.controller.homecontroller.api.ViewControllerListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class HomeView extends JFrame {
    public static final double SCALING = 2.5;
    public static final int BUTTON_X_SCALING = 6;
    public static final int BUTTON_Y_SCALING = 16;
    private JPanel mainPanel;
    private JButton startButton;
    private JButton exitButton;
    private final Box titleBox;

    public HomeView(ViewControllerListener controller) {
        Dimension syst = Toolkit.getDefaultToolkit().getScreenSize();

        this.mainPanel = new JPanel();
        this.mainPanel.setLayout(new BoxLayout(this.mainPanel, BoxLayout.Y_AXIS));

        this.titleBox = new Box(BoxLayout.Y_AXIS);
        this.startButton = new JButton("Start Game");
        this.exitButton = new JButton("Quit");

        this.setTitle("Michelito");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.setLocationRelativeTo(null);

        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel titleLabel = new JLabel("Michelito El Esqueleto Explosivo");
        titleLabel.setFont(new Font("Papyrus", Font.BOLD, 33)); // Ingrandisci il titolo
        titlePanel.add(titleLabel);

        JPanel buttonPanel1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel buttonPanel2 = new JPanel(new FlowLayout(FlowLayout.CENTER));

        Dimension buttonSize = new Dimension(syst.width / BUTTON_X_SCALING, syst.width / BUTTON_Y_SCALING);
        this.startButton.setPreferredSize(buttonSize);
        this.exitButton.setPreferredSize(buttonSize);

        buttonPanel1.add(this.startButton);
        buttonPanel2.add(this.exitButton);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int response = JOptionPane.showConfirmDialog(
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

        this.mainPanel.add(Box.createVerticalGlue());
        this.mainPanel.add(titlePanel);
        this.mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        this.mainPanel.add(buttonPanel1);
        this.mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        this.mainPanel.add(buttonPanel2);
        this.mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        this.mainPanel.add(Box.createVerticalGlue());

        this.startButton.addActionListener(e -> controller.switchToGame());
        this.exitButton.addActionListener(e -> controller.quit());

        this.setSize((int) (syst.width / SCALING), (int) (syst.height / SCALING));
        this.mainPanel.setBackground(Color.cyan);
        buttonPanel1.setBackground(Color.cyan);
        buttonPanel2.setBackground(Color.cyan);
        titlePanel.setBackground(Color.cyan);

        this.startButton.setForeground(Color.green);
        this.exitButton.setForeground(Color.red);

        this.setVisible(true);
    }

    public void close() {
        this.setVisible(false);
        this.dispose();
    }
}
