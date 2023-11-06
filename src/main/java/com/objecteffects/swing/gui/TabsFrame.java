package com.objecteffects.swing.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TabsFrame {
    @SuppressWarnings("unused")
    private final static Logger log = LogManager
            .getLogger(TabsFrame.class);

    private final static JFrame frame = new JFrame("reddit");
    private final static JPanel mainPanel = new JPanel();

    final static Font fontLabel = new Font("SansSerif", Font.BOLD, 20);
    final static Font fontName = new Font("SansSerif", Font.PLAIN, 20);
    final static Color colorBg = Color.LIGHT_GRAY;

    final static Border lineBorder = LineBorder.createGrayLineBorder();
    final static Border textFieldBorder = new BevelBorder(
            BevelBorder.LOWERED);

    public static void setup() {
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setContentPane(mainPanel);
        frame.setName("frame");

        mainPanel.setBackground(colorBg);
        mainPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.setName("mainPanel");

        final JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.setBackground(colorBg);
        tabbedPane.setBorder(lineBorder);

        tabbedPane.addTab("hide posts", HidePostsPanel.setupHidePostsPanel());
        tabbedPane.addTab("add users", AddUsersPanel.setupAddUsersPanel());
        tabbedPane.addTab("messages", MessagesPanel.setupMessagesPanel());

        mainPanel.add(tabbedPane);

        frame.pack();
        frame.setVisible(true);
    }

    static void revalidate() {
        HidePostsPanel.validate();
        mainPanel.validate();
        frame.validate();
    }

    static void makeStretchy(final JComponent component) {
        component.setMaximumSize(new Dimension(Short.MAX_VALUE,
                Short.MAX_VALUE));
    }
}
