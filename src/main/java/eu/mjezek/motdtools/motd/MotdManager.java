package eu.mjezek.motdtools.motd;

import eu.mjezek.motdtools.Manager;
import eu.mjezek.motdtools.MotdTools;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.server.ServerListPingEvent;

public class MotdManager extends Manager {
    public MotdManager(MotdTools plugin) {
        super(plugin);
    }

    @EventHandler
    public void onPing(ServerListPingEvent event) {
        event.setMotd(
                plugin.getConfigManager().specialsTranslate(plugin.getConfigManager().getConfig("motd1"), 1)
                        + "\n" + ChatColor.RESET
                        + plugin.getConfigManager().specialsTranslate(plugin.getConfigManager().randomMotd2(), 2)
        );
    }
}
