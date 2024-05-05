package eu.mjezek.motdtools.update;

import com.google.gson.Gson;
import eu.mjezek.motdtools.Manager;
import eu.mjezek.motdtools.MotdTools;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;

public class UpdateManager extends Manager {
    private final Gson gson = new Gson();
    private String latestVersion = null;
    private boolean consoleInformed = false;

    private final int PLUGIN_ID = 67559;

    public UpdateManager(MotdTools plugin) {
        super(plugin);

        new BukkitRunnable() {
            @Override
            public void run() {
                checkUpdates();

                if (!consoleInformed && isUpdateAvailable()) {
                    consoleInformed = true;

                    Bukkit.getLogger().info("Motd Tools Updater> Plugin is outdated!");
                    Bukkit.getLogger().info("Motd Tools Updater> Your version: " + UpdateManager.this.plugin.getDescription().getVersion());
                    Bukkit.getLogger().info("Motd Tools Updater> Latest version: " + latestVersion);
                }
            }
        }.runTaskTimerAsynchronously(plugin, 0, 60 * 60 * 20);
    }

    private void checkUpdates() {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL("https://api.spiget.org/v2/resources/" + PLUGIN_ID + "/versions/latest").openConnection();
            connection.addRequestProperty("User-Agent", "MotdTools Update Checker");
            connection.connect();

            VersionObject versionObject = gson.fromJson(new InputStreamReader(connection.getInputStream()), VersionObject.class);
            latestVersion = versionObject.name;

            connection.disconnect();
        } catch (Exception e) {
            Bukkit.getLogger().log(Level.WARNING, "Failed to get latest version from spiget.org", e);
        }
    }

    private boolean isUpdateAvailable() {
        return latestVersion != null && !latestVersion.equals(plugin.getDescription().getVersion());
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();

        if (p.isOp() && isUpdateAvailable()) {
            p.sendMessage(MotdTools.PREFIX + "Plugin is outdated!");
            p.sendMessage(MotdTools.PREFIX + "Your version: " + plugin.getDescription().getVersion());
            p.sendMessage(MotdTools.PREFIX + "Latest version: " + latestVersion);
        }
    }
}
