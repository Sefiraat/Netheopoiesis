package dev.sefiraat.netheopoiesis.api.interfaces;

import dev.sefiraat.netheopoiesis.api.plant.netheos.FlavourProfile;

import javax.annotation.Nullable;

/**
 * A SeedPaste item a {@link WorldCrushable} item whose crushed variant can be used for making
 * Malvin Cubes
 */
public interface SeedPaste extends WorldCrushable {

    @Nullable
    FlavourProfile getFlavourProfile();
}
