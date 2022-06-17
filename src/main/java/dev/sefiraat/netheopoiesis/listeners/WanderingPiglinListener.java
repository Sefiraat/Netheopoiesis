package dev.sefiraat.netheopoiesis.listeners;

import dev.sefiraat.netheopoiesis.api.plant.netheos.NetheoBalls;
import dev.sefiraat.netheopoiesis.api.plant.netheos.Trades;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

import javax.annotation.Nonnull;
import java.util.Map;

/**
 * The purpose of this Listener is to stop mobs targeting other entities (inc. Players)
 * when in purified ground. It also adds doubling to Bartering.
 */
public class WanderingPiglinListener implements Listener {

    private Map<NetheoBalls, Trades.TradePool> possibleTrades;

    public void onPlayerDropItem(@Nonnull PlayerDropItemEvent event) {

    }


}