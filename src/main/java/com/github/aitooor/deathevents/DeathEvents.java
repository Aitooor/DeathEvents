package com.github.aitooor.deathevents;

import com.github.aitooor.deathevents.config.ConfigFile;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class DeathEvents extends JavaPlugin {

    private static DeathEvents instance;
    private ConfigFile configFile;

    @Override
    public void onEnable() {
        instance = this;
        this.configFile = new ConfigFile(this, "config.yml");
        Bukkit.getPluginManager().registerEvents(new DeathListener(this, this.configFile), this);
    }

    @Override
    public void onDisable() { instance = null; }

}
