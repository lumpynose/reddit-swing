package com.objecteffects.swing.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.text.JTextComponent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RedditGuiGroupLayout {
    @SuppressWarnings("unused")
    private final static Logger log = LogManager
            .getLogger(RedditGuiGroupLayout.class);

    private final static JFrame frame = new JFrame("reddit");
    private final static JComponent mainPanel = new JPanel();

    private final static Font fontLabel = new Font("SansSerif", Font.BOLD, 20);
    private final static Font fontName = new Font("SansSerif", Font.PLAIN, 20);
    private final static Color colorBg = Color.LIGHT_GRAY;
//    private final static Color colorBorder = Color.GRAY;

//    private final static LineBorder lineBorder = new LineBorder(colorBorder, 1);
    private final static Border lineBorder = LineBorder.createGrayLineBorder();

    private final static List<JTextComponent> textFields = new ArrayList<>();

    public static void setup(final List<String> users) {
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setContentPane(mainPanel);
        frame.setName("frame");

        mainPanel.setBackground(colorBg);
        mainPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.setName("mainPanel");
        mainPanel.setBorder(lineBorder);

//        final JPanel panel = groupLayoutSetup();
//        final JPanel panel = boxLayoutSetup();

        mainPanel.add(hideUsersSetupGroup(users));
        mainPanel.add(Box.createHorizontalGlue());
        mainPanel.add(addUserSetup(users.size()));

        frame.pack();

        frame.setVisible(true);
    }

    private static JComponent hideUsersSetupGroup(
            final List<String> users) {
        final JComponent buttonPanel = new JPanel();

        final GroupLayout layout = new GroupLayout(buttonPanel);
        buttonPanel.setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        // row layouts
        final SequentialGroup hLabelsLayout = layout.createSequentialGroup();
        final ParallelGroup vLabelsLayout = layout
                .createParallelGroup(GroupLayout.Alignment.BASELINE);

        // pane layouts
        final SequentialGroup horizLayout = layout.createSequentialGroup();
        final ParallelGroup vertLayout = layout
                .createParallelGroup(GroupLayout.Alignment.BASELINE);

        final JComponent nameLabel = new JLabel("name");
        nameLabel.setFont(fontLabel);
        nameLabel.setBorder(lineBorder);
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        hLabelsLayout.addComponent(nameLabel);
        vLabelsLayout.addComponent(nameLabel);

        final JComponent upLabel = new JLabel("up vote");
        upLabel.setFont(fontLabel);
        upLabel.setBorder(lineBorder);
        upLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        hLabelsLayout.addComponent(upLabel);
        vLabelsLayout.addComponent(upLabel);

        final JComponent downLabel = new JLabel("down vote");
        downLabel.setFont(fontLabel);
        downLabel.setBorder(lineBorder);
        downLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        hLabelsLayout.addComponent(downLabel);
        vLabelsLayout.addComponent(downLabel);

        final JComponent deleteLabel = new JLabel("delete");
        deleteLabel.setFont(fontLabel);
        deleteLabel.setBorder(lineBorder);
        deleteLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        hLabelsLayout.addComponent(deleteLabel);
        vLabelsLayout.addComponent(deleteLabel);

        SequentialGroup hRowLayoutLast;
        ParallelGroup vRowLayoutLast;

        hRowLayoutLast = horizLayout.addGroup(hLabelsLayout);
        vRowLayoutLast = vertLayout.addGroup(vLabelsLayout);

        // row layouts
        SequentialGroup hRowLayout;
        ParallelGroup vRowLayout;

        for (final String user : users) {
            hRowLayout = layout.createSequentialGroup();
            vRowLayout = layout
                    .createParallelGroup(GroupLayout.Alignment.BASELINE);

            final JComponent nameCheckBox = new JCheckBox(user);
            nameCheckBox.setFont(fontName);
            nameCheckBox.setOpaque(false);
            hRowLayout.addComponent(nameCheckBox);
            vRowLayout.addComponent(nameCheckBox);

            final ButtonGroup buttonGroup = new ButtonGroup();

            final JRadioButton upButton = new JRadioButton();
            upButton.setOpaque(false);
            buttonGroup.add(upButton);
            hRowLayout.addComponent(upButton);
            vRowLayout.addComponent(upButton);

            final JRadioButton downButton = new JRadioButton();
            downButton.setOpaque(false);
            buttonGroup.add(downButton);
            hRowLayout.addComponent(downButton);
            vRowLayout.addComponent(downButton);

            final JRadioButton deleteButton = new JRadioButton();
            deleteButton.setOpaque(false);
            buttonGroup.add(deleteButton);
            hRowLayout.addComponent(deleteButton);
            vRowLayout.addComponent(deleteButton);

            hRowLayoutLast = horizLayout.addGroup(hRowLayout);
            vRowLayoutLast = vertLayout.addGroup(vRowLayout);
        }

        layout.setHorizontalGroup(hRowLayoutLast);
        layout.setVerticalGroup(vRowLayoutLast);

        return buttonPanel;
    }

    private static JComponent addUserSetup(final int rows) {
        final JComponent panel = Box.createVerticalBox();
        panel.setBorder(lineBorder);
        panel.setBackground(colorBg);

        final JComponent label = new JLabel("add users");
        label.setFont(fontLabel);
        label.setBorder(lineBorder);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(label);

        final JComponent boxPanel = Box.createVerticalBox();
        boxPanel.setBorder(lineBorder);

        for (int i = 0; i < rows; i++) {
            final JTextComponent textField = new JTextField(20);
            textField.setBorder(lineBorder);
            textField.setFont(fontName);

            boxPanel.add(textField);

            textFields.add(textField);
        }

        panel.add(boxPanel);

        final AbstractButton addUserButton = new JButton("add user");
        addUserButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addUserButton.addActionListener(new ListenerAddUser());

        panel.add(addUserButton);

        return panel;
    }

    @SuppressWarnings("unused")
    private static void makeStretchy(final JComponent component) {
        component.setMaximumSize(new Dimension(Short.MAX_VALUE,
                Short.MAX_VALUE));
    }

    public static List<JTextComponent> getTextFields() {
        return textFields;
    }

//    protected static ImageIcon createImageIcon(final String path) {
//        final java.net.URL imgURL = RedditGui.class.getResource(path);
//
//        if (imgURL != null) {
//            return new ImageIcon(imgURL, null);
//        }
//
//        System.err.println("Couldn't find file: " + path);
//
//        return null;
//    }

}
