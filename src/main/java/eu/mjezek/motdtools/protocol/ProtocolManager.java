package eu.mjezek.motdtools.protocol;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.events.PacketListener;
import com.comphenix.protocol.wrappers.WrappedGameProfile;
import com.comphenix.protocol.wrappers.WrappedServerPing;
import eu.mjezek.motdtools.Manager;
import eu.mjezek.motdtools.MotdTools;
import eu.mjezek.motdtools.config.ConfigManager;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProtocolManager extends Manager {
    private final com.comphenix.protocol.ProtocolManager _protocolManager;
    private final PacketListener _packetListener;

    public ProtocolManager(MotdTools plugin) {
        super(plugin);

        _packetListener = new PacketAdapter(plugin, ListenerPriority.NORMAL, PacketType.Status.Server.SERVER_INFO) {
            @Override
            public void onPacketSending(PacketEvent event) {
                ConfigManager configManager = ProtocolManager.this.plugin.getConfigManager();
                WrappedServerPing ping = event.getPacket().getServerPings().read(0);

                String version = configManager.getConfig("version");
                if (version != null && !version.isEmpty()) {
                    ping.setVersionProtocol(-1);
                    ping.setVersionName(ChatColor.RESET + configManager.specialsTranslate(version));
                }

                List<String> hover = configManager.getConfig("hover");
                if (hover != null && !hover.isEmpty()) {
                    List<WrappedGameProfile> hovers = new ArrayList<>(hover.size());
                    for (String line : hover) {
                        hovers.add(new WrappedGameProfile(UUID.randomUUID(), configManager.specialsTranslate(line)));
                    }

                    ping.setPlayersVisible(true);
                    ping.setPlayers(hovers);
                }

                event.getPacket().getServerPings().write(0, ping);
            }
        };

        _protocolManager = ProtocolLibrary.getProtocolManager();
    }

    public void registerListener() {
        _protocolManager.addPacketListener(_packetListener);
    }

    public void unregisterListener() {
        _protocolManager.removePacketListener(_packetListener);
    }
}
