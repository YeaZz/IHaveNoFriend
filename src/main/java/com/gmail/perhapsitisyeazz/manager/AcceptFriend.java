package com.gmail.perhapsitisyeazz.manager;

import com.gmail.perhapsitisyeazz.IHaveNoFriend;
import com.gmail.perhapsitisyeazz.util.Data;
import com.gmail.perhapsitisyeazz.util.Message;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class AcceptFriend {

    public static void friendAcceptCommand(Player sender, OfflinePlayer target) {
        HashMap<UUID, UUID> friendRequest = IHaveNoFriend.friendRequest;
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
}