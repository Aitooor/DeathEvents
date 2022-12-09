package com.github.aitooor.deathevents;

import com.github.aitooor.deathevents.acf.CommandAPI;
import com.github.aitooor.deathevents.commands.MainCommand;
import com.github.aitooor.deathevents.config.ConfigFile;
import com.github.aitooor.deathevents.config.MessageFile;
import com.github.aitooor.deathevents.liteners.DeathListener;
import com.github.aitooor.deathevents.liteners.RespawnListener;
import com.github.aitooor.deathevents.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class DeathEvents extends JavaPlugin {

    private static DeathEvents instance;
    private ConfigFile configFile;
    private MessageFile messageFile;
    private CommandAPI command;

    @Override
    public void onEnable() {
        instance = this;

        this.configFile = new ConfigFile(this, "config.yml");
        this.messageFile = new MessageFile(this, "messages.yml");
        if(!configFile.getBoolean("death_events.enable") && !configFile.getBoolean("respawn_events.enable")) {
            instance.getPluginLoader().disablePlugin(this);
            ChatUtils.errorLog(this.messageFile.getString("error.both_false"));
            return;
        }
        ChatUtils.infoLog("Loaded Config and Messages Files");

        this.command = new CommandAPI(this);
        this.command.register(new MainCommand(this, this.configFile, this.messageFile));
        ChatUtils.infoLog("Loaded " + this.command.getCommands().size() + " commands");

        Bukkit.getPluginManager().registerEvents(new DeathListener(this, this.configFile, this.messageFile), this);
        Bukkit.getPluginManager().registerEvents(new RespawnListener(this, this.configFile, this.messageFile), this);
        ChatUtils.infoLog("Loaded Death and Respawn Listeners");
    }

    @Override
    public void onDisable() { instance = null; }
}
