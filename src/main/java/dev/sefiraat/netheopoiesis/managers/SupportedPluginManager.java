package dev.sefiraat.netheopoiesis.managers;

import org.apache.commons.lang.Validate;

/**
 * This class is used to check, register and manage/provide supported plugins
 */
public class SupportedPluginManager {

    private static SupportedPluginManager instance;

    public SupportedPluginManager() {
        Validate.isTrue(instance == null, "Cannot create a new instance of the SupportedPluginManager");
        instance = this;
        setup();
    }

    private void setup() {
        // Todo - Remove this whole class if it's not needed before the Jam ends
    }

    public static SupportedPluginManager getInstance() {
        return instance;
    }
}
