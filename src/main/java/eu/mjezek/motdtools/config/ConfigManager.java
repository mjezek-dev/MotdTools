package eu.mjezek.motdtools.config;

import eu.mjezek.motdtools.Manager;
import eu.mjezek.motdtools.MotdTools;
import eu.mjezek.motdtools.centerutil.CenterUtilMotd;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.ChatColor;

import java.util.List;
import java.util.Random;

public class ConfigManager extends Manager {
    private final Random random = new Random();

    public ConfigManager(MotdTools plugin) {
        super(plugin);

        this.plugin.saveDefaultConfig();
    }

    @SuppressWarnings("unchecked")
    public <T> T getConfig(String key) {
        return (T) plugin.getConfig().get(key);
    }

    public <T> T getConfigOrDefault(String key, T defaultValue) {
        T value = getConfig(key);
        if (value == null)
            return defaultValue;

        return value;
    }

    public void setConfig(String key, Object value) {
        plugin.getConfig().set(key, value);
        plugin.saveConfig();
    }

    public String randomMotd2() {
        List<String> list = getConfig("motd2");
        if (list == null || list.isEmpty())
            return "Line 2 MOTD is not set!";

        return list.get(random.nextInt(list.size()));
    }

    public String randomHover() {
        List<String> list = getConfig("hover");
        if (list == null || list.isEmpty())
            return "Hover message is not set!";

        return list.get(random.nextInt(list.size()));
    }

    public String specialsTranslate(String s) {
        return specialsTranslate(s, 0);
    }

    public String specialsTranslate(String text, int line) {
        text = ChatColor.translateAlternateColorCodes('&', text);

        if (plugin.isPlaceholderApiAvailable()) {
            text = PlaceholderAPI.setPlaceholders(null, text);
        }

        boolean center = false;
        if (line == 1) {
            center = getConfig("center1");
        } else if (line == 2) {
            center = getConfig("center2");
        }

        if (center) {
            text = CenterUtilMotd.getCenteredMotd(text);
        }

        return text;
    }
}
