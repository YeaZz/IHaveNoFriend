package com.gmail.perhapsitisyeazz.manager.SubCommandFunc;

import com.gmail.perhapsitisyeazz.util.Data;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class RemoveFriend {

    public static void friendRemoveCommand(Player sender, OfflinePlayer target) {
        if (!Data.alreadyFriend(sender, target)) {
            sender.sendMessage(new ComponentBuilder()
                    .append("[").color(ChatColor.DARK_GRAY)
                    .append("Friend").color(ChatColor.DARK_AQUA)
                    .append("] ").color(ChatColor.DARK_GRAY)
                    .append(target.getName()).color(ChatColor.AQUA)
                    .append(" is not your friend.").color(ChatColor.DARK_GREEN)
                    .create());
            return;
        }
        Data.removeFriendFile(sender, target);
        sender.sendMessage(new ComponentBuilder()
                .append("[").color(ChatColor.DARK_GRAY)
                .append("Friend").color(ChatColor.DARK_AQUA)
                .append("] ").color(ChatColor.DARK_GRAY)
                .append("You have successfully removed ").color(ChatColor.DARK_GREEN)
                .append(target.getName()).color(ChatColor.AQUA)
                .append(" from your friend list.").color(ChatColor.DARK_GREEN)
                .create());
        if (!target.isOnline()) return;
        ((Player) target).sendMessage(new ComponentBuilder()
                .append("[").color(ChatColor.DARK_GRAY)
                .append("Friend").color(ChatColor.DARK_AQUA)
                .append("] ").color(ChatColor.DARK_GRAY)
                .append(sender.getName()).color(ChatColor.AQUA)
                .append(" has removed you from his friend list.").color(ChatColor.DARK_GREEN)
                .create());
    }
}
