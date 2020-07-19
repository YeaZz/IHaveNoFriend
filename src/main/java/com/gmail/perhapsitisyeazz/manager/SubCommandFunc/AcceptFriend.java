package com.gmail.perhapsitisyeazz.manager.SubCommandFunc;

import com.gmail.perhapsitisyeazz.IHaveNoFriend;
import com.gmail.perhapsitisyeazz.util.Data;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class AcceptFriend {

    public static void friendAcceptCommand(Player sender, OfflinePlayer target) {
        HashMap<UUID, UUID> friendRequest = IHaveNoFriend.friendRequest;
        if (!friendRequest.containsKey(target.getUniqueId()) && !friendRequest.containsValue(sender.getUniqueId())) {
            sender.sendMessage(ChatColor.RED + "This request is invalid or has expired.");
            return;
        }
        friendRequest.remove(target.getUniqueId(), sender.getUniqueId());
        if (Data.alreadyFriend(sender, target)) {
            sender.sendMessage(new ComponentBuilder()
                    .append("[").color(ChatColor.DARK_GRAY)
                    .append("Friend").color(ChatColor.DARK_AQUA)
                    .append("] ").color(ChatColor.DARK_GRAY)
                    .append(target.getName()).color(ChatColor.AQUA)
                    .append(" is already your friend.").color(ChatColor.DARK_GREEN)
                    .create());
            return;
        }
        Data.addFriendFile(sender, target);
        sender.sendMessage(new ComponentBuilder()
                .append("[").color(ChatColor.DARK_GRAY)
                .append("Friend").color(ChatColor.DARK_AQUA)
                .append("] ").color(ChatColor.DARK_GRAY)
                .append(target.getName()).color(ChatColor.AQUA)
                .append(" has been added in your friend list.").color(ChatColor.DARK_GREEN)
                .create());
        if (!target.isOnline()) return;
        ((Player) target).sendMessage(new ComponentBuilder()
                .append("[").color(ChatColor.DARK_GRAY)
                .append("Friend").color(ChatColor.DARK_AQUA)
                .append("] ").color(ChatColor.DARK_GRAY)
                .append(sender.getName()).color(ChatColor.AQUA)
                .append(" has been added in your friend list.").color(ChatColor.DARK_GREEN)
                .create());
    }
}