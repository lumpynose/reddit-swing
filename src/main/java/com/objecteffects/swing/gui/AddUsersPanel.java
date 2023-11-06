package com.objecteffects.swing.gui;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

class AddUsersPanel {
    private final static List<JTextComponent> textFieldList = new ArrayList<>();
    private final static int textFieldCount = 5;

    static JComponent setupAddUsersPanel() {
        final JComponent panel = Box.createVerticalBox();

        panel.setBackground(TabsFrame.colorBg);

        for (int i = 0; i < textFieldCount; i++) {
            final JTextComponent textField = new JTextField(20);
            textField.setBorder(TabsFrame.textFieldBorder);
            textField.setFont(TabsFrame.fontName);

            panel.add(textField);
            panel.add(Box.createVerticalStrut(4));

            textFieldList.add(textField);
        }

        final AbstractButton addUserButton = new JButton("add user");
        addUserButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addUserButton.addActionListener(new ListenerAddUser());

        panel.add(addUserButton);

        return panel;
    }

    static List<JTextComponent> getTextFields() {
        return textFieldList;
    }
}
