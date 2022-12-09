package com.github.aitooor.deathevents.acf;

import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public abstract class BukkitArgument {

    private final String name;
    private final String usage;
    private final String permission;
    private final String description;

    public BukkitArgument(String name, String usage, String permission, String description) {
        this.name = name;
        this.usage = usage;
        this.permission = permission;
        this.description = description;
    }

    public abstract boolean execute(CommandSender sender, String label, String[] args);

    public List<String> tabComplete(CommandSender sender, String label, String[] args) { return new ArrayList<>(); }

    public String getName() { return this.name; }

    public String getUsage() { return this.usage; }

    public String getPermission() { return this.permission; }

    public String getDescription() { return this.description; }
}

