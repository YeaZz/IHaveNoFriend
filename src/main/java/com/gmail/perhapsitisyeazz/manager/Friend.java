package com.gmail.perhapsitisyeazz.manager;

import com.gmail.perhapsitisyeazz.IHaveNoFriend;
import com.gmail.perhapsitisyeazz.util.Data;
import com.gmail.perhapsitisyeazz.util.Message;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moderocky.mask.command.Commander;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Friend {

    private static Friend friendManager;

    public static Friend getFriendManager() {
        return friendManager;
    }

    private final IHaveNoFriend instance = IHaveNoFriend.getInstance();

    private final HashMap<UUID, UUID> friendRequest = IHaveNoFriend.friendRequest;

    public void friendAcceptCommand(Player sender, OfflinePlayer target) {
        if (!friendRequest.containsKey(target.getUniqueId()) && !friendRequest.containsValue(sender.getUniqueId())) {
            sender.sendMessage(Message.getInvalidRequestMessage());
            return;
        }
        friendRequest.remove(target.getUniqueId(), sender.getUniqueId());
        if (Data.alreadyFriend(sender, target)) {
            sender.sendMessage(Message.getIsAlreadyYourFriendMessage(target));
            return;
        }
        Data.addFriendFile(sender, target);
        sender.sendMessage(Message.getSuccessfullyAddedMessage(target));
        if (!target.isOnline()) return;
        ((Player) target).sendMessage(Message.getAddedInFriendListMessage(sender));
    }

    public void friendDenyCommand(Player sender, OfflinePlayer target) {
        if (!friendRequest.containsKey(target.getUniqueId()) && !friendRequest.containsValue(sender.getUniqueId())) {
            sender.sendMessage(Message.getInvalidRequestMessage());
            return;
        }
        friendRequest.remove(target.getUniqueId());
        sender.sendMessage(Message.getSuccessfullyDeclinedMessage(target));
    }

    public void friendAddCommand(Player sender, OfflinePlayer target) {
        UUID sID = sender.getUniqueId();
        UUID tID = target.getUniqueId();
        if (sender == target) {
            sender.sendMessage(Message.getCannotAddFriendMessage());
            return;
        }
        if (Data.alreadyFriend(sender, target)) {
            sender.sendMessage(Message.getIsAlreadyYourFriendMessage(target));
            return;
        }
        if (!Data.hasFriendToggle(target)) {
            sender.sendMessage(Message.getDoesNotAcceptFriendMessage(target));
            return;
        }
        if (friendRequest.containsKey(sID) && friendRequest.containsValue(tID)) {
            sender.sendMessage(Message.getRequestAlreadySend(target));
            return;
        }
        if (friendRequest.containsKey(tID) && friendRequest.containsValue(sID)) {
            friendRequest.remove(tID, sID);
            Data.addFriendFile(sender, target);
            sender.sendMessage(Message.getAddedInFriendListMessage(target));
            if (target.isOnline()) ((Player) target).sendMessage(Message.getAddedInFriendListMessage(sender));
            return;
        }
        friendRequest.put(sID, tID);
        sender.sendMessage(Message.getSenderRequestMessage(target));
        if (target.isOnline()) ((Player) target).sendMessage(Message.getTargetRequestMessage(sender));
        BukkitRunnable runnable;
        runnable = new BukkitRunnable() {
            @Override
            public void run() {
                if (friendRequest.containsKey(sID) && friendRequest.containsValue(tID)) {
                    friendRequest.remove(sID, tID);
                    sender.sendMessage(Message.getFriendRequestExpired(target));
                    if (target.isOnline()) ((Player) target).sendMessage(Message.getFriendRequestExpired(sender));
                }
            }
        };
        runnable.runTaskLater(instance, 1200);
    }

    public void friendRemoveCommand(Player sender, OfflinePlayer target) {
        if (!Data.alreadyFriend(sender, target)) {
            sender.sendMessage(Message.getIsNotYourFriendMessage(target));
            return;
        }
        Data.removeFriendFile(sender, target);
        sender.sendMessage(Message.getSuccessfullyRemovedMessage(target));
    }

    public void friendListFriend(Player sender) {
        JsonObject senderObject = Data.getJsonObject(sender);
        JsonArray jsonArray = senderObject.get("FriendList").getAsJsonArray();
        if (jsonArray.size() == 0) {
            sender.sendMessage(Message.getNoFriendMessage());
            return;
        }
        ComponentBuilder builder = new ComponentBuilder();
        builder
                .append("[").color(ChatColor.DARK_GRAY)
                .append("Friend").color(ChatColor.DARK_AQUA)
                .append("] ").color(ChatColor.DARK_GRAY)
                .append("Here is your friend list: ").color(ChatColor.DARK_GREEN)
                .append("(").color(ChatColor.DARK_GRAY)
                .append(String.valueOf(jsonArray.size())).color(ChatColor.AQUA)
                .append(")").color(ChatColor.DARK_GRAY);
        for (JsonElement array : jsonArray) {
            UUID uuid = UUID.fromString(array.getAsString());
            OfflinePlayer friend = Bukkit.getPlayer(uuid);
            if (friend == null) continue;
            builder
                    .append("\n » ").color(ChatColor.DARK_AQUA)
                    .append(friend.getName()).color(ChatColor.GREEN);
            if (friend.isOnline()) builder
                    .append(" (").color(ChatColor.DARK_GRAY)
                    .append("Online").color(ChatColor.AQUA)
                    .append(")").color(ChatColor.DARK_GRAY);
        }
        sender.sendMessage(builder.create());
    }

    public void friendToggleCommand(Player sender) {
        JsonObject object = Data.getJsonObject(sender);
        if (Data.hasFriendToggle(sender)) {
            sender.sendMessage(Message.getNobodyCanAddFriendMessage());
            object.addProperty("hasFriendRequestToggle", false);
            return;
        }
        sender.sendMessage(Message.getEveryCanAddFriendMessage());
        object.addProperty("hasFriendRequestToggle", true);
        Data.saveObject(Data.getData(sender), object);
    }

    public void friendHelpCommand(CommandSender sender, Commander<?> commander) {
        if (sender instanceof Player) {
            ComponentBuilder builder = new ComponentBuilder();
            builder.append("Running ").color(ChatColor.DARK_GREEN);
            builder.append(instance.getName()).color(ChatColor.AQUA);
            builder.append(" v");
            builder.append(instance.getVersion());
            builder.append(".").color(ChatColor.DARK_GREEN);
            for (Map.Entry<String, String> entry : commander.getPatternDescriptions().entrySet()) {
                String pattern = entry.getKey();
                String desc = entry.getValue();
                if (!pattern.contains("help")) {
                    String command = "/" + commander.getCommand();
                    String arg = pattern.split(" ")[0].substring(0, 1).toUpperCase() + pattern.split(" ")[0].substring(1);
                    builder
                            .append("\n» ").color(ChatColor.DARK_AQUA)
                            .append("/" + commander.getCommand() + " " + pattern).color(ChatColor.GREEN)
                            .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText(
                                    ChatColor.DARK_GREEN + "Command: " + ChatColor.AQUA + arg +
                                            ChatColor.DARK_GREEN + "\nDescription: " + ChatColor.AQUA + desc +
                                            ChatColor.DARK_GREEN + "\nUsage: " + ChatColor.AQUA + command + " " + pattern +
                                            "\n \n" + ChatColor.GRAY + "Click to execute.")))
                            .event(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, command + " " + pattern.split(" ")[0] + " "))
                            .append(" ").retain(ComponentBuilder.FormatRetention.NONE).reset();
                }
            }
            sender.sendMessage(builder.create());
        } else {
            sender.sendMessage(new ComponentBuilder()
                    .append("Running ").color(ChatColor.DARK_GREEN)
                    .append(instance.getName()).color(ChatColor.AQUA)
                    .append(" v")
                    .append(instance.getVersion())
                    .append(".").color(ChatColor.DARK_GREEN)
                    .create());
            sender.sendMessage(new ComponentBuilder()
                    .append("Only executable by players.").color(ChatColor.RED)
                    .create());
            for (String pattern : commander.getPatterns()) {
                if (!pattern.contains("help")) {
                    sender.sendMessage(new ComponentBuilder()
                            .append("» ").color(ChatColor.DARK_AQUA)
                            .append("/" + commander.getCommand() + " " + pattern).color(ChatColor.GREEN)
                            .append(" ").retain(ComponentBuilder.FormatRetention.NONE).reset()
                            .create());
                }
            }
        }
    }
}