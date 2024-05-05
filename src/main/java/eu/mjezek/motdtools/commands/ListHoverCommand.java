package eu.mjezek.motdtools.commands;

import eu.mjezek.motdtools.MotdTools;
import eu.mjezek.motdtools.command.Command;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

import java.util.ArrayList;
import java.util.List;

public class ListHoverCommand extends Command {
    public ListHoverCommand(MotdTools plugin) {
        super(plugin, "listhover", new Permission[]{new Permission("motdtools.hover")}, "Lists all hover messages.");
    }

    @Override
    public void execute(Player sender, String[] args) {
        if (!plugin.isProtocolLibAvailable()) {
            sender.sendMessage(MotdTools.PREFIX + "You must have ProtocolLib installed to use this functionality!");
            return;
        }

        sender.sendMessage(MotdTools.PREFIX + "List of hover messages:");

        List<String> list = plugin.getConfigManager().getConfigOrDefault("hover", new ArrayList<>());

        int i = 0;
        for (String value : list) {
            sender.sendMessage(ChatColor.GREEN.toString() + ChatColor.BOLD + i++ + ChatColor.DARK_GRAY + " || " + ChatColor.RESET + plugin.getConfigManager().specialsTranslate(value));
        }
    }
}
