package eu.mjezek.motdtools;

import org.bukkit.event.Listener;

public abstract class Manager implements Listener {
    protected MotdTools plugin;

    public Manager(MotdTools plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);
    }
}
