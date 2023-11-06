package com.objecteffects.swing.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

class ListenerDownVote implements ActionListener {
    private final static Logger log = LogManager
            .getLogger(ListenerDownVote.class);

    private final Set<String> downVoteUsers =
            ProcessUsers.getDownVoteUsers();
    private final Set<String> upVoteUsers =
            ProcessUsers.getUpVoteUsers();

    @Override
    public void actionPerformed(final ActionEvent e) {
        log.debug("actionPerformed: actionCommand: {}",
                e.getActionCommand());

        this.downVoteUsers.add(e.getActionCommand());
        this.upVoteUsers.remove(e.getActionCommand());
    }
}
