package com.objecteffects.swing.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.AbstractButton;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

class ListenerHide implements ActionListener {
    private final static Logger log = LogManager
            .getLogger(ListenerHide.class);

    private final Set<String> hideUsers = ProcessUsers.getHideUsers();

    @Override
    public void actionPerformed(final ActionEvent e) {
        final AbstractButton cb = (AbstractButton) e.getSource();

        if (cb.isSelected()) {
            log.debug("selected: {}", e.getActionCommand());
            this.hideUsers.add(e.getActionCommand());
        }
        else {
            log.debug("deselected: {}", e.getActionCommand());
            this.hideUsers.remove(e.getActionCommand());
        }
    }
}
