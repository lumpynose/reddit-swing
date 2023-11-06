package com.objecteffects.swing.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.objecteffects.swing.main.AppConfig;

class HidePostsPanel {
    private final static Logger log = LogManager
            .getLogger(HidePostsPanel.class);

    private final static JPanel hidePostsPanel =
            new JPanel(new GridBagLayout());
    private final static GridBagConstraints hidePostsGbc =
            new GridBagConstraints();

    private final static List<AbstractButton> checkBoxList = new ArrayList<>();
    private final static List<ButtonGroup> buttonGroupList = new ArrayList<>();

    @SuppressWarnings("boxing")
    static JComponent setupHidePostsPanel() {
        hidePostsPanel.setBackground(TabsFrame.colorBg);

        int col = 0;
        int row = 0;

        hidePostsGbc.gridy = row;
        hidePostsGbc.insets = new Insets(0, 8, 0, 8);

        final JComponent nameLabel = new JLabel("name");
        nameLabel.setFont(TabsFrame.fontLabel);
        hidePostsGbc.gridx = col++;
        hidePostsGbc.anchor = GridBagConstraints.LINE_START;
        hidePostsPanel.add(nameLabel, hidePostsGbc);

        hidePostsGbc.anchor = GridBagConstraints.CENTER;

        final JComponent hideLabel = new JLabel("hide");
        hideLabel.setFont(TabsFrame.fontLabel);
        hidePostsGbc.gridx = col++;
        hidePostsPanel.add(hideLabel, hidePostsGbc);

        final JComponent deleteLabel = new JLabel("remove");
        deleteLabel.setFont(TabsFrame.fontLabel);
        hidePostsGbc.gridx = col++;
        hidePostsPanel.add(deleteLabel, hidePostsGbc);

        final JComponent upLabel = new JLabel("up vote");
        upLabel.setFont(TabsFrame.fontLabel);
        hidePostsGbc.gridx = col++;
        hidePostsPanel.add(upLabel, hidePostsGbc);

        final JComponent downLabel = new JLabel("down vote");
        downLabel.setFont(TabsFrame.fontLabel);
        hidePostsGbc.gridx = col++;
        hidePostsPanel.add(downLabel, hidePostsGbc);

        row = addUsers();

        final AbstractButton processButton = new JButton("process");
        processButton.addActionListener(new ListenerProcess());

        hidePostsGbc.weighty = 1.0;
        hidePostsGbc.gridwidth = 5;
        hidePostsGbc.gridx = 0;
        hidePostsGbc.gridy = row * 10;
        hidePostsGbc.fill = GridBagConstraints.HORIZONTAL;
        hidePostsPanel.add(processButton, hidePostsGbc);

        final LayoutManager lm = hidePostsPanel.getLayout();
        final GridBagConstraints gbc =
                ((GridBagLayout) lm).getConstraints(hidePostsPanel);
        log.debug("layout manager: {}, {}", gbc, gbc.gridy);

        return hidePostsPanel;
    }

    private static int addUsers() {
        int row = 1;

        final List<String> users = AppConfig.getNameList();

        checkBoxList.clear();
        buttonGroupList.clear();

        for (final String user : users) {
            hidePostsGbc.gridy = row++;

            addRow(user);
        }

        return row;
    }

    static void addRow(final String user) {
        int col = 0;

        final JLabel name = new JLabel(user);
        name.setFont(TabsFrame.fontName);
        name.setOpaque(false);
        hidePostsGbc.gridx = col++;
        hidePostsGbc.anchor = GridBagConstraints.LINE_START;
        hidePostsPanel.add(name, hidePostsGbc);

        hidePostsGbc.anchor = GridBagConstraints.CENTER;

        final AbstractButton hideCheckBox = new JCheckBox();
        hideCheckBox.setOpaque(false);
        hideCheckBox.setActionCommand(user);
        hideCheckBox.addActionListener(new ListenerHide());
        hidePostsGbc.gridx = col++;
        hidePostsPanel.add(hideCheckBox, hidePostsGbc);
        checkBoxList.add(hideCheckBox);

        final AbstractButton deleteCheckBox = new JCheckBox();
        deleteCheckBox.setOpaque(false);
        deleteCheckBox.setActionCommand(user);
        deleteCheckBox.addActionListener(new ListenerRemove());
        hidePostsGbc.gridx = col++;
        hidePostsPanel.add(deleteCheckBox, hidePostsGbc);
        checkBoxList.add(deleteCheckBox);

        final ButtonGroup buttonGroup = new ButtonGroup();

        final AbstractButton upButton = new JRadioButton();
        upButton.setOpaque(false);
        upButton.addActionListener(new ListenerUpVote());
        upButton.setActionCommand(user);
        buttonGroup.add(upButton);
        hidePostsGbc.gridx = col++;
        hidePostsPanel.add(upButton, hidePostsGbc);

        final AbstractButton downButton = new JRadioButton();
        downButton.setOpaque(false);
        downButton.addActionListener(new ListenerDownVote());
        downButton.setActionCommand(user);
        buttonGroup.add(downButton);
        hidePostsGbc.gridx = col++;
        hidePostsPanel.add(downButton, hidePostsGbc);

        buttonGroupList.add(buttonGroup);
    }

    static void validate() {
        hidePostsPanel.validate();
    }

    static List<AbstractButton> getCheckBoxes() {
        return checkBoxList;
    }

    static List<ButtonGroup> getButtonGroups() {
        return buttonGroupList;
    }
}
