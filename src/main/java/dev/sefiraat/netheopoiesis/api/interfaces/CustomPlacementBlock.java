package dev.sefiraat.netheopoiesis.api.interfaces;

import org.bukkit.event.block.BlockPlaceEvent;

import javax.annotation.Nonnull;

public interface CustomPlacementBlock {

    void whenPlaced(@Nonnull BlockPlaceEvent event);

}
