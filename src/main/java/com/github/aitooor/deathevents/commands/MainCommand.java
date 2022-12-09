package com.github.aitooor.deathevents.commands;

import com.github.aitooor.deathevents.DeathEvents;
import com.github.aitooor.deathevents.acf.BukkitCommand;
import com.github.aitooor.deathevents.commands.args.ReloadCommand;
import com.github.aitooor.deathevents.config.ConfigFile;
import com.github.aitooor.deathevents.config.MessageFile;

public class MainCommand extends BukkitCommand {
    private ConfigFile config;
    private MessageFile messageFile;

    public MainCommand(DeathEvents plugin, ConfigFile config, MessageFile messageFile) {
        super("deathevents", "deathevents.admin", "Main Command");

        this.config = config;
        this.messageFile = messageFile;

        this.register(new ReloadCommand(plugin, this.config, this.messageFile));
    }
}
