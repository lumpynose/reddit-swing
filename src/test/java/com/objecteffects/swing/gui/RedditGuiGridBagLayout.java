package com.objecteffects.swing.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.text.JTextComponent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RedditGuiGridBagLayout {
    private final static Logger log = LogManager
            .getLogger(RedditGuiGridBagLayout.class);

    private final static JFrame frame = new JFrame("reddit");
    private final static JPanel mainPanel = new JPanel(new GridBagLayout());

    private final static Font fontLabel = new Font("SansSerif", Font.BOLD, 20);
    private final static Font fontName = new Font("SansSerif", Font.PLAIN, 20);
    private final static Color colorBg = Color.LIGHT_GRAY;

    private final static Border lineBorder = LineBorder.createGrayLineBorder();

    private final static List<JTextComponent> textFields = new ArrayList<>();
    private final static List<AbstractButton> buttons = new ArrayList<>();
    private final static List<ButtonGroup> buttonGroups = new ArrayList<>();

    public static void setup(final List<String> users) {
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setContentPane(mainPanel);
        frame.setName("frame");

        mainPanel.setBackground(colorBg);
        mainPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.setName("mainPanel");
        mainPanel.setBorder(lineBorder);

        final GridBagConstraints gbc = new GridBagConstraints();

//        gbc.gridx = 0;
//        gbc.gridy = 0;

        gbc.gridx = 0;
        gbc.gridy = 0;

//        JPanel subPanel = hideUsersSetupGridBag(users);
        hideUsersSetupGridBag(users, mainPanel, gbc);

//        mainPanel.add(subPanel, gbc);

//        mainPanel.add(addUserSetup(users.size(), subPanel), gbc);
        addUserSetup(users.size(), mainPanel, gbc);

        final JTextArea textArea = new JTextArea(20, 0);
        final JScrollPane scrollPane = new JScrollPane(textArea);

        textArea.setFont(fontName);

        log.debug("gridy: {}", gbc.gridy);
        gbc.gridx = 0;
        gbc.gridy = users.size() + 2;
        gbc.gridwidth = 6;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        log.debug("gridy: {}", gbc.gridy);

        mainPanel.add(scrollPane, gbc);

        frame.pack();

        frame.setVisible(true);
    }

    private static JPanel hideUsersSetupGridBag(
            final List<String> users, final JPanel panel,
            final GridBagConstraints gbc) {
//        final JComponent panel = new JPanel(new GridBagLayout());

        panel.setOpaque(false);

        int col = 0;

        final JComponent nameLabel = new JLabel("name");
        nameLabel.setFont(fontLabel);
        gbc.gridx = col++;
        gbc.insets = new Insets(0, 8, 0, 8);
        gbc.anchor = GridBagConstraints.LINE_START;
        panel.add(nameLabel, gbc);

        final JComponent hideLabel = new JLabel("hide");
        hideLabel.setFont(fontLabel);
        gbc.gridx = col++;
        panel.add(hideLabel, gbc);

        final JComponent upLabel = new JLabel("up vote");
        upLabel.setFont(fontLabel);
        gbc.gridx = col++;
        panel.add(upLabel, gbc);

        final JComponent downLabel = new JLabel("down vote");
        downLabel.setFont(fontLabel);
        gbc.gridx = col++;
        panel.add(downLabel, gbc);

        final JComponent deleteLabel = new JLabel("delete");
        deleteLabel.setFont(fontLabel);
        gbc.gridx = col++;
        panel.add(deleteLabel, gbc);

        int row = 1;

        for (final String user : users) {
            col = 0;

            final JLabel name = new JLabel(user);
            name.setFont(fontName);
            name.setOpaque(false);
            gbc.gridx = col++;
            gbc.gridy = row;
            gbc.anchor = GridBagConstraints.LINE_START;
            panel.add(name, gbc);

            final AbstractButton hideCheckBox = new JCheckBox();
            hideCheckBox.setOpaque(false);
            hideCheckBox.setActionCommand(user);
            hideCheckBox.addActionListener(new ListenerHide());
            gbc.gridx = col++;
            gbc.gridy = row;
            panel.add(hideCheckBox, gbc);
            buttons.add(hideCheckBox);

            final ButtonGroup buttonGroup = new ButtonGroup();

            final AbstractButton upButton = new JRadioButton();
            upButton.setOpaque(false);
            upButton.addActionListener(new ListenerUpVote());
            upButton.setActionCommand(user);
            buttonGroup.add(upButton);
            gbc.gridx = col++;
            gbc.gridy = row;
            panel.add(upButton, gbc);
            buttons.add(upButton);

            final AbstractButton downButton = new JRadioButton();
            downButton.setOpaque(false);
            downButton.addActionListener(new ListenerDownVote());
            downButton.setActionCommand(user);
            buttonGroup.add(downButton);
            gbc.gridx = col++;
            gbc.gridy = row;
            panel.add(downButton, gbc);
            buttons.add(downButton);

            final AbstractButton deleteButton = new JRadioButton();
            deleteButton.setOpaque(false);
            deleteButton.addActionListener(new ListenerRemove());
            deleteButton.setActionCommand(user);
            buttonGroup.add(deleteButton);
            gbc.gridx = col++;
            gbc.gridy = row;
            panel.add(deleteButton, gbc);
            buttons.add(deleteButton);

            buttonGroups.add(buttonGroup);

            row++;
        }

        final AbstractButton processButton = new JButton("process");
        processButton.addActionListener(new ListenerProcess());

        gbc.gridwidth = 5;
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(processButton, gbc);

        return panel;
    }

    private static JComponent addUserSetup(final int rows,
            final JPanel subPanel, final GridBagConstraints gbc) {
//        final JComponent panel = Box.createVerticalBox();
//        final JComponent panel = new JPanel(new GridBagLayout());

//        final GridBagConstraints gbc = new GridBagConstraints();

        final JComponent panel = subPanel;

        panel.setBackground(colorBg);

        final JComponent label = new JLabel("add users");
        label.setFont(fontLabel);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        gbc.gridx = 5;
        gbc.gridy = 0;
        panel.add(label, gbc);

//        final JComponent boxPanel = Box.createVerticalBox();
//        boxPanel.setBorder(lineBorder);

        int row = 1;

        for (int i = 0; i < rows; i++) {
            final JTextComponent textField = new JTextField(20);
            textField.setBorder(lineBorder);
            textField.setFont(fontName);

//            boxPanel.add(textField);
            gbc.gridx = 5;
            gbc.gridy = row++;
            panel.add(textField, gbc);

            textFields.add(textField);
        }

//        gbc.gridy = row++;
//        panel.add(boxPanel, gbc);

        final AbstractButton addUserButton = new JButton("add user");
        addUserButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addUserButton.addActionListener(new ListenerAddUser());

        gbc.gridy = row++;
        panel.add(addUserButton, gbc);

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

    public static List<AbstractButton> getButtons() {
        return buttons;
    }

    public static List<ButtonGroup> getButtonGroups() {
        return buttonGroups;
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
