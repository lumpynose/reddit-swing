package com.objecteffects.swing.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.text.JTextComponent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.objecteffects.swing.main.AppConfig;

class ListenerAddUser implements ActionListener {
    private final static Logger log = LogManager
            .getLogger(ListenerAddUser.class);

    @Override
    public void actionPerformed(final ActionEvent e) {
        log.debug("clicked: {}", e.getActionCommand());

        final List<JTextComponent> textFields = AddUsersPanel.getTextFields();

        for (final JTextComponent textField : textFields) {
            if (!textField.getText().isEmpty()) {
                final String name = textField.getText();
                log.debug(name);

                AppConfig.addName(name);
//                TabsFrame.addRow(name);

                textField.setText("");
            }

            AppConfig.saveConfig();

//            TabsFrame.revalidate();
        }
    }
}
