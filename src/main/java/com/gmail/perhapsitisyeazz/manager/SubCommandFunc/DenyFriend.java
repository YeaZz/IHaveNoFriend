package com.gmail.perhapsitisyeazz.manager.SubCommandFunc;

import com.gmail.perhapsitisyeazz.IHaveNoFriend;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class DenyFriend {

    public static void friendDenyCommand(Player sender, OfflinePlayer target) {
        HashMap<UUID, UUID> friendRequest = IHaveNoFriend.friendRequest;
        if (!friendRequest.containsKey(target.getUniqueId()) && !friendRequest.containsValue(sender.getUniqueId())) {
            sender.sendMessage(new ComponentBuilder()
                    .append("[").color(ChatColor.DARK_GRAY)
                    .append("Friend").color(ChatColor.DARK_AQUA)
                    .append("] ").color(ChatColor.DARK_GRAY)
                    .append("This request is invalid or has expired.").color(ChatColor.RED)
                    .create());
            return;
        }
        friendRequest.remove(target.getUniqueId());
        sender.sendMessage(new ComponentBuilder()
                .append("[").color(ChatColor.DARK_GRAY)
                .append("Friend").color(ChatColor.DARK_AQUA)
                .append("] ").color(ChatColor.DARK_GRAY)
                .append("You have declined the friend request from ").color(ChatColor.DARK_GREEN)
                .append(target.getName()).color(ChatColor.AQUA)
                .create());
        if (!target.isOnline()) return;
        ((Player) target).sendMessage(new ComponentBuilder()
                .append("[").color(ChatColor.DARK_GRAY)
                .append("Friend").color(ChatColor.DARK_AQUA)
                .append("] ").color(ChatColor.DARK_GRAY)
                .append(sender.getName()).color(ChatColor.AQUA)
                .append(" has decline your friend request.").color(ChatColor.DARK_GREEN)
                .create());
    }
}
