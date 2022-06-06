package dev.sefiraat.netheopoiesis.managers;

import dev.sefiraat.netheopoiesis.Netheopoiesis;
import org.apache.commons.lang.Validate;

public class RunnableManager {

    private static RunnableManager instance;

    public RunnableManager() {
        Validate.isTrue(instance == null, "Cannot create a new instance of the ListenerManager");
        instance = this;
        final Netheopoiesis plugin = Netheopoiesis.getInstance();
    }

    public static RunnableManager getInstance() {
        return instance;
    }
}
