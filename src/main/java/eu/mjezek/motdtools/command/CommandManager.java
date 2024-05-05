package eu.mjezek.motdtools.command;

import eu.mjezek.motdtools.Manager;
import eu.mjezek.motdtools.MotdTools;
import eu.mjezek.motdtools.commands.*;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class CommandManager extends Manager {
    private final Map<String, Command> commands = new LinkedHashMap<>();

    public CommandManager(MotdTools plugin) {
        super(plugin);

        addCommand(new Motd1Command(this.plugin));
        addCommand(new AddMotd2Command(this.plugin));
        addCommand(new ListMotd2Command(this.plugin));
        addCommand(new RemoveMotd2Command(this.plugin));
        addCommand(new Center1Command(this.plugin));
        addCommand(new Center2Command(this.plugin));
        addCommand(new VersionCommand(this.plugin));
        addCommand(new AddHoverCommand(this.plugin));
        addCommand(new ListHoverCommand(this.plugin));
        addCommand(new RemoveHoverCommand(this.plugin));
        addCommand(new InfoCommand(this.plugin));
    }

    public void addCommand(Command command) {
        commands.put(command.getName(), command);
    }

    public void handleCommand(Player sender, String[] originalArgs) {
        String commandName = originalArgs[0].toLowerCase();
        Command command = commands.get(commandName);
        if (command == null) {
            sender.sendMessage(ChatColor.RED + "The command doesn't exist. Use /mt for help.");
            return;
        }

        String[] args = Arrays.copyOfRange(originalArgs, 1, originalArgs.length);
        tryExecute(command, sender, args);
    }

    private void tryExecute(Command command, Player sender, String[] args) {
        if (Arrays.stream(command.getPermissions()).allMatch(sender::hasPermission)) {
            command.execute(sender, args);
        } else {
            sender.sendMessage(ChatColor.RED + "You can't use this command!");
        }
    }

    public void printHelp(Player sender) {
        sender.sendMessage(ChatColor.GREEN.toString() + ChatColor.STRIKETHROUGH + "===============" +
                ChatColor.GOLD + ChatColor.BOLD + " Motd Tools " +
                ChatColor.GREEN + ChatColor.STRIKETHROUGH + "===============");

        for (Command command : commands.values()) {
            sender.sendMessage(ChatColor.GREEN + "/mt " + command.getName() + ChatColor.DARK_GRAY + " || " + ChatColor.GOLD + command.getHelp());
        }

        sender.sendMessage("");
    }
}
