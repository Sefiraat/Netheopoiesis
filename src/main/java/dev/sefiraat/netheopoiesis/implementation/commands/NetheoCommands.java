package dev.sefiraat.netheopoiesis.implementation.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.Subcommand;
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
        if (sender instanceof Player player) {
            String[] messages = new String[MobCapType.values().length];
            for (int i = 0; i < MobCapType.values().length; i++) {
                final MobCapType type = MobCapType.values()[i];
                final MobCap mobCap = MobManager.getInstance().getMobCap(type);
                messages[i] = Theme.CLICK_INFO.apply(
                    TextUtils.toTitleCase(type.name()) + ": " + mobCap.count() + "/" + mobCap.getMaxAmount()
                );
            }
            player.sendMessage(messages);
        }
    }

    @Subcommand("PurgeMobCap")
    @Description("Kills all mobs from a specific Mob Cap")
    @CommandPermission("netheopoiesis.admin.mobcaps")
    @CommandCompletion("@MOB_CAPS")
    public void purgeMobCap(CommandSender sender, String mobCapType) {
        if (sender instanceof Player player) {
            final MobCap cap = MobManager.getInstance().getMobCap(MobCapType.valueOf(mobCapType));
            cap.killAllMobs();
            player.sendMessage(Theme.SUCCESS.apply("Mob Cap Purged"));
        }
    }

    @Subcommand("PurgeAllMobCap")
    @Description("Kills all mobs from all Mob Caps")
    @CommandPermission("netheopoiesis.admin.mobcaps")
    public void purgeMobCap(CommandSender sender) {
        if (sender instanceof Player player) {
            for (MobCapType type : MobCapType.values()) {
                MobManager.getInstance().getMobCap(type).killAllMobs();
            }
            player.sendMessage(Theme.SUCCESS.apply("All Mob Caps Purged"));
        }
    }

}

