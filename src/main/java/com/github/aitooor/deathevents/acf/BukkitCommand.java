package com.github.aitooor.deathevents.acf;

import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BukkitCommand extends org.bukkit.command.defaults.BukkitCommand {

    private final Map<String, BukkitArgument> arguments = new HashMap<>();
    private final String name;
    private final String permission;
    private final String description;

    public BukkitCommand(String name, String permission, String description) {
        super(name);
        setDescription(description);
        setPermission(permission);
        this.name = name;
        this.description = description;
        this.permission = permission;
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        BukkitArgument argument = this.getArgument(args[0]);
        argument.execute(sender, label, Arrays.copyOfRange(args, 1, args.length));
        return false;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String label, String[] args) {
        return (this.getArgument(args[0]) != null && sender.hasPermission(this.getPermission())) ? this.arguments.get(args[0]).tabComplete(sender, label, Arrays.copyOfRange(args, 1, args.length)) : this.arguments.values().stream().map(BukkitArgument::getName).collect(Collectors.toList());
    }

    public void register(BukkitArgument argument) { this.arguments.put(argument.getName(), argument); }

    public void unregister(BukkitArgument argument) { this.arguments.remove(argument.getName()); }

    @Override
    public String getName() { return this.name; }

    @Override
    public String getPermission() { return this.permission; }

    public String getDescription() { return this.description; }

    public Map<String, BukkitArgument> getArguments() { return this.arguments; }

    public BukkitArgument getArgument(String name) { return this.arguments.get(name);  }

}
