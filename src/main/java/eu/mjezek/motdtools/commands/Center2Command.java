package eu.mjezek.motdtools.commands;

import eu.mjezek.motdtools.MotdTools;
import eu.mjezek.motdtools.command.Command;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

public class Center2Command extends Command {
    public Center2Command(MotdTools plugin) {
        super(plugin, "center2", new Permission[]{new Permission("motdtools.center2")}, "Gets or sets MOTD centering on line 2.");
    }

    @Override
    public void execute(Player sender, String[] args) {
        if (args.length < 1) {
            sender.sendMessage(MotdTools.PREFIX + "Line 2 centering: " + ChatColor.RESET + plugin.getConfigManager().getConfig("center2"));
            return;
        }

        String setString = args[0];
        if (!setString.equals("false") && !setString.equals("true")) {
            sender.sendMessage(MotdTools.PREFIX + "You must enter true or false as argument!");
            return;
        }

        boolean set = Boolean.parseBoolean(setString);
        plugin.getConfigManager().setConfig("center2", set);
        sender.sendMessage(MotdTools.PREFIX + "Line 2 centering changed to: " + ChatColor.RESET + set);
    }
}
