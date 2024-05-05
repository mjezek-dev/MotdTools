package eu.mjezek.motdtools.commands;

import eu.mjezek.motdtools.MotdTools;
import eu.mjezek.motdtools.command.Command;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

public class InfoCommand extends Command {

    public InfoCommand(MotdTools plugin) {
        super(plugin, "info", new Permission[]{}, "Shows plugin info.");
    }

    @Override
    public void execute(Player sender, String[] args) {
        sender.sendMessage(MotdTools.PREFIX + "Plugin info:");

        sender.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "Author: " + ChatColor.GREEN + "mjezek");
        sender.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "SpigotMC: " + ChatColor.GREEN + "https://www.spigotmc.org/resources/motd-tools.67559/");
        sender.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "Version: " + ChatColor.GREEN + plugin.getDescription().getVersion());
    }

}
