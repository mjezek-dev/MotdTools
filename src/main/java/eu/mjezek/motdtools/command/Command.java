package eu.mjezek.motdtools.command;

import eu.mjezek.motdtools.MotdTools;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

public abstract class Command {
    private final String name;
    private final Permission[] permissions;
    private final String help;

    protected final MotdTools plugin;

    public Command(MotdTools plugin, String name, Permission[] permissions, String help) {
        this.plugin = plugin;
        this.name = name;
        this.permissions = permissions;
        this.help = help;
    }

    public abstract void execute(Player sender, String[] args);

    public String getName() {
        return name;
    }

    public Permission[] getPermissions() {
        return permissions;
    }

    public String getHelp() {
        return help;
    }
}
