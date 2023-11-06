package com.objecteffects.swing.gui;

//Java Program to illustrate the GroupLayout class
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

//creating a class GroupLayoutDemo
public class GroupLayoutDemo {
    private JFrame mainFrame;
    private JLabel headerLabel, statusLabel;
    private JPanel controlPanel;

    public GroupLayoutDemo() {
        prepareGUI();
    }

    public static void main(final String[] args) {
        final GroupLayoutDemo GroupLayoutDemo = new GroupLayoutDemo();

        GroupLayoutDemo.showGroupLayoutDemo();
    }

    private void prepareGUI() {
        this.mainFrame = new JFrame("Java GroupLayout Examples");

        this.mainFrame.setSize(400, 400);
        this.mainFrame.setLayout(new GridLayout(3, 1));

        this.headerLabel = new JLabel("", SwingConstants.CENTER);

        this.statusLabel = new JLabel("", SwingConstants.CENTER);
        this.statusLabel.setSize(350, 100);

        this.mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(final WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        this.controlPanel = new JPanel();

        this.controlPanel.setLayout(new FlowLayout());

        this.mainFrame.add(this.headerLabel);
        this.mainFrame.add(this.controlPanel);
        this.mainFrame.add(this.statusLabel);
        this.mainFrame.setVisible(true);
    }

    private void showGroupLayoutDemo() {
        this.headerLabel.setText("Layout in action: GroupLayout");

        final JPanel panel = new JPanel();
        panel.setSize(200, 200);

        final GroupLayout layout = new GroupLayout(panel);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        final JButton btn1 = new JButton("Button 1");

        final JButton btn2 = new JButton("Button 2");

        final JButton btn3 = new JButton("Button 3");

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addComponent(btn1)
                .addGroup(layout.createSequentialGroup()
                        .addGroup(layout
                                .createParallelGroup(
                                        GroupLayout.Alignment.LEADING)
                                .addComponent(btn2)
                                .addComponent(btn3))));

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(btn1)
                .addComponent(btn2)
                .addComponent(btn3));

        panel.setLayout(layout);

        this.controlPanel.add(panel);

        this.mainFrame.setVisible(true);
    }
}
