package com.objecteffects.swing.gui;

import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

class MessagesPanel {
    private final static JTextArea textArea = new JTextArea();

    static JComponent setupMessagesPanel() {
        textArea.setFont(TabsFrame.fontName);
        textArea.setEditable(false);

        final JScrollPane scrollPane = new JScrollPane(textArea);

        scrollPane.setVerticalScrollBarPolicy(
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        return scrollPane;
    }

    static void append(final String string) {
        textArea.append(string);
    }
}
