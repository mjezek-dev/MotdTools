package eu.mjezek.motdtools.commands;

import eu.mjezek.motdtools.MotdTools;
import eu.mjezek.motdtools.command.Command;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

public class VersionCommand extends Command {
    public VersionCommand(MotdTools plugin) {
        super(plugin, "version", new Permission[]{new Permission("motdtools.version")}, "Gets or sets version message. (\"none\" for disable)");
    }

    @Override
    public void execute(Player sender, String[] args) {
        if (!plugin.isProtocolLibAvailable()) {
            sender.sendMessage(MotdTools.PREFIX + "You must have ProtocolLib installed to use this functionality!");
            return;
        }

        if (args.length < 1) {
            sender.sendMessage(MotdTools.PREFIX + "Version message: " + ChatColor.RESET + plugin.getConfigManager().specialsTranslate(plugin.getConfigManager().getConfig("version")));
            return;
        }

        String set = String.join(" ", args);

        if (set.equalsIgnoreCase("none")) {
            plugin.getConfigManager().setConfig("version", "");
        } else {
            plugin.getConfigManager().setConfig("version", set);
        }

        sender.sendMessage(MotdTools.PREFIX + "Version message changed to: " + ChatColor.RESET + plugin.getConfigManager().specialsTranslate(set));
    }
}