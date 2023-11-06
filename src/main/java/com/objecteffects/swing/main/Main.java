package com.objecteffects.swing.main;

import java.awt.Color;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.objecteffects.swing.gui.TabsFrame;

public final class Main {
    private final static Logger log = LogManager.getLogger(Main.class);

    public static void main(final String[] args)
            throws InterruptedException, UnsupportedLookAndFeelException,
            IllegalAccessException, InvocationTargetException,
            ClassNotFoundException, InstantiationException {
        log.debug("nameList: {}", AppConfig.getNameList());

        UIManager.put("nimbusBase", Color.GRAY);
        UIManager.put("nimbusFocus", Color.DARK_GRAY);

        for (final LookAndFeelInfo info : UIManager
                .getInstalledLookAndFeels()) {
            log.debug("laf: {}", info.getName());

            if (Objects.equals(info.getName(), "Nimbus")) {
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                }
                catch (final Exception e) {
                    log.warn("UIManager", e);

                    UIManager.setLookAndFeel(
                            UIManager.getCrossPlatformLookAndFeelClassName());
                }

                break;
            }
        }

        javax.swing.SwingUtilities.invokeAndWait(new Runnable() {
            @Override
            public void run() {
                TabsFrame.setup();
            }
        });
    }
}
