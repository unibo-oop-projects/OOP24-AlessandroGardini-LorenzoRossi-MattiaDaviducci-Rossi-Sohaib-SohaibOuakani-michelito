package it.unibo.michelito.view.homeview;

import it.unibo.michelito.controller.homecontroller.api.ViewControllerListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class HomeView extends JFrame {
    public static final int SCALING = 4;
    private JPanel mainPanel;
    private JButton startButton;
    private JButton exitButton;

    public HomeView(ViewControllerListener controller) {
        Dimension syst = Toolkit.getDefaultToolkit().getScreenSize();
        this.setTitle("Michelito");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.mainPanel = new JPanel();
        this.startButton = new JButton("Start Game");
        this.exitButton = new JButton("Quit");
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.setLayout(new GridLayout(2, 1));
        ActionListener startButton = e -> {
            controller.switchToGame();
        };
        ActionListener exitButton = e -> {
            controller.quit();
        };
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
        this.startButton.addActionListener(startButton);
        this.exitButton.addActionListener(exitButton);
        this.mainPanel.add(this.startButton);
        this.mainPanel.add(this.exitButton);
        this.setSize(syst.width / SCALING, syst.height / SCALING);
        this.setVisible(true);
    }

    public void close() {
        this.setVisible(false);
        this.dispose();
    }
}
