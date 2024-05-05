package eu.mjezek.motdtools.placeholder;

import eu.mjezek.motdtools.MotdTools;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;

public class MotdToolsExpansion extends PlaceholderExpansion {
    private final MotdTools _plugin;

    public MotdToolsExpansion(MotdTools plugin) {
        _plugin = plugin;
    }

    @Override
    public boolean canRegister() {
        return true;
    }

    @Override
    public String getAuthor() {
        return "mjezek";
    }

    @Override
    public String getIdentifier() {
        return "motdtools";
    }

    @Override
    public String getVersion() {
        return _plugin.getDescription().getVersion();
    }

    @Override
    public String onRequest(OfflinePlayer player, String identifier) {
        switch (identifier) {
            case "motd1":
                return _plugin.getConfigManager().specialsTranslate(_plugin.getConfigManager().getConfig("motd1"));
            case "motd2":
                return _plugin.getConfigManager().specialsTranslate(_plugin.getConfigManager().randomMotd2());
            case "center1":
                return _plugin.getConfigManager().getConfig("center1");
            case "center2":
                return _plugin.getConfigManager().getConfig("center2");
            case "hover":
                if (!_plugin.isProtocolLibAvailable())
                    return "You must have ProtocolLib installed!";

                return _plugin.getConfigManager().specialsTranslate(_plugin.getConfigManager().randomHover());
            case "version":
                if (!_plugin.isProtocolLibAvailable())
                    return "You must have ProtocolLib installed!";

                String version = _plugin.getConfigManager().getConfig("version");
                if (version == null || version.isEmpty())
                    return "Version is not set!";

                return _plugin.getConfigManager().specialsTranslate(version);
        }

        return null;
    }
}