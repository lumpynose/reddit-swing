package com.objecteffects.swing.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.LineBorder;

public class Layouts {
    @SuppressWarnings("unused")
    private static JPanel boxLayoutSetup(final List<String> users) {
        final JPanel panel = new JPanel();

        final LineBorder border = new LineBorder(Color.BLACK);

        final BoxLayout layout = new BoxLayout(panel, BoxLayout.X_AXIS);
        panel.setLayout(layout);

        panel.setMaximumSize(new Dimension(Short.MAX_VALUE,
                Short.MAX_VALUE));
        panel.setBorder(border);

        final JPanel namePanel = new JPanel();
        namePanel.setMaximumSize(new Dimension(Short.MAX_VALUE,
                Short.MAX_VALUE));
        namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.Y_AXIS));
        namePanel.setBorder(border);

        for (final String user : users) {
            final JCheckBox nameCheckBox = new JCheckBox(user);
            nameCheckBox.setMaximumSize(new Dimension(Short.MAX_VALUE,
                    Short.MAX_VALUE));

            namePanel.add(nameCheckBox);
        }

        panel.add(namePanel);

        final JPanel upPanel = new JPanel();
        upPanel.setMaximumSize(new Dimension(Short.MAX_VALUE,
                Short.MAX_VALUE));
        upPanel.setLayout(new BoxLayout(upPanel, BoxLayout.Y_AXIS));
        upPanel.setBorder(border);

        final ArrayList<JRadioButton> upButtons = new ArrayList<>();

        for (final String user : users) {
            final JRadioButton upButton = new JRadioButton("Up");
            upButton.setMaximumSize(new Dimension(Short.MAX_VALUE,
                    Short.MAX_VALUE));

            upPanel.add(upButton);
            upButtons.add(upButton);
        }

        panel.add(upPanel);

        final JPanel downPanel = new JPanel();
        downPanel.setMaximumSize(new Dimension(Short.MAX_VALUE,
                Short.MAX_VALUE));
        downPanel.setLayout(new BoxLayout(downPanel, BoxLayout.Y_AXIS));
        downPanel.setBorder(border);

        for (int i = 0; i < users.size(); i++) {
            final JRadioButton downButton = new JRadioButton("Down");
            downButton.setMaximumSize(new Dimension(Short.MAX_VALUE,
                    Short.MAX_VALUE));

            downPanel.add(downButton);

            final ButtonGroup buttonGroup = new ButtonGroup();

            buttonGroup.add(downButton);
            buttonGroup.add(upButtons.get(i));
        }

        panel.add(downPanel);

        return panel;
    }

    @SuppressWarnings("unused")
    private static JPanel groupLayoutSetup(final List<String> users) {
//        final GridLayout panelLayout = new GridLayout(0, 3);
        final JPanel panel = new JPanel();

        final GroupLayout panelLayout = new GroupLayout(panel);

        final SequentialGroup hSeqGroup = panelLayout
                .createSequentialGroup();
        final SequentialGroup vSeqGroup = panelLayout
                .createSequentialGroup();

        panel.setLayout(panelLayout);

        for (final String user : users) {
//            final JPanel rowPanel = new JPanel();

            final ParallelGroup hGroup = panelLayout
                    .createParallelGroup(GroupLayout.Alignment.LEADING);
            final ParallelGroup vGroup = panelLayout
                    .createParallelGroup(GroupLayout.Alignment.BASELINE);

            final JCheckBox user1CheckBox = new JCheckBox(user);
            hGroup.addComponent(user1CheckBox);
            vGroup.addComponent(user1CheckBox);

//            final JRadioButton user1UpButton = new JRadioButton("Up");
//            hGroup.addComponent(user1UpButton);
//            vGroup.addComponent(user1UpButton);
//
//            final JRadioButton user1DownButton = new JRadioButton("Down");
//            hGroup.addComponent(user1DownButton);
//            vGroup.addComponent(user1DownButton);

            hSeqGroup.addGroup(hGroup);
            vSeqGroup.addGroup(vGroup);

//            final ButtonGroup buttonGroup = new ButtonGroup();
//            buttonGroup.add(user1UpButton);
//            buttonGroup.add(user1DownButton);

//              panel.add(rowPanel);
        }

        panelLayout.setHorizontalGroup(hSeqGroup);
        panelLayout.setVerticalGroup(vSeqGroup);

        return panel;
    }

}
