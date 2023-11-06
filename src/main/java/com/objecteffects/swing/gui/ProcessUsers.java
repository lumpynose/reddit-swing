package com.objecteffects.swing.gui;

import java.util.HashSet;
import java.util.Set;

final class ProcessUsers {
    private final static Set<String> removeUsers = new HashSet<>();
    private final static Set<String> hideUsers = new HashSet<>();
    private final static Set<String> upVoteUsers = new HashSet<>();
    private final static Set<String> downVoteUsers = new HashSet<>();

    private ProcessUsers() {
        // empty
    }

    static Set<String> getRemoveUsers() {
        return removeUsers;
    }

    static Set<String> getHideUsers() {
        return hideUsers;
    }

    static Set<String> getUpVoteUsers() {
        return upVoteUsers;
    }

    static Set<String> getDownVoteUsers() {
        return downVoteUsers;
    }
}
