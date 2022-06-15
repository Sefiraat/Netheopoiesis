package dev.sefiraat.netheopoiesis.managers;

import com.google.common.base.Preconditions;

/**
 * This class is used to check, register and manage/provide supported plugins
 */
public class SupportedPluginManager {

    private static SupportedPluginManager instance;

    public SupportedPluginManager() {
        Preconditions.checkArgument(instance == null, "Cannot create a new instance of the SupportedPluginManager");
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
