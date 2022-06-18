package dev.sefiraat.netheopoiesis.implementation.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.Subcommand;
import dev.sefiraat.netheopoiesis.Netheopoiesis;
import dev.sefiraat.netheopoiesis.api.mobs.MobCap;
import dev.sefiraat.netheopoiesis.api.mobs.MobCapType;
import dev.sefiraat.netheopoiesis.managers.MobManager;
import dev.sefiraat.netheopoiesis.utils.TextUtils;
import dev.sefiraat.netheopoiesis.utils.Theme;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("netheopoiesis|netheo")
public class NetheoCommands extends BaseCommand {

    @Default
    public void onDefault(CommandSender sender) {
        if (sender instanceof Player) {
            sender.sendMessage(Theme.ERROR + "Please provide a valid subcommand.");
        }
    }

    @Subcommand("MobCaps")
    @Description("Displays information about the various MobCaps")
    @CommandPermission("netheopoiesis.admin.mobcaps")
    public void viewMobCaps(CommandSender sender) {
        final String[] messages = new String[MobCapType.values().length];

        for (int i = 0; i < MobCapType.values().length; i++) {
            final MobCapType type = MobCapType.values()[i];
            final MobCap mobCap = MobManager.getInstance().getMobCap(type);
            messages[i] = TextUtils.toTitleCase(type.name()) + ": " + mobCap.count() + "/" + mobCap.getMaxAmount();
        }

        if (sender instanceof Player player) {
            for (String message : messages) {
                player.sendMessage(Theme.CLICK_INFO.apply(message));
            }
        } else {
            for (String message : messages) {
                Netheopoiesis.logInfo(message);
            }
        }
    }

    @Subcommand("PurgeMobCap")
    @Description("Kills all mobs from a specific Mob Cap")
    @CommandPermission("netheopoiesis.admin.mobcaps")
    @CommandCompletion("@MOB_CAPS")
    public void purgeMobCap(CommandSender sender, String mobCapType) {
        final MobCap cap = MobManager.getInstance().getMobCap(MobCapType.valueOf(mobCapType));
        final String message = "Mob Cap Purged";

        cap.killAllMobs();
        if (sender instanceof Player player) {
            player.sendMessage(Theme.SUCCESS.apply(message));
        } else {
            Netheopoiesis.logInfo(message);
        }
    }

    @Subcommand("PurgeAllMobCap")
    @Description("Kills all mobs from all Mob Caps")
    @CommandPermission("netheopoiesis.admin.mobcaps")
    public void purgeMobCap(CommandSender sender) {
        final String message = "All Mob Caps Purged";

        for (MobCapType type : MobCapType.values()) {
            MobManager.getInstance().getMobCap(type).killAllMobs();
        }

        if (sender instanceof Player player) {
            player.sendMessage(Theme.SUCCESS.apply(message));
        } else {
            Netheopoiesis.logInfo(message);
        }
    }

}

