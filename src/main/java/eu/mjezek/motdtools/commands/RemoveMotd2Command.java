package eu.mjezek.motdtools.commands;

import eu.mjezek.motdtools.MotdTools;
import eu.mjezek.motdtools.command.Command;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

import java.util.List;

public class RemoveMotd2Command extends Command {
    public RemoveMotd2Command(MotdTools plugin) {
        super(plugin, "removemotd2", new Permission[]{new Permission("motdtools.motd2")}, "Removes MOTD from line 2.");
    }

    @Override
    public void execute(Player sender, String[] args) {
        if (args.length < 1) {
            sender.sendMessage(MotdTools.PREFIX + "You must enter MOTD ID as argument!");
            return;
        }

        int id;
        try {
            id = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            sender.sendMessage(MotdTools.PREFIX + "MOTD ID is invalid!");
            return;
        }

        List<String> list = plugin.getConfigManager().getConfig("motd2");
        if (id < 0 || id >= list.size()) {
            sender.sendMessage(MotdTools.PREFIX + "MOTD ID is invalid!");
            return;
        }

        String motd = list.remove(id);

        plugin.getConfigManager().setConfig("motd2", list);
        sender.sendMessage(MotdTools.PREFIX + "MOTD on line 2 removed: " + ChatColor.RESET + plugin.getConfigManager().specialsTranslate(motd));
    }
}
