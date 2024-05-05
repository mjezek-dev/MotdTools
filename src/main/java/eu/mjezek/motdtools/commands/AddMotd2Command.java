package eu.mjezek.motdtools.commands;

import eu.mjezek.motdtools.MotdTools;
import eu.mjezek.motdtools.command.Command;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

import java.util.List;

public class AddMotd2Command extends Command {
    public AddMotd2Command(MotdTools plugin) {
        super(plugin, "addmotd2", new Permission[]{new Permission("motdtools.motd2")}, "Adds MOTD to line 2.");
    }

    @Override
    public void execute(Player sender, String[] args) {
        if (args.length < 1) {
            sender.sendMessage(MotdTools.PREFIX + "You must enter MOTD as command argument!");
            return;
        }

        String set = String.join(" ", args);

        List<String> list = plugin.getConfigManager().getConfig("motd2");
        list.add(set);
        plugin.getConfigManager().setConfig("motd2", list);

        sender.sendMessage(MotdTools.PREFIX + "MOTD on line 2 added: " + ChatColor.RESET + plugin.getConfigManager().specialsTranslate(set));
    }
}
