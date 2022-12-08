package com.github.aitooor.deathevents;

import com.github.aitooor.deathevents.config.ConfigFile;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class DeathListener implements Listener {
    private DeathEvents plugin;
    private ConfigFile config;

    public DeathListener(DeathEvents plugin, ConfigFile config) {
        this.plugin = plugin;
        this.config = config;
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent e) {
        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();

        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            for(String commandList : config.getStringList("respawn_events.commands")) {
                commandList = commandList.replace("%player%", e.getPlayer().getName());
                Bukkit.dispatchCommand(console, commandList);
            }
        }, 4L);
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
        Player player = e.getEntity().getPlayer();

        if(!(player instanceof Player)) return;

        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            for(String commandList : config.getStringList("death_events.commands")) {
                commandList = commandList.replace("%player%", player.getName());
                Bukkit.dispatchCommand(console, commandList);
            }
        }, 4L);
    }
}