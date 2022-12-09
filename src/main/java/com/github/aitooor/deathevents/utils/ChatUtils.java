package com.github.aitooor.deathevents.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.logging.Level;
import java.util.stream.Collectors;

public interface ChatUtils {

    static String prefix() {
        return "&8[&cDeathEvents&8] ";
    }

    static String prefixNoColor() {
        return "[DeathEvents] ";
    }

    static String translate(String translate) {
        return ChatColor.translateAlternateColorCodes('&', translate);
    }

    static List<String> translate(List<String> translate) {
        return translate.stream().map(ChatUtils::translate).collect(Collectors.toList());
    }

    static void send(Player sender, String message) {
        sender.sendMessage(translate(prefix() + message));
    }

    static void sendNoPrefix(Player sender, String message) {
        sender.sendMessage(translate(message));
    }

    static void infoLog(String message) { Bukkit.getServer().getLogger().log(Level.INFO, prefixNoColor() + message); }
    static void errorLog(String message) { Bukkit.getServer().getLogger().log(Level.WARNING, prefixNoColor() + message); }
    static void severeLog(String message) { Bukkit.getServer().getLogger().log(Level.SEVERE, prefixNoColor() + message); }
}
