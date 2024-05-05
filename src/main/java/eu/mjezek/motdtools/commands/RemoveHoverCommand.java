package eu.mjezek.motdtools.commands;

import eu.mjezek.motdtools.MotdTools;
import eu.mjezek.motdtools.command.Command;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

import java.util.ArrayList;
import java.util.List;

public class RemoveHoverCommand extends Command {
    public RemoveHoverCommand(MotdTools plugin) {
        super(plugin, "removehover", new Permission[]{new Permission("motdtools.hover")}, "Removes a hover message.");
    }

    @Override
    public void execute(Player sender, String[] args) {
        if (!plugin.isProtocolLibAvailable()) {
            sender.sendMessage(MotdTools.PREFIX + "You must have ProtocolLib installed to use this functionality!");
            return;
        }

        if (args.length < 1) {
            sender.sendMessage(MotdTools.PREFIX + "You must enter hover message ID as argument!");
            return;
        }

        int id;
        try {
            id = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            sender.sendMessage(MotdTools.PREFIX + "Hover message ID is invalid!");
            return;
        }

        List<String> list = plugin.getConfigManager().getConfigOrDefault("hover", new ArrayList<>());
        if (id < 0 || id >= list.size()) {
            sender.sendMessage(MotdTools.PREFIX + "Hover message ID is invalid!");
            return;
        }

        String hoverMessage = list.remove(id);

        plugin.getConfigManager().setConfig("hover", list);
        sender.sendMessage(MotdTools.PREFIX + "Hover message removed: " + ChatColor.RESET + plugin.getConfigManager().specialsTranslate(hoverMessage));
    }
}
