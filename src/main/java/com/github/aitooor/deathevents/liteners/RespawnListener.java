package com.github.aitooor.deathevents.liteners;

import com.github.aitooor.deathevents.DeathEvents;
import com.github.aitooor.deathevents.config.ConfigFile;
import com.github.aitooor.deathevents.config.MessageFile;
import com.github.aitooor.deathevents.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class RespawnListener implements Listener {
    private DeathEvents plugin;
    private ConfigFile config;
    private MessageFile messageFile;

    public RespawnListener(DeathEvents plugin, ConfigFile config, MessageFile messageFile) {
        this.plugin = plugin;
        this.config = config;
        this.messageFile = messageFile;
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent e) {
        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
        Player player = e.getPlayer();

        if(!(player instanceof Player)) return;

        if(!config.getBoolean("respawn_events.enable")) {
            if(messageFile.getBoolean("respawn_events.enable")) {
                if (player.hasPermission("deathevents.admin")) {
                    ChatUtils.send(player, messageFile.getString("respawn_events.no_enabled"));
                }
            }
            return;
        }

        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            for(String commandList : config.getStringList("respawn_events.commands")) {
                commandList = commandList.replace("%player%", e.getPlayer().getName());
                Bukkit.dispatchCommand(console, commandList);
            }
        }, 4L);
    }
}