package eu.mjezek.motdtools.commands;

import eu.mjezek.motdtools.MotdTools;
import eu.mjezek.motdtools.command.Command;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

public class Motd1Command extends Command {
    public Motd1Command(MotdTools plugin) {
        super(plugin, "motd1", new Permission[]{new Permission("motdtools.motd1")}, "Gets or sets MOTD on line 1.");
    }

    @Override
    public void execute(Player sender, String[] args) {
        if (args.length < 1) {
            sender.sendMessage(MotdTools.PREFIX + "MOTD on line 1: " + ChatColor.RESET + plugin.getConfigManager().specialsTranslate(plugin.getConfigManager().getConfig("motd1")));
            return;
        }

        String set = String.join(" ", args);

        plugin.getConfigManager().setConfig("motd1", set);
        sender.sendMessage(MotdTools.PREFIX + "MOTD on line 1 changed to: " + ChatColor.RESET + plugin.getConfigManager().specialsTranslate(set));
    }
}
