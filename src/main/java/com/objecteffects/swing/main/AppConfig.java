package com.objecteffects.swing.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AppConfig {
    private final static Logger log = LogManager
            .getLogger(AppConfig.class);

    private final static String configFile =
            "c:/home/rusty/reddit-swing.properties";

    private final static Parameters params = new Parameters();

    private final static FileBasedConfigurationBuilder<FileBasedConfiguration> builder =
            new FileBasedConfigurationBuilder<FileBasedConfiguration>(
                    PropertiesConfiguration.class)
                    .configure(params.properties().setFileName(configFile));

    private static Configuration config;
    private static List<String> nameList = null;
    private final static String nameListProperty = "nameList";

    public static void loadConfiguration() {
        try {
            config = builder.getConfiguration();

            // need to wrap in an ArrayList because Arrays.asList returns
            // an unmodifiable list, otherwise removeAll in getNameList
            // fails.
            nameList = new ArrayList<>(
                    Arrays.asList(config.getStringArray(nameListProperty)));
        }
        catch (final ConfigurationException cex) {
            log.warn("configuration load error", cex);

            nameList = Collections.emptyList();
        }
    }

    public static List<String> getNameList() {
        if (nameList == null) {
            loadConfiguration();
        }

        nameList.removeAll(Collections.singleton(null));
        nameList.removeAll(Collections.singleton(""));

        return nameList;
    }

    public static void addName(final String name) {
        nameList.add(name);
        config.setProperty(nameListProperty, nameList);

        saveConfig();
    }

    public static void removeName(final String name) {
        nameList.remove(name);
        config.setProperty(nameListProperty, nameList);

        saveConfig();
    }

    public static void saveConfig() {
        try {
            builder.save();
        }
        catch (final ConfigurationException cex) {
            log.warn("configuration save error", cex);
        }
    }
}
