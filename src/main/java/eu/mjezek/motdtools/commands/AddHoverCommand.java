package eu.mjezek.motdtools.commands;

import eu.mjezek.motdtools.MotdTools;
import eu.mjezek.motdtools.command.Command;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

import java.util.ArrayList;
import java.util.List;

public class AddHoverCommand extends Command {
    public AddHoverCommand(MotdTools plugin) {
        super(plugin, "addhover", new Permission[]{new Permission("motdtools.hover")}, "Adds a hover message.");
    }

    @Override
    public void execute(Player sender, String[] args) {
        if (!plugin.isProtocolLibAvailable()) {
            sender.sendMessage(MotdTools.PREFIX + "You must have ProtocolLib installed to use this functionality!");
            return;
        }

        if (args.length < 1) {
            sender.sendMessage(MotdTools.PREFIX + "You must enter hover message as command argument!");
            return;
        }

        String set = String.join(" ", args);

        List<String> hoverMessages = plugin.getConfigManager().getConfigOrDefault("hover", new ArrayList<>());
        hoverMessages.add(set);
        plugin.getConfigManager().setConfig("hover", hoverMessages);

        sender.sendMessage(MotdTools.PREFIX + "Hover message added: " + ChatColor.RESET + plugin.getConfigManager().specialsTranslate(set));
    }
}
