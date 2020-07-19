package com.gmail.perhapsitisyeazz.manager.SubCommandFunc;

import com.gmail.perhapsitisyeazz.IHaveNoFriend;
import com.gmail.perhapsitisyeazz.util.Data;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.UUID;

public class AddFriend {

    private static final IHaveNoFriend instance = IHaveNoFriend.getInstance();

    public static void friendAddCommand(Player sender, OfflinePlayer target) {
        HashMap<UUID, UUID> friendRequest = IHaveNoFriend.friendRequest;
        UUID sID = sender.getUniqueId();
        UUID tID = target.getUniqueId();
        if (sender == target) {
            sender.sendMessage(new ComponentBuilder()
                    .append("[").color(ChatColor.DARK_GRAY)
                    .append("Friend").color(ChatColor.DARK_AQUA)
                    .append("] ").color(ChatColor.DARK_GRAY)
                    .append("You cannot add this player to be friend.").color(ChatColor.RED)
                    .create());
            return;
        }
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
        if (friendRequest.containsKey(sID) && friendRequest.containsValue(tID)) {
            sender.sendMessage(new ComponentBuilder()
                    .append("[").color(ChatColor.DARK_GRAY)
                    .append("Friend").color(ChatColor.DARK_AQUA)
                    .append("] ").color(ChatColor.DARK_GRAY)
                    .append("Request already sent to ").color(ChatColor.DARK_GREEN)
                    .append(target.getName()).color(ChatColor.AQUA)
                    .append(".").color(ChatColor.DARK_GREEN)
                    .create());
        } else if (friendRequest.containsKey(tID) && friendRequest.containsValue(sID)) {
            friendRequest.remove(tID, sID);
            sender.sendMessage(new ComponentBuilder()
                    .append("[").color(ChatColor.DARK_GRAY)
                    .append("Friend").color(ChatColor.DARK_AQUA)
                    .append("] ").color(ChatColor.DARK_GRAY)
                    .append(target.getName()).color(ChatColor.AQUA)
                    .append(" has been added in your friend list.").color(ChatColor.DARK_GREEN)
                    .create());
            if (target.isOnline()) ((Player) target).sendMessage(new ComponentBuilder()
                    .append("[").color(ChatColor.DARK_GRAY)
                    .append("Friend").color(ChatColor.DARK_AQUA)
                    .append("] ").color(ChatColor.DARK_GRAY)
                    .append(sender.getName()).color(ChatColor.AQUA)
                    .append(" has been added in your friend list.").color(ChatColor.DARK_GREEN)
                    .create());
        } else {
            friendRequest.put(sID, tID);
            sender.sendMessage(new ComponentBuilder()
                    .append("[").color(ChatColor.DARK_GRAY)
                    .append("Friend").color(ChatColor.DARK_AQUA)
                    .append("] ").color(ChatColor.DARK_GRAY)
                    .append("Friend request send to ").color(ChatColor.DARK_GREEN)
                    .append(target.getName()).color(ChatColor.AQUA)
                    .append(".").color(ChatColor.DARK_GREEN)
                    .append("\n").append(target.getName()).color(ChatColor.AQUA)
                    .append("has 60 seconds to accept your request.").color(ChatColor.DARK_GREEN)
                    .create());
            if (target.isOnline()) ((Player) target).sendMessage(new ComponentBuilder()
                    .append("[").color(ChatColor.DARK_GRAY)
                    .append("Friend").color(ChatColor.DARK_AQUA)
                    .append("] ").color(ChatColor.DARK_GRAY)
                    .append("You received a friend request from ").color(ChatColor.DARK_GREEN)
                    .append(sender.getName()).color(ChatColor.AQUA)
                    .append("\nYou have ").color(ChatColor.DARK_GREEN)
                    .append("60 seconds").color(ChatColor.AQUA)
                    .append(" left to accept.\n         ")
                    .append("[Yes]").color(ChatColor.GREEN)
                    .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText("Click to execute")))
                    .event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/friend accept " + target.getName()))
                    .append("         ").retain(ComponentBuilder.FormatRetention.NONE).reset()
                    .append("[No]").color(ChatColor.RED)
                    .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText("Click to execute")))
                    .event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/friend deny " + target.getName()))
                    .create());
            BukkitRunnable runnable;
            runnable = new BukkitRunnable() {
                @Override
                public void run() {
                    if (friendRequest.containsKey(sID) && friendRequest.containsValue(tID)) {
                        friendRequest.remove(sID, tID);
                        sender.sendMessage(new ComponentBuilder()
                                .append("[").color(ChatColor.DARK_GRAY)
                                .append("Friend").color(ChatColor.DARK_AQUA)
                                .append("] ").color(ChatColor.DARK_GRAY)
                                .append("Friend request from ").color(ChatColor.DARK_GREEN)
                                .append(target.getName()).color(ChatColor.AQUA)
                                .append(" expired.").color(ChatColor.DARK_GREEN)
                                .create());
                        if (target.isOnline()) ((Player) target).sendMessage(new ComponentBuilder()
                                .append("[").color(ChatColor.DARK_GRAY)
                                .append("Friend").color(ChatColor.DARK_AQUA)
                                .append("] ").color(ChatColor.DARK_GRAY)
                                .append("Friend request from ").color(ChatColor.DARK_GREEN)
                                .append(sender.getName()).color(ChatColor.AQUA)
                                .append(" expired.").color(ChatColor.DARK_GREEN)
                                .create());
                    }
                }
            };
            runnable.runTaskLater(instance, 1200);
        }
    }
}
