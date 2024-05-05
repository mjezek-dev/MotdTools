package eu.mjezek.motdtools;

import eu.mjezek.motdtools.command.CommandManager;
import eu.mjezek.motdtools.config.ConfigManager;
import eu.mjezek.motdtools.motd.MotdManager;
import eu.mjezek.motdtools.placeholder.MotdToolsExpansion;
import eu.mjezek.motdtools.protocol.ProtocolManager;
import eu.mjezek.motdtools.update.UpdateManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class MotdTools extends JavaPlugin {
    private ConfigManager config;
    private CommandManager command;
    private ProtocolManager protocol;

    private boolean placeholderApiAvailable = false;

    public static final String PREFIX = ChatColor.GOLD + "Motd Tools> " + ChatColor.GREEN;

    @Override
    public void onEnable() {
        config = new ConfigManager(this);
        command = new CommandManager(this);
        new MotdManager(this);
        new UpdateManager(this);

        Plugin protocolLibPlugin = getServer().getPluginManager().getPlugin("ProtocolLib");
        if (protocolLibPlugin != null && protocolLibPlugin.isEnabled()) {
            protocol = new ProtocolManager(this);
            protocol.registerListener();
        }

        Plugin placeholderApiPlugin = getServer().getPluginManager().getPlugin("PlaceholderAPI");
        if (placeholderApiPlugin != null && placeholderApiPlugin.isEnabled()) {
            new MotdToolsExpansion(this).register();
            placeholderApiAvailable = true;
        }
    }

    @Override
    public void onDisable() {
        if (isProtocolLibAvailable()) {
            protocol.unregisterListener();
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("motdtools")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.RED + "This command can't be executed by console!");
                return true;
            }

            Player player = (Player) sender;
            if (args != null && args.length > 0) {
                getCommandManager().handleCommand(player, args);
            } else {
                getCommandManager().printHelp(player);
            }

            return true;
        }

        return false;
    }

    public ConfigManager getConfigManager() {
        return config;
    }

    public CommandManager getCommandManager() {
        return command;
    }

    public ProtocolManager getProtocolManager() {
        return protocol;
    }

    public boolean isProtocolLibAvailable() {
        return getProtocolManager() != null;
    }

    public boolean isPlaceholderApiAvailable() {
        return placeholderApiAvailable;
    }
}
