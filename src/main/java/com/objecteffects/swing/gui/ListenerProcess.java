package com.objecteffects.swing.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.objecteffects.reddit.method.HidePostsJsonPath;
import com.objecteffects.reddit.method.UpVotePostsJsonPath;
import com.objecteffects.swing.main.AppConfig;

class ListenerProcess implements ActionListener {
    private final static Logger log = LogManager
            .getLogger(ListenerProcess.class);

    private final static int LOOP_COUNT = 8;
    private final static int POSTS_COUNT = 100;

    @Override
    public void actionPerformed(final ActionEvent e) {
        log.debug("clicked: {}, param: {}", e.getActionCommand(),
                e.paramString());

        final Set<String> removeUsers = ProcessUsers.getRemoveUsers();
        final Set<String> hideUsers = ProcessUsers.getHideUsers();
        final Set<String> upVoteUsers = ProcessUsers.getUpVoteUsers();
        final Set<String> downVoteUsers = ProcessUsers.getDownVoteUsers();

        if (!removeUsers.isEmpty()) {
            processRemoveUsers(removeUsers);
            removeUsers.clear();
        }

        if (!hideUsers.isEmpty()) {
            final Set<String> threadSet = new HashSet<>(hideUsers);

            final Thread thread = new Thread("hide-posts") {
                @Override
                public void run() {
                    processHidePosts(threadSet);
                }
            };

            thread.start();
            hideUsers.clear();
        }

        if (!upVoteUsers.isEmpty()) {
            final Set<String> threadSet = new HashSet<>(upVoteUsers);

            final Thread thread = new Thread("upvote-posts") {
                @Override
                public void run() {
                    processUpVotes(threadSet);
                }
            };

            thread.start();
            upVoteUsers.clear();
        }

        if (!downVoteUsers.isEmpty()) {
            final Set<String> threadSet = new HashSet<>(downVoteUsers);

            final Thread thread = new Thread("downvote-posts") {
                @Override
                public void run() {
                    processDownVotes(threadSet);
                    MessagesPanel.append("downvote posts done\n");
                }
            };

            thread.start();
            downVoteUsers.clear();
        }

        resetBoxesButtons();
    }

    private void processRemoveUsers(final Set<String> removeUsers) {
        for (final String user : removeUsers) {
            log.debug("delete: {}", user);
            AppConfig.removeName(user);
        }
    }

    private void processHidePosts(final Set<String> hideUsers) {
        final HidePostsJsonPath hidePosts = new HidePostsJsonPath();

        for (final String user : hideUsers) {
            log.debug("hide: {}", user);
            try {
                String after = hidePosts.hidePosts(user, POSTS_COUNT,
                        null);

                for (int i = 0; i < LOOP_COUNT; i++) {
                    if (after == null) {
                        break;
                    }

                    after = hidePosts.hidePosts(user, POSTS_COUNT,
                            after);
                }

                final String date = date();

                MessagesPanel.append(
                        String.format("%s: hide posts done for %s\n",
                                date, user));
            }
            catch (IOException | InterruptedException ex) {
                log.warn("hidePosts exception: ", ex);
            }
        }
    }

    private String date() {
        final String pattern = "HH:m dd MMM";
        final SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat(pattern);
        final String date = simpleDateFormat.format(new Date());
        return date;
    }

    private void processUpVotes(final Set<String> upVoteUsers) {
        final UpVotePostsJsonPath upVotePosts =
                new UpVotePostsJsonPath();

        for (final String user : upVoteUsers) {
            log.debug("up vote: {}", user);

            try {
                String after = upVotePosts.upVotePosts(user,
                        POSTS_COUNT, null);

                for (int i = 0; i < LOOP_COUNT; i++) {
                    if (after == null) {
                        break;
                    }

                    after = upVotePosts.upVotePosts(user,
                            POSTS_COUNT, after);
                }

                final String date = date();

                MessagesPanel.append(
                        String.format("%s: upvote posts done for %s\n", date,
                                user));
            }
            catch (IOException | InterruptedException ex) {
                log.warn("upVotePosts exception: ", ex);
            }
        }
    }

    private void processDownVotes(final Set<String> downVoteUsers) {
        for (final String user : downVoteUsers) {
            log.debug("down vote: {}", user);
        }
    }

    private void resetBoxesButtons() {
        for (final AbstractButton button : HidePostsPanel.getCheckBoxes()) {
            // only works for JCheckBox; no effect for JRadioButton
            button.setSelected(false);
        }

        // deselect all JRadioButton in each group
        for (final ButtonGroup buttonGroups : HidePostsPanel
                .getButtonGroups()) {
            buttonGroups.clearSelection();
        }
    }
}
