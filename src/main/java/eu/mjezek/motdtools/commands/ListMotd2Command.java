package eu.mjezek.motdtools.commands;

import eu.mjezek.motdtools.MotdTools;
import eu.mjezek.motdtools.command.Command;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

import java.util.List;

public class ListMotd2Command extends Command {
    public ListMotd2Command(MotdTools plugin) {
        super(plugin, "listmotd2", new Permission[]{new Permission("motdtools.motd2")}, "Lists all MOTDs on line 2.");
    }

    @Override
    public void execute(Player sender, String[] args) {
        sender.sendMessage(MotdTools.PREFIX + "List of MOTDs on line 2:");

        List<String> list = plugin.getConfigManager().getConfig("motd2");

        int i = 0;
        for (String value : list) {
            sender.sendMessage(ChatColor.GREEN.toString() + ChatColor.BOLD + i++ + ChatColor.DARK_GRAY + " || " + ChatColor.RESET + plugin.getConfigManager().specialsTranslate(value));
        }
    }
}
