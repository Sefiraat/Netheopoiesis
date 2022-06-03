package dev.sefiraat.netheopoiesis.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import dev.sefiraat.netheopoiesis.utils.Theme;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("netheopoiesis|nps")
public class CommandMain extends BaseCommand {

    @Default
    public void onDefault(CommandSender sender) {
        if (sender instanceof Player) {
            sender.sendMessage(Theme.ERROR + "Please provide a valid subcommand.");
        }
    }
}

