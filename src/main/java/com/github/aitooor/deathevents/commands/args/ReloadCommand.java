package com.github.aitooor.deathevents.commands.args;

import com.github.aitooor.deathevents.DeathEvents;
import com.github.aitooor.deathevents.acf.BukkitArgument;
import com.github.aitooor.deathevents.config.ConfigFile;
import com.github.aitooor.deathevents.config.MessageFile;
import com.github.aitooor.deathevents.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReloadCommand extends BukkitArgument {
    private ConfigFile config;
    private MessageFile messageFile;

    public ReloadCommand(DeathEvents plugin, ConfigFile config, MessageFile messageFile) {
        super("reload", "/deathevents reload", "deathevents.admin", "Reload command");

        this.config = config;
        this.messageFile = messageFile;
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        Player player = Bukkit.getPlayer(sender.getName());
        config.reload();
        messageFile.reload();
        ChatUtils.send(player, messageFile.getString("commands.reload"));
        return false;
    }
}
