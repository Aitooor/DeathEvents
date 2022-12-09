package com.github.aitooor.deathevents.acf;

import org.bukkit.command.CommandMap;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class CommandAPI {

    private final List<BukkitCommand> commands = new ArrayList<>();
    private final Plugin plugin;

    public CommandAPI(Plugin plugin) {
        this.plugin = plugin;
    }


    public void enable() {
        try {
            for (BukkitCommand command : this.commands) {
                if (!this.getCommandMap().register(this.plugin.getName(), command)) return;
                command.unregister(this.getCommandMap());
                this.getCommandMap().register(this.plugin.getName(), command);

            }
            this.plugin.getLogger().info(this.commands.size()+ " commands loaded correctly");
        } catch (Exception ex) {
            this.plugin.getLogger().severe("Commands could not be registered. Reason " + ex.getMessage());
        }
    }

    public void disable() {
        try {
            for (BukkitCommand command : this.commands) {
                if (!this.getCommandMap().register(this.plugin.getName(), command)) return;
                command.unregister(this.getCommandMap());
            }
        } catch (Exception ex) {
            this.plugin.getLogger().severe("Commands could not be unregistered. Reason " + ex.getMessage());
        }
    }

    public void register(BukkitCommand command) { this.commands.add(command); }

    public void unregister(BukkitCommand command) { this.commands.remove(command); }

    CommandMap getCommandMap() throws Exception {
        Field field = this.plugin.getServer().getClass().getDeclaredField("commandMap");
        field.setAccessible(true);
        return (CommandMap) field.get(this.plugin.getServer());
    }

    public List<BukkitCommand> getCommands() { return this.commands; }

    public BukkitCommand getCommand(String name) { return this.commands.stream().filter(command -> command.getName().equalsIgnoreCase(name)).findFirst().orElse(null); }

}
